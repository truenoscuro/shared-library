package es.tresdigits.jenkins


class Maven  implements Serializable {

    def script
    def utils


    // init angular
    def init(script,utils){
        script.echo "Init Spring..."
        this.script = script
        this.utils = utils
    }

    def package(){
        script.sh "mvn clean package"
    }
    def  install(){
        script.sh "mvn clean install"
    }

    def compile(){
        script.sh "mvn clean compile"
    }
    

    @Override
    String toString() {
        return "maven"
    }



}
