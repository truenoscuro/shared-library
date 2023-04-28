@groovy.transform.Field
es.tresdigits.Step step = new es.tresdigits.Step()

def init(sript){
    step.init(sript)
    return step;
}

def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}