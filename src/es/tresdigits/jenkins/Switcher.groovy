package es.tresdigits.jenkins

// languages import
import es.tresdigits.jenkins.languages.Angular


class Switcher  implements Serializable {

    def utils


    Map languages

    Switcher( utils ){
        utils.script.echo "init switcher"
        this.utils = utils
        this.languages = [:]
    }
    
    def angular(){
        if(languages.angular == null){
            languages["angular"] = new Angular( utils )
        }
        utils.echo "${languages.angular.imprimir()}"
        return languages.angular 
    }


    


}