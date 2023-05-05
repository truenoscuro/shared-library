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

    def build(){
        script.sh "mvn package"
    }

    def compile(){
        return sh "mvn clean compile"
    }

    @Override
    String toString() {
        return "maven"
    }



}
