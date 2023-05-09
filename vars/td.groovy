package vars

//import @Field
import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
//import es.tresdigits.jenkins.Angular
import es.tresdigits.jenkins.Docker
import es.tresdigits.jenkins.Sonar
import es.tresdigits.jenkins.Maven



// Field de clases
@Field Utils utils = new Utils()
//@Field Angular angular = new Angular()
@Field Docker docker = new Docker()
@Field Sonar sonar = new Sonar()
@Field Maven maven = new Maven()


// parametros default
@Field  String sonarName = "prueba"
@Field  String nameTool = "sonar"
//script


// inicialitzacio de utils
def init(script,env,sonarName="${sonarName}",nameTool="${nameTool}"){
    script.stage("Init"){
        utils.init(script,env) 
        docker.init(script,utils) 
        //Tod init de ses funcions!
        maven.init(script,utils)
        //sonar.init(script,utils,sonarName,nameTool)
    }
}
//Stes i geters
def setSonar(String nameTool="",String sonarName=""){
    if(nameTool != "") sonar.scannerTool = nameTool
    if(sonarName != "") sonar.name = sonarName
}

// funcions utils
def script(){ return utils.script }

// funcio clau per seleccionar clase 
// TODO magradiria que creas se clase la inicialitzes i tires la funcio
def switchFunction(String key, funct){
    def stage = {}

    switch(key.toLowerCase()){
        //TODO aqui necesita posar tots els casos
        case "${maven.toString()}":
            //funct =  maven."${funct}"()
            stage = { maven."${funct}"() }
            break
        case "${sonar.toString()}":
            stage = { sonar."${funct}"() }
            break
        default:
           // pararlel normal hi ha una error si no existeix el cas i no es una funct()
            stage = { funct() }       
    }

    return stage
}



//TODO cambia el nom a tot igual sonar



//funcions modificades
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

def stage(Map job,String nameStage="stage"){
    def script = script()
    script.stage("${nameStage}"){
        job.each{ key,funct ->
         switchFunction(key,funct).call() // punt call per cridar les closures
         }
    }
}


def parallel(Map jobs){
    def stages = [:]
    jobs.each{
        key,funct ->
            String name = "${key}"
            if(funct instanceof String ) name = "${name}-${funct}"
            stages["${name}"] = switchFunction(key,funct)
    }
    script().parallel(stages)
}




//nombre obligatorio
// convertir los arg en un mapa
def dockerfileRun(String imageName="default ${utils.env.BUILD_NUMBER}",String dockerfile="Dockerfile", String path=".",args=""){
    script().stage("Build ${dockerfile}"){
        docker.remove()
        docker.run(imageName,dockerfile,path,args)
    }
}






// funcions de templades