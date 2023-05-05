package vars

//import @Field
import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
import es.tresdigits.jenkins.Angular
import es.tresdigits.jenkins.DockerCustom
import es.tresdigits.jenkins.Sonar
import es.tresdigits.jenkins.Maven
import es.tresdigits.jenkins.Pipeline


// Field de clases
@Field Utils utils = new Utils()
@Field Angular angular = new Angular()
@Field DockerCustom dk = new DockerCustom()
@Field Sonar sonar = new Sonar()
@Field Maven maven = new Maven()
@Field Pipeline pipe = new Pipeline()

// parametros default
@Field  String sonarName = "prueba"
@Field  String nameTool = "sonar"
//script
def script(){ return utils.script }


// inicialitzacio de utils
def init(script,env,docker,sonarName="${sonarName}",nameTool="${nameTool}"){
    script.stage("Init"){
        utils.init(script,env) 
        dk.init(script,docker,utils) 
        //Tod init de ses funcions!
        maven.init(script,utils)
        sonar.init(script,utils,sonarName,nameTool)
    }
}
def setNameSonar(String sonarName){
    sonar.name = sonarName
}

def git(String gitUrl="",String credentialsId=''){
    def script = script()
    try{
        utils.initGit( gitUrl , credentialsId )

        script.stage("clone git"){
            utils.git()
        }

    }catch(Exception ex){
        script.error "Falta el url del git"
    }   
}

def switchFunction(String key, funct){
    def stage = {}
    try{
        if( funct instanceof String ) stage = { "${key}"."${funct}"() }
        else stage = { funct() }
    } cath( Exception ex ){
        script().error "El compilador no funciona o la funcion no existe"
    }
    
    return stage

}
//TODO cambia el nom a tot igual sonar
def setSonar(String nameTool="",String sonarName=""){
    if(nameTool != "") sonar.scannerTool = nameTool
    if(sonarName != "") sonar.name = sonarName
}

def parallelSonar(boolean binaries=true,String nameTool="", String sonarName="" ,Map jobs){

    setSonar(nameTool,sonarName)
    
    String funct="scanner"
    if(binaries) funct= "${funct}Binaries"
    jobs["sonar"]= funct
    parallel(jobs)
}

def scanner(boolean binaries=true,String nameTool="", String sonarName=""){
    def script = script()

    script.stage("sonarTest"){
        sonar.scanner() //TODO aqui posar lo de binaries
    }

}

//  
def parallel(Map jobs){
    def stages = [:]

    def indx = 0
    jobs.each{
        key,funct ->
            String name = "${key}"
            if(funct instanceof String ) name = "${name}-${funct}"
            stages["${name}"] = switchFunction(key,funct)
           
    }



    
    script().parallel(stages)
    
    



}





// funcions de templades