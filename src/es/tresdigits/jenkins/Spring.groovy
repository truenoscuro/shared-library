package es.tresdigits.jenkins


class Spring  implements Serializable {

    def step
    def utils


    // init angular
    def init(step,utils){
        step.echo "Init Spring..."
        this.step = step
        this.utils = utils
    }

    def build(){
        step.sh "mvn package"
    }

}
