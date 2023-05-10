package es.tresdigits.jenkins.configs

class GlobalConfig  implements Serializable {

    boolean isWindows

    GlobalConfig(Map conf){
        
        isWindows = conf.isWindows ?: false
    }


}