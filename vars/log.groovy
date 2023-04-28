@groovy.transform.Field
es.tresdigits.Step step = new es.tresdigits.Step()

def init(sript){
    script.echo "init step"
    step.init(sript)
}

def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}