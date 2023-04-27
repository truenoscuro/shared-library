package es.tresdigits

class Angular implements Serializable{

    def steps
    Angular(steps) {this.steps = steps}

    build(){ steps.sh "ng build" }

}