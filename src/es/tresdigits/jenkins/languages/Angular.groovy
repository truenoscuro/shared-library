package es.tresdigits.jenkins.languages


class Angular {

    def utils

    Angular( utils ){ this.utils =  utils }


    def version() {  utils.cmd "npm -v" }
    /*
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
    */





}