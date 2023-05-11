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
            languages["maven"] = new Maven(utils,globalConfig.maven)
        }
        return languages.maven
    }

    private def ssh(){
        if( languages.ssh == null ){
            languages["ssh"] = new Ssh(utils,globalConfig.ssh)
        }
        return languages.ssh
    }

    def error ={ utils.error "ha habido un error "}



    //get
    def getLang(String lang){
        def langBiblio = Biblio.getLang(lang)
        if( langBiblio == "custom" ) return langBiblio
        return "${langBiblio}"()
    }


    def getFunct(String lang,funct){
        
        def langBiblio = getLang( lang )


        //caso custom
        if( langBiblio instanceof String ) return funct
        //list funct
        if( funct instanceof List ){
            return { funct.each{ f -> 
                langBiblio."${Biblio.getFunct(f)}" 
            }
            }
        }

        //no list
   
        return { langBiblio."${Biblio.getFunct(funct)}"() }


    }
    


    


}