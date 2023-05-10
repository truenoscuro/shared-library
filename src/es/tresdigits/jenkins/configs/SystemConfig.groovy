package es.tresdigits.jenkins.configs

class SystemConfig  implements Serializable {

    boolean isWindows 

    SystemConfig(boolean isWindows = false ){
        this.isWindows = isWindows
    }
}