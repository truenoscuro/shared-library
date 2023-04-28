package es.tresdigits

class Utils  implements Serializable {

    def step
    def env


    def init(step,env){
        step.echo "Init utils ..."
        this.step =step
        this.env = env    
    }

    



}