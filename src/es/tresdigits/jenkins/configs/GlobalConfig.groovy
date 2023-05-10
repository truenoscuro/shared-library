package es.tresdigits.jenkins.configs

class GlobalConfig  implements Serializable {

    SystemConfig systemConfig
    Map git
    Map sonar
    Map maven
    
    
    GlobalConfig(Map conf){
        // system config
        systemConfig = conf.systemConfig
        //-- git
        git = conf.git
        //-- sonar config
        sonar = conf.sonar
        if(sonar.haveBinaries == null) {
        sonar["haveBinaries"] = true
        sonar["binaries"] = "."
        }
        if(sonar.properties == null )
            sonar["properties"] = "sonar-project.properties"
        //-- maven
        maven = conf.maven
    }

    


}