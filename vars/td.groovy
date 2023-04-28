package vars

import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils


// Field de clases
@Field Utils utils = new Utils()


// Test function
def test(step,env){
    init(step,env);
    step.echo "Test utils init"
    
}

// inicialitzacio de utils
def init(step,env){
    step.echo "Init pipeline... "
    utils.init(step,env)
}


// funcions de templades