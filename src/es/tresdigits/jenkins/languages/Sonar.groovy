// hi ha un paquet que te fa el sonar no fa falta utilitzarlo

package es.tresdigits.jenkins.languages
class Sonar  {

    
    //TODO cambiar nombre 
    def utils
    def conf



    Sonar( utils , conf ){ 
        this.utils = utils
        this.conf = [:]
        if(conf.path == null ) this.conf["tool"] = conf.tool
        else this.conf["path"] = conf.path
        this.conf["name"] = conf.name
        this.conf["properties"] = conf.properties
        if(conf.haveBinaries) this.conf["binaries"] = "-Dsonar.java.binaries=${conf.binaries}"
    }
    

    def scanner() {
        def path = ""
        if(conf.tool){
            def sonar = utils.tool "${conf.tool}"
            path = "${sonar}/bin/sonar-scanner"
        }
        else path = conf.path
        
        utils.echo "${path}"
        utils.script.withSonarQubeEnv("${conf.name}"){
           utils.cmd "${path} -Dproject.settings=${conf.properties} ${conf.binaries}"
        }
    
    }
}   
