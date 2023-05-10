package es.tresdigits.jenkins.configs

class SystemConfig  implements Serializable {

    boolean isWindows 

    IsWindows(boolean isWindows = false ){
        this.isWindows = isWindows
    }
}