package es.tresdigits.jenkins

class Utils  implements Serializable {

    def step
    def env

    def init(step,env){
        step.echo "Init Utils ..."
        this.step =step
        this.env = env
        this.env.TOMATIGA = "tomatiga verda"
        step.echo "Enviaroment: ${this.env.TOMATIGA}"

    }





}