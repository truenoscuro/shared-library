import groovy.transform.Field
import es.tresdigits.Step

@Field Step step = new Step()

def init(sript){
    echo "init step"
}

def holaMundo(){
    step.holaMundo()
}

def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}