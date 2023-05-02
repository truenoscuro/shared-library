package es.tresdigits.jenkins


class Angular  implements Serializable {

    def script
    def utils


    // init angular
    def init(script,utils){
        script.echo "Init angular..."
        this.script = script
        this.utils = utils
    }

    def install(){
        script.sh "npm install -g @angular/cli"
    }

    def build(){
        script.echo "Build angular"
        script.sh "ng build"
    }

    def iPackage(){
        script.sh "npm i"
    }


    /** 
    PIPELINE
    **/




}