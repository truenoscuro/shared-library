package es.tresdigits.jenkins

/*
Aquesta clase nomes retorna ses images contruides per ara no utiltiza dockerFiles pero se podrien fer 
i en vers d'utilitzar la funcio image seria build
*/

//TODO per culpa del jenkinsDocker fa falta posar --user root a tot
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

    //TODO dins utils pasa totes les versions dels dockers i fer utils.getNodeVersion()

    //Imagenes Docker
    def imageNode(){ 
        step.echo "Image node creada"
        return dk.image("node:latest") 
    
    }






}