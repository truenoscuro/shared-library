package vars

import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils


// Field de clases
@Field Utils utils = new Utils()



// inicialitzacio de utils
def init(script,env){
    utils.init(script,env)
}


// funcions de templades