// hi ha un paquet que te fa el sonar no fa falta utilitzarlo

package es.tresdigits.jenkins
class Sonar  implements Serializable {

    def step
    def env
   

    def init( step,env ){
        step.echo "Init Sonar..."
        this.step = step
        this.env = env
        
    }
    def test(){

    }





}
