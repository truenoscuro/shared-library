package es.tresdigits.jenkins


//Files
import java.io.File

class Utils  implements Serializable {

    def script
    def env

    //git
    String gitUrl
    String credentials
    
    def init( script , env ){
        script.echo "Init Utils ..."
        this.script =script
        this.env = env // ENV se poden afegir posat env.[nom dalgo] = [String]
    }
    //TODO sleep
    def sleepMin(int totalMin){
        int min = 1000*60
        sleep(min*totalMin)
    }

    def initGit( gitUrl , credentials ){
        this.gitUrl = gitUrl
        this.credentials = credentials
    }

   // def setGitUrl( gitUrl ){ this.gitUrl = gitUrl }
    /*
    heckout scmGit(
    branches: [[name: 'master']],
    extensions: [ lfs() ],
    userRemoteConfigs: [[credentialsId: 'my-username-password-id, url: 'https://github.com/jenkinsci/git-plugin.git']])
    */




    def git(){
        //TODO credentials se treura del java //branch:
        if(credentials.length() == 0)
            script.git url: "${gitUrl}"
        else
            script.git credentialsId: "${credentials}", withSonarQubeEnvurl: "${gitUrl}"
    }

  



    def emailSent( toVar, subjectVar, bodyVar=" "){

        script.echo "Sent mail..."
    
        script.emailext  subject: "${subjectVar}" ,body: "${bodyVar}" , to: "${toVar}"
                    
    }

    def listFiles(String relativePath ="."){

        new File("${script.WORKSPACE}/${relativePath}").list()

    }

    def findJarWar(){
        def regex = /.+\.(jar|war)$/
        def package = ""
        listFiles("target").each{ file,value ->
            if(file =~ regex) package = file
        }
        return package

    }









}