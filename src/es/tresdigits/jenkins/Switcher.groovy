package es.tresdigits.jenkins

// languages import
import es.tresdigits.jenkins.languages.Angular


class Switcher  implements Serializable {

    def utils


    private Map languages

    Switcher( utils ){
        utils.script.echo "init switcher"
        this.utils = utils
        this.languages = [:]
    }
    
    def Angular angular(){
        if(languages.angular == null){
            languages["angular"] = new Angular( utils )
        }
        return languages.angular
    }

    def searchFunct = {language,funct ->
        if(language == "custom"){
            utils.echo "custom "
            return funct
        }else{
            def lang = "${language}"()
            return lang."${funct}"()
        }
    }
    


    


}