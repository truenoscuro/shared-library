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
@Field Maven mvn = new Maven()
@Field Pipeline pipe = new Pipeline()

//script
def script(){ return utils.script }


// inicialitzacio de utils
def init(script,env,docker){
    script.stage("Init"){
        utils.init(script,env) // aqui crida
        dk.init(script,docker,utils)  
    }

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
    /*switch(key.toLowerCase()){
        case "${mvn}":
            stage = { echo "hello word" }
            break
        default:
            // pararlel normal
            stage = { funct() }
    }*/
    stage = {
        echo "${mvn.toString()}"
    }
    
    return stage

}

//  
def parallel(Map jobs){
    def stages = [:]
    //key --> nom del stage 
    //value-- > ["maven", "funcio" ]
    jobs.each{
        key,funct ->
            //TODO mira el nom a posar
            stages["${key}-${funct}"] = switchFunction(key,funct)
            /*
            // pararlel normal
            if("personal" == key ){
                stages["personal"] = {
                    funct()
                }
            // es personalitzat
            }else{
                stages["${key}-${funct}"] = {
                echo "hello world"
                }
            }
            */
           
    }
    /*
    stages["mac"] = {
            echo "build for mac"
        }
    */
    // crees els jobs --> els he fas abaix
    
    script().parallel(stages)



}





// funcions de templades