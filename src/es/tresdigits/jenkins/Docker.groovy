package es.tresdigits.jenkins


/*
Aquesta clase nomes retorna ses images contruides per ara no utiltiza dockerFiles pero se podrien fer 
i en vers d'utilitzar la funcio image seria build
*/

//TODO per culpa del jenkinsDocker fa falta posar --user root a tot
class Docker  implements Serializable {

    def script
    def docker
    def utils

    //init docker
    def init(script,utils){
        script.echo "Init Docker..."
        this.docker  = script.docker
        this.utils = utils
        this.script = script  // aquest no es necesari en principi
    }

    //TODO dins utils pasa totes les versions dels dockers i fer utils.getNodeVersion()

    //Imagenes Docker
    def image(nameImage){ 
        script.echo "Image ${nameImage} creada"
        return docker.image( "${nameImage}" ) 
    
    }

    //Run dockerfile create

    def remove( ){
        try{
            script.sh """
            docker stop $ (docker ps -a -q)
            docker rm $ (docker ps -a -q)
            """
        }catch(Exception ex){
            script.echo "primera vez en ejecucion"
        }
    }

    def run(String imageName,String dockerfile,String path,String args =""){

        def image = docker.build(imageName,"-f ${dockerfile} ${path}" )
        def container = image.run( args )
        //TODO slep quitar
        /*
        def totalMin = 1
        script.echo "sleep de ${totalMin} min"
        utils.sleepMin(  totalMin )
        container.stop()
        */

    }
    
    

    
    






}