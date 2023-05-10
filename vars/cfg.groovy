//import @Field
import groovy.transform.Field

//import configs
//import clases
import es.tresdigits.jenkins.configs.GlobalConfig




//parameters
@Field boolean isWindows = true



@Field GlobalConfig globalConfig = new GlobalConfig(
    [
        isWindows: this.isWindows


    ]
)