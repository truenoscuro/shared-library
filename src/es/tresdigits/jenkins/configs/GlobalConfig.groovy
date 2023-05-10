package es.tresdigits.jenkins.configs

class GlobalConfig  implements Serializable {

    boolean isWindows
    Map git
    GlobalConfig(Map conf){
        isWindows = conf.isWindows
        git = conf.git
    }


}