package es.tresdigits.jenkins

class Switcher  implements Serializable {

    def utils

    Switcher( utils ){
        utils.script.echo "init switcher"
        this.utils = utils
    }


}