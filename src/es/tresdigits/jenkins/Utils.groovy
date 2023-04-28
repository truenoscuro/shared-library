package es.tresdigits.jenkins

class Utils  implements Serializable {

    def step
    def env

    def init(step,env){
        step.echo "Init Utils ..."
        this.step =step
        this.env = env

        step.echo "Enviaroment: ${env}"

    }





}