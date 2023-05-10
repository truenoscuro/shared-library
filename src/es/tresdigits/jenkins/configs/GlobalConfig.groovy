package es.tresdigits.jenkins.configs

class GlobalConfig  implements Serializable {

    boolean isWindows
    Map git
    Map sonar
    GlobalConfig(Map conf){
        isWindows = conf.isWindows
        git = conf.git
        sonar = conf.sonar
        if(sonar.haveBinaries == null) {
        sonar["haveBinaries"] = true
        sonar["binaries"] = "."
        }
        if(sonar.properties == null )
            sonar["properties"] = "sonar-project.properties"
    
    }

    def confSonar(){
        if(sonar.haveBinaries == null) {
            sonar["haveBinaries"] = true
            sonar["binaries"] = "."
        }
        if(sonar.properties == null )
            sonar["properties"] = "sonar-project.properties"

    }


}