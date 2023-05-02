package es.tresdigits.jenkins


class DockerCustom  implements Serializable {

    def step
    def dk
    def utils

    //init docker
    def init(step,docker,utils){
        step.echo "Init Docker..."
        this.dk  = docker
        this.utils = utils
        this.step = step  // aquest no es necesari en principi
    }

    def npmV(){
        dk.image("node").inside{
            sh "npm -v"
        }
    }






}