package es.tresdigits.jenkins


//Files
import java.io.File

//utils va lo que se utiliza en varios lados
class Utils  implements Serializable {

    def script
    def env
    def systemConfig

    Utils (script,env,systemConfig){
        this.script = script
        this.env = env
        this.systemConfig = systemConfig
    }

    def echo = {String arg -> script.echo(arg)}
    def cmd = { String arg -> (systemConfig.isWindows)? script.bat(arg) : script.sh(arg) }
    def git = { Map conf  -> script.git(conf) }
    def tool = { String name -> script.tool(name)}
    def docker = { String tag-> script.docker(tag)}


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