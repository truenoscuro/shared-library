// hi ha un paquet que te fa el sonar no fa falta utilitzarlo

package es.tresdigits.jenkins.languages
class Sonar  {

    
    //TODO cambiar nombre 
    def utils
    def conf



    Sonar( utils , conf ){ 
        this.utils = utils
        this.conf = [:]
        this.conf["path"] = conf.path ?: conf.tool
        utils.echo "${ utils.tool(conf.path) }"
        this.conf["name"] = conf.name
        this.conf["properties"] = conf.properties
        if(conf.haveBinaries) this.conf["binaries"] = "-Dsonar.java.binaries=${conf.binaries}"
    }
    

    def scanner  = {
        
        /*
        String path = conf.path ?: "${utils.tool(conf.path)}/bin/sonar-scanner"
        utils.script.withSonarQubeEnv("${conf.name}"){
           utils.cmd "${path} -Dproject.settings=${conf.properties} ${conf.binaries}"
        }
        */
    }
}   
