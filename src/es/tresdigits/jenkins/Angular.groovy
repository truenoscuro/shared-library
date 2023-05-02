package es.tresdigits.jenkins


class Angular  implements Serializable {

    def step
    def utils


    // init angular
    def init(step,utils){
        step.echo "Init angular..."
        this.step = step
        this.utils = utils
    }

    def install(){
        step.sh "npm install -g @angular/cli"
    }

    def build(){
        step.echo "Build angular"
        step.sh "ng build"
    }

    def iPackage(){
        step.sh "npm i"
    }


    /** 
    PIPELINE
    **/




}