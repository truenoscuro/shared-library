//import @Field
import groovy.transform.Field

//import configs
//import clases
import es.tresdigits.jenkins.configs.GlobalConfig
import es.tresdigits.jenkins.configs.SystemConfig



//parameters
@Field boolean isWindows = false
@Field Map git = [url:"urlgit",credentialsId:"credentialsIdT", branch:"default master"]
@Field Map sonar = [name:"",tool:"",pathScanner:"",haveBinaries:true, properties:"sonar-project.properties",binaries:"."]
@Field Map maven =[toolJDK:"",toolMaven:""]

// init global config
@Field GlobalConfig globalConfig
def init(){
    globalConfig = new GlobalConfig(
        [
            systemConfig: new SystemConfig(this.isWindows),
            git: this.git,
            sonar: this.sonar,
            maven: this.maven


        ]
    )
}

