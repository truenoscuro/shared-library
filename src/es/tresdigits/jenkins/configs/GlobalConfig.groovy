package es.tresdigits.jenkins.configs

class GlobalConfig  implements Serializable {

    boolean isWindows
    Map git
    Map sonar
    Map maven
    GlobalConfig(Map conf){
        //windows
        isWindows = conf.isWindows
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