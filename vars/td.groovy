package vars

//import @Field
import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
import es.tresdigits.jenkins.Angular
import es.tresdigits.jenkins.DockerCustom
import es.tresdigits.jenkins.Sonar
import es.tresdigits.jenkins.Spring


// Field de clases
@Field Utils utils = new Utils()
@Field Angular angular = new Angular()
@Field DockerCustom dk = new DockerCustom()
@Field Sonar sonar = new Sonar()
@Field Spring spring = new Spring()


// Test function
def test(script,env){
    init(script,env)
    script.echo "Test utils"
    utils.emailSent("nofreruizsalom@gmail.com","test prova")
}

def testAngular(script,env){
    
    init(script,env)
    script.echo " Test Angular"
    angular.init(script,utils)
    angular.newProject("prova-1")

}

def testDocker(script,env,docker){
    init(script,env)
    script.echo " Test Docker "
    dk.init(script,docker,utils)

    dk.image("node")

}

def testSonar(script,env){
    //TODO deixar per mes tard
    init(script,env)
    script.echo "Test Sonar"
    sonar.init(script,env)
}

// Test buildear en angular en Docker
def testAngular2(script,env,docker){
    utils.init(script,env)
    utils.initGit("https://github.com/angular-university/angular-testing-course.git") // git de prueba angular
    
    dk.init(script,docker,utils)
    
    angular.init(script,utils)

    def node = dk.image("node")   
    // base de dades fer-la correr
    //TODO  String wS= env["WORKSPACE"]
    //esto furula
    //Test
    node.inside("-u root"){
        angular.install()
        utils.git()
        angular.iPackage()
        angular.build()
    }

}

def testSpring2(script,env,docker){
    utils.init(script,env)
    utils.initGit("https://github.com/Aravamudhan/spring-boot-app.git") // git de prueba angular
    
    dk.init(script,docker,utils)
    
    spring.init(script,utils)

    def maven = dk.image("maven")   
    // base de dades fer-la correr
    //TODO 
    //esto furula
    //Test
    maven.inside("-u root"){
        utils.git()
        spring.build()
    }
}

def testDockerFileAngular(script,env,docker){
    
    utils.init(script,env)
    utils.initGit("https://github.com/angular-university/angular-testing-course.git")
    
    dk.init(script,docker,utils)
    script.echo "Test DockerFile Angular"
 
    dk.runAngular("-u root -p 80:80")



}

def testDockerFileSpring(script,env,docker){
    
    utils.init(script,env)
    utils.initGit("https://github.com/truenoscuro/spring-example.git")
    
    dk.init(script,docker,utils)
    script.echo "Test DockerFile Spring"
 
    dk.runSpring("-u root -p 8080:8080")



}


// inicialitzacio de utils
def init(script,env,docker){
    script.stages("init pipeline"){
        utils.init(script,env)
        dk.init(script,docker,utils)   
    }   
}


// funcions de templades