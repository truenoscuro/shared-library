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

    //TODO per culpa del jenkinsDocker fa falta posar --user root a tot
    
    //TODO dins utils pasa totes les versions dels dockers i fer utils.getNodeVersion()

    //Imagenes Docker
    def imageNode(){ return dk.image("node:latest") }






}