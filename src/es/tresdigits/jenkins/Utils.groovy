package es.tresdigits.jenkins


//Files
import java.io.File

//utils va lo que se utiliza en varios lados
class Utils  implements Serializable {

    def script
    def env
    boolean isWindows

    Utils (script,env,isWindows){
        this.script = script
        this.env = env
        this.isWindows = isWindows
    }

    def echo = {String arg -> script.echo(arg)} 
    def error = {String arg -> script.error(arg)} 
    def cmd = { String arg -> (isWindows)? script.bat(arg) : script.sh(arg) }
    def git = { Map conf  -> script.git(conf) }
    def tool = { String name -> script.tool(name)}
    def image = {String tag -> script.docker.image(tag) }


    def isLinux(){ isWindows = false }
    def isWindows(){ isWindows = true }
   /*

    def emailSent( toVar, subjectVar, bodyVar=" "){

        script.echo "Sent mail..."
    
        script.emailext  subject: "${subjectVar}" ,body: "${bodyVar}" , to: "${toVar}"
                    
    }
*/

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
 

    








}