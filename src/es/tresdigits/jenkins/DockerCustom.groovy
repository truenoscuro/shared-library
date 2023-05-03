package es.tresdigits.jenkins

import es.tresdigits.jenkins.utils.DockerFile
/*
Aquesta clase nomes retorna ses images contruides per ara no utiltiza dockerFiles pero se podrien fer 
i en vers d'utilitzar la funcio image seria build
*/

//TODO per culpa del jenkinsDocker fa falta posar --user root a tot
class DockerCustom  implements Serializable {

    def script
    def dk
    def utils

    //init docker
    def init(script,docker,utils){
        script.echo "Init Docker..."
        this.dk  = docker
        this.utils = utils
        this.script = script  // aquest no es necesari en principi
    }

    //TODO dins utils pasa totes les versions dels dockers i fer utils.getNodeVersion()

    //Imagenes Docker
    def image(nameImage){ 
        script.echo "Image ${nameImage} creada"
        return dk.image( "${nameImage}" ) 
    
    }
    
    def generate(String image){
        String workspace = script.WORKSPACE
        script.echo "${workspace}"
        script.echo "Generate Dockerfile"
        DockerFile.generate(workspace,image)

    }
    def runDockerFile(String args =""){
        def imgDockerFile = dk.build("./DockerFile")
        def container = imgDockeFile.run(args)

        
    }

    
    






}