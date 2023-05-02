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

    def imageNode(){
        dk.image("node:latest").inside{
            step.sh "sudo npm -v"  // dins una clase necesita el step si o si  
            step.sh "sudo npm install -g @angular/cli"
            step.sh "sudo ng new my-app"
            
            }
    }






}