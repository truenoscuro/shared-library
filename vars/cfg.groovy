//import @Field
import groovy.transform.Field

//import configs
//import clases
import es.tresdigits.jenkins.configs.GlobalConfig




//parameters
@Field boolean isWindows = false


// init global config
@Field GlobalConfig globalConfig
def init(){
    globalConfig = new GlobalConfig(
        [
            isWindows: this.isWindows


        ]
    )
}

