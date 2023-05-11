package es.tresdigits.jenkins.configs

class GlobalConfig  implements Serializable {
    boolean isWindows
    Map git
    Map sonar
    Map ssh
    GlobalConfig(Map conf){
        
        //system pipelina
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

        
        //ssh
        ssh = conf.ssh
        if(ssh.name == null) ssh["name"] = "server"
        if(ssh.host == null ) ssh["host"] = "localhost"
        if(ssh.allowAnyHosts  == null ) ssh["allowAnyHosts"] = true
        if(ssh.credentialsId == null){
            if(ssh.user == null ) ssh["user"] = "root"
            if(ssh.password == null ) ssh["password"] = "root"
        }

        //maven
        maven = conf.maven        
    }

    


}