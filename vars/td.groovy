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
//  
@NonCPS 
def parallel(Map jobs){
    def stages = [:]
    //key --> nom del stage 
    //value-- > ["maven", "funcio" ]
    jobs.each{
        key,value -> 
            stages["${key}"] = {
                echo "hello world"
            }
        
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