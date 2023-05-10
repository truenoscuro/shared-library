package es.tresdigits.jenkins


//Files
import java.io.File

class Utils  implements Serializable {

    def script
    def env
    boolean isWindows
    //git
    String gitUrl
    String credentials
    Utils (script,env, boolean isWindows){
        script.echo "Init utils"
        this.script = script
        this.env = env
        this.isWindows = isWindows
    }


    def cmd = {String arg -> (isWindows)? script.bat "${arg}" : script.sh "${arg}" }
    def echo = { String arg -> script.echo "${arg}" }
    
   /*

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
        String pack = ""
        listFiles("target").each{ file,value ->
            if(file =~ regex) pack = file
        }
        return pack

    }
    */

    








}