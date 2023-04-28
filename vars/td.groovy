package vars

import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
import es.tresdigits.jenkins.Angular
import es.tresdigits.jenkins.Docker



// Field de clases
@Field Utils utils = new Utils()
@Field Angular angular = new Angular()
@Field DockerCustom dockerCustom = new DockerCustom()

// Test function
def test(step,env){
    init(step,env)
    step.echo "Test utils"
    utils.emailSent("nofreruizsalom@gmail.com","test prova")
   
}

def testDocker(step,env,docker){
    init(step,env)
    step.echo " Test Docker"
    dockerCustom.init(step,utils,docker)
    dockerCustom.build()
    
}

def testAngular(step,env){
    
    init(step,env)
    step.echo " Test Angular"
    angular.init(step,utils)
    angular.newProject("prova-1")

}

// inicialitzacio de utils
def init(step,env){
    step.echo "Init pipeline... "
    utils.init(step,env)
}


// funcions de templades