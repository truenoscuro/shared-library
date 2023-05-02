package es.tresdigits.jenkins
@Grab(group='org.sonarsource.scanner.maven', module='sonar-maven-plugin', version='3.9.1.2184')

class Sonar  implements Serializable {

    def step
    def env
   

    def init( step,env,sonar ){
        step.echo "Init Sonar..."
        this.step = step
        this.env = env
        
    }





}
