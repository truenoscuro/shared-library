// hi ha un paquet que te fa el sonar no fa falta utilitzarlo

package es.tresdigits.jenkins.languages
class Sonar  {

    
    //TODO cambiar nombre 
    def utils
    def conf



    Sonar( utils , conf ){ 
        this.utils = utils
        this.conf = conf     
    }
    

    def scanner  = {
        String path = conf.path ?: utils.tool(conf.tool)
        String binaries = (conf.haveBinaries)? "${conf.binaries}":"."
        utils.echo "${conf.name}"
        utils.script.withSonarQubeEnv("${conf.name}"){
           utils.cmd "${path}/bin/sonar-scanner -Dproject.settings=${conf.properties} -Dsonar.java.binaries=${binaries}"
        }.call()
    }
   

}
