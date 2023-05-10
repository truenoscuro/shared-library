package es.tresdigits.jenkins.languages


class Angular  {

    def utils

    Angular( utils ){ 
        utils.echo "Init angular "
        this.utils =  utils 
    }

    def imprimir(){
        "som el angular"
    }


    def version =  {  utils.cmd "npm -v" }
    def nodeVersion =  {  utils.cmd "node -v" }
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