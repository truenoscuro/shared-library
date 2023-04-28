import groovy.transform.Field
import es.tresdigits.step

@Field Step step = new Step()

def init(sript){
    echo "init step"
}


def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}