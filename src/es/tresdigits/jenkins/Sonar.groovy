package es.tresdigits.jenkins
class Sonar  implements Serializable {

    def step
    def env
    def sonar

    def init( step,env,sonar ){
        step.echo "Init Sonar..."
        this.step = step
        this.env = env
        this.sonar = sonar

    }





}
