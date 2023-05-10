package es.tresdigits.jenkins.languages


class Angular  implements Serializable {

    def script
    def utils


    // init angular
    def init(script,utils){
        script.echo "Init angular..."
        this.script = script
        this.utils = utils
    }

    def angularInstall(){
        script.sh "npm install -g @angular/cli"
    }

    def build(){
        script.echo "Build angular"
        script.sh "ng build"
    }

    def install(){
        script.sh "npm i"
    }


   @Override
    String toString() {
        return "angular"
    }



}