package es.tresdigits.jenkins

class Utils  implements Serializable {

    def step

    def init(step){
        step.echo "Init Utils ..."
        this.step =step

    }





}