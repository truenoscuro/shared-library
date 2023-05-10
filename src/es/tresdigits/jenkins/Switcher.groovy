package es.tresdigits.jenkins

// languages import
import es.tresdigits.jenkins.languages.Angular
import es.tresdigits.jenkins.languages.Maven
import es.tresdigits.jenkins.languages.Sonar


class Switcher  implements Serializable {

    def utils
    def globalConfig


    private Map languages

    Switcher( utils ,globalConfig){
        this.utils = utils
        this.globalConfig = globalConfig
        this.languages = [:]
    }
    
    def angular(){
        if(languages.angular == null){
            languages["angular"] = new Angular( utils )
        }
        return languages.angular
    }

    def sonar(){
        if(languages.sonar == null){
            languages["sonar"] = new Sonar(utils,conf.sonar)
        }
        return languages.sonar
    }
    
    def maven(){
        if(languages.maven == null){
            languages["maven"] = new Maven(utils,globalConfig.maven)
        }
        return languages.maven
    }


    def returnClosureFunt(lang,funct){
        if(funct instanceof List){
            return { funct.each{ f -> "${lang}"()."${f}".call() } }
        }

        return (lang =="custom")? funct:"${lang}"()."${funct}"


    }
    


    


}