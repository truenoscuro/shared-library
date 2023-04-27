@groovy.transform.Field
es.tresdigits.Step step = new es.tresdigits.Step(this)


def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}