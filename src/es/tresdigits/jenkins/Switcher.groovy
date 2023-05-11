package es.tresdigits.jenkins

// languages import
import es.tresdigits.jenkins.languages.Angular
import es.tresdigits.jenkins.languages.Maven
import es.tresdigits.jenkins.languages.Sonar
import es.tresdigits.jenkins.languages.Ssh

//import Biblio
import es.tresdigits.jenkins.biblio.Biblio

class Switcher  implements Serializable {

    def utils
    def globalConfig


    private Map languages

    Switcher( utils ,globalConfig){
        this.utils = utils
        this.globalConfig = globalConfig
        this.languages = [:]
    }
    
    private def angular(){
        if(languages.angular == null){
            languages["angular"] = new Angular( utils )
        }
        return languages.angular
    }

    private def sonar(){
        if(languages.sonar == null){
            languages["sonar"] = new Sonar(utils,globalConfig.sonar)
        }
        return languages.sonar
    }
    
    private def maven(){
        if(languages.maven == null){
            languages["maven"] = new Maven(utils)
        }
        return languages.maven
    }

    private def ssh(){
        if( languages.ssh == null ){
            languages["ssh"] = new Ssh(utils,globalConfig.ssh)
        }
        return languages.ssh
    }

    def getLang(String lang){
        utils.echo "${Biblio.getLang(lang)}"
        return "${Biblio.getLang(lang)}"()
    }


    def getFunct(String lang,String funct){
        if( funct instanceof List ){
            def language = getLang( lang )
            return { funct.each{ f -> 

                language."${f}".call() 
            }}
        }
        if(lang == "custom") return funct

        return getClass( lang )."${funct}"


    }
    


    


}