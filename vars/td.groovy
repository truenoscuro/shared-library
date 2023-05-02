package vars

import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
import es.tresdigits.jenkins.Angular
import es.tresdigits.jenkins.DockerCustom
import es.tresdigits.jenkins.Sonar

//docker ve de docker-workflow



// Field de clases
@Field Utils utils = new Utils()
@Field Angular angular = new Angular()
@Field DockerCustom dk = new DockerCustom()
@Field Sonar sonar = new Sonar()


// Test function
def test(step,env){
    init(step,env)
    step.echo "Test utils"
    utils.emailSent("nofreruizsalom@gmail.com","test prova")
   
}

def testAngular(step,env){
    
    init(step,env)
    step.echo " Test Angular"
    angular.init(step,utils)
    angular.newProject("prova-1")

}

def testDocker(step,env,docker){
    init(step,env)
    step.echo " Test Docker "
    dk.init(step,docker,utils)

    dk.imageNode()

}

def testSonar(step,env){
    init(step,env)
    step.echo "Test Sonar"
    sonar.init(step,env)
}









// inicialitzacio de utils
def init(step,env){
    step.echo "Init pipeline... "
    utils.init(step,env)
}


// funcions de templades