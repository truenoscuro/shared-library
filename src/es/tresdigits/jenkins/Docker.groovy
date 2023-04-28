package es.tresdigits.jenkins

class Docker implements Serializable {
    
    def step
    def utils


    // init angular
    def init(step,utils){
        step.echo "Init docker..."

        this.step = step
        this.utils = utils
    }


    

}
