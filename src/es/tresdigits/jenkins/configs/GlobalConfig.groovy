package es.tresdigits.jenkins.configs

class GlobalConfig  implements Serializable {

    boolean isWindows
    Map git
    Map sonar
    GlobalConfig(Map conf){
        isWindows = conf.isWindows
        git = conf.git
        confSonar(conf)
        
    }

    private def confSonar(Map conf){
        sonar = conf.sonar
        if(sonar.binaries == null) {
            sonar["haveBinaries"] = true
            sonar["binaries"] = "."
        }
        if(sonar.properties == null )
            sonar["properties"] = "sonar-project.properties"

    }


}