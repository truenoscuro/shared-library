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

    dk.image("node")

}

def testSonar(step,env){
    //TODO deixar per mes tard
    init(step,env)
    step.echo "Test Sonar"
    sonar.init(step,env)
}

// Test desplegar en angular en Docker
def testAngular2(step,env,docker){
    utils.init(step,env)
    utils.initGit("https://github.com/angular-university/angular-testing-course.git")
    
    dk.init(step,docker,utils)
    
    angular.init(step,utils)

    def img = dk.image("node")
    img.inside("-u root"){
        angular.install()
        utils.git()
        step.sh "cd angular-testing-course"
        step.sh "ls"    
    }
}



// inicialitzacio de utils
def init(step,env,docker){
    step.echo "Init pipeline... "
    utils.init(step,env)
}


// funcions de templades