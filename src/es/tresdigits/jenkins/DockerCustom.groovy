package es.tresdigits.jenkins

class DockerCustom implements Serializable {
    
    def step
    def utils
    def docker

    // init docker
    def init(step,utils,docker){
        step.echo "Init docker..."

        this.step = step
        this.utils = utils
        this.docker = docker
    }

    def build(){ 
        
        docker.image('node').inside
        
    }




}
