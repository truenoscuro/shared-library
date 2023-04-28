package vars

import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
import es.tresdigits.jenkins.Angular



// Field de clases
@Field Utils utils = new Utils()
@Field Angular angular = new Angular()


// Test function
def test(step,env){
    init(step,env);
    /*
    step.echo "Test utils"
    utils.emailSent("nofreruizsalom@gmail.com","test prova")
    */
    step.echo " Test Angular"
    angular.init(step,utils)

}

// inicialitzacio de utils
def init(step,env){
    step.echo "Init pipeline... "
    utils.init(step,env)
}


// funcions de templades