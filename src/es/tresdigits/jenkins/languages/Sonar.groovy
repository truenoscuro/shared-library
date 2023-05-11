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
    

    def scanner  = {
        utils.echo "$conf.tool"
        //def tool = utils.tool "${conf.tool}"
        //def path = conf.path ?: "${utils.tool(conf.tool)}/bin/sonar-scanner"
        //utils.echo "${tool}"
        /*
        utils.script.withSonarQubeEnv("${conf.name}"){
           utils.cmd "${tool}/bin/sonar-scanner -Dproject.settings=${conf.properties} ${conf.binaries}"
        }
        */
    }
}   
