// hi ha un paquet que te fa el sonar no fa falta utilitzarlo

package es.tresdigits.jenkins
class Sonar  implements Serializable {

    def script
    def env
   

    def init( script,env ){
        script.echo "Init Sonar..."
        this.script = script
        this.env = env
        
    }

    def sonar(){
        script.withSonarQubeEnv("Sonar "){
            // lo que hagui de sonar
        }
    }




}
