// hi ha un paquet que te fa el sonar no fa falta utilitzarlo

package es.tresdigits.jenkins
class Sonar  implements Serializable {

    def script
    def utils
    //TODO cambiar nombre 
    def name
    def scannerTool

    def init( script,name,utils, name,nameTool){
        script.echo "Init Sonar..."
        this.script = script 
        this.name= name
        this.utils = utils
        this.scannerTool = script.tool "${nameTool}"

        
    }
    def setSonarName(name){
        if(name.length() > 0) this.name = name
        
    }
    def scannerTool(scannerTool){
        if(scannerTool.length() > 0) this.scannerTool = scannerTool
    }


    def scannerBinaries = {
        script.withSonarQubeEnv("${name}"){
           sh "${scannerTool}/bin/sonar-scanner -Dproject.settings=sonar-project.properties -Dsonar.java.binaries=."
        }
    }

    @Override
    String toString() {
        return "sonar"
    }




}
