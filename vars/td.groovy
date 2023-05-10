package vars

//import @Field
import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
import es.tresdigits.jenkins.Switcher
//Aqui nomes ha daver ses funcions de dins node 
// Field de clases
@Field Utils utils;
@Field Switcher switcher

//script
def init( script , env , globalConfig ){
    script.echo "restructuring init"
    utils = new Utils(script , env , globalConfig )
    switcher = new Switcher( utils )
}
//nom a al stage
def git ( Map conf = utils.globalConfig.git ){
    def script = utils.script
    def git = utils.git
    script.stage("git clone de ${conf.url}"){
        git(conf)
    }
}





@Field 
def stage = { Map jobs -> 
    def script = utils.script
    String name = jobs.name ?: "stage"
    script.stage(name){
        jobs.each{ key,funct ->
           //utils.echo "${key} --- ${funct}"
            switcher.searchFunct(key,funct).call().call()
            //utils.echo "${lang.imprimir()}"
            
                

                //switchFunction(key,funct).call() // punt call per cridar les closures
        }
    }
    
}

@Field 
def parallel = { Map jobs -> 
    def script = utils.script
    def stages = [:]
    jobs.each{
        key,funct ->
            String name = "${key}"
            if(funct instanceof String ) name = "${name}-${funct}"
            stages["${name}"] =  funct
            //stages["${name}"] = switchFunction(key,funct)
    }
    script.parallel(stages)
    
}











// inicialitzacio de utils
/*
def init(script,env,sonarName="${sonarName}",nameTool="${nameTool}"){
    script.stage("Init"){
        utils.init(script,env) 
        docker.init(script,utils) 
        //Tod init de ses funcions!
        angular.init(script,utils)
        ssh.init(script,utils)
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
        case "${angular.toString()}":
            stage = { angular."${funct}"() }
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

//ssh comand
def sshComand(Map conf,String command,boolean isSudo = false){
    def script = script()
    script.stage("comand ssh"){
       ssh.command(conf,command,isSudo)
    }
}


def sshRunDocker(Map conf,String language, boolean isSudo = false){
    def script = script()
    script.stage(" docker run ssh"){
        ssh.docker(conf,language,isSudo)
    }
}


// funcions de templades

*/
