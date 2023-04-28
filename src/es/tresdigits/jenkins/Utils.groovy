package es.tresdigits.jenkins

class Utils  implements Serializable {

    def step
    def env

    
    def init(step,env){
        step.echo "Init Utils ..."
        this.step =step
        this.env = env // ENV se poden afegir posat env.[nom dalgo] = [String]
    }



    def emailSent( toVar, subjectVar, bodyVar=" "){

        step.echo "Sent mail..."
    
        step.emailext  subject: "${subjectVar}" ,body: "${bodyVar}" , to: "${toVar}"
                    
    }








}