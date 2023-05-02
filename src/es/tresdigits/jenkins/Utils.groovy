package es.tresdigits.jenkins

class Utils  implements Serializable {

    def step
    def env

    //git
    String gitUrl
    String credentials

    
    def init( step , env ){
        step.echo "Init Utils ..."
        this.step =step
        this.env = env // ENV se poden afegir posat env.[nom dalgo] = [String]
    }

    def initGit( gitUrl , credentials="" ){
        this.gitUrl = gitUrl
        this.credentials = credentials
    }

   // def setGitUrl( gitUrl ){ this.gitUrl = gitUrl }
    
    def git(){
        //TODO credentials se treura del java 
        if(credentials.length() == 0)
            step.git url: "${gitUrl}"
        else
            step.git credentialsId: "${credentials}", withSonarQubeEnvurl: "${gitUrl}"
    }



    def emailSent( toVar, subjectVar, bodyVar=" "){

        step.echo "Sent mail..."
    
        step.emailext  subject: "${subjectVar}" ,body: "${bodyVar}" , to: "${toVar}"
                    
    }








}