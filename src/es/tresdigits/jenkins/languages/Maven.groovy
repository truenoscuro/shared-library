package es.tresdigits.jenkins.languages


class Maven {

    def utils
    def conf

    Maven( utils ,conf){
        this.utils = utils 
        this.conf = conf 
    }
    
      
    
    def mvnV = {
        utils.script.withMaven{
            utils.cmd "mvn -v"
        }
    } 
       
    


    /*
    //def mvnV = withMaven(utils.cmd "mvn -v")
    def pack =  withMaven(  { utils.cmd "mvn clean package" } )
        
    def install = { utils.cmd "mvn clean install" }
    def compile = { utils.cmd "mvn clean compile" }
    */    




}
