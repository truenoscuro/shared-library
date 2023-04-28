package vars

import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
import es.tresdigits.jenkins.Angular
import es.tresdigits.jenkins.Docker



// Field de clases
@Field Utils utils = new Utils()
@Field Angular angular = new Angular()
@Field Docker docker = new Docker()

// Test function
def test(step,env){
    init(step,env)
    step.echo "Test utils"
    utils.emailSent("nofreruizsalom@gmail.com","test prova")
   
}

def testDocker(step,env){
    
    init(step,env)
    step.echo " Test Docker"
    docker.init(step,utils)
    
}

def testAngular(step,env)
    
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