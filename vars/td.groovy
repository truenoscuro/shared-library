package vars

//import @Field
import groovy.transform.Field

//import clases
import es.tresdigits.jenkins.Utils
import es.tresdigits.jenkins.Switcher
//Aqui nomes ha daver ses funcions de dins node 
// Field de clases
@Field Utils utils;
@Field Switcher switcher

//init + git init
def init( script , env , globalConfig ){
    utils = new Utils(script , env, globalConfig.isWindows)
    switcher = new Switcher( utils , globalConfig)


    //git 
    script.stage("git clone de ${utils.getDirectoryGit( globalConfig.git.url )}"){
        script.git(globalConfig.git)
    }
}


def stage(Map jobs){
    def script = utils.script
    String name = jobs.name ?: "stage"
    
    script.stage(name){
        jobs.each{ String key,funct ->
            if(key !="name") switcher.getFunct(key,funct).call()
        }
    }
}

def parallel( Map jobs ){
    def script = utils.script
    def stages = [:]
    jobs.each{
        key,funct ->
            String name = "${key}"
            if(funct instanceof String ) name = "${name}-${funct}"
            stages["${name}"] =  {
                script.stage("${name}"){
                    switcher.getFunct(key,funct).call()
                }
            }
    }
   
    script.parallel(stages)
    
   
    
   
  
   
}
def docker(Map conf){ 
    
    if( conf.isAngular == null ) conf["isAngular"] = true
    if( conf.arg == null ) conf["arg"] = ""
    boolean system = utils.isWindows
    utils.isLinux()
    utils.image(conf.tag).inside(conf.arg){
        if(conf.tag == "node" && conf.isAngular )
            switcher.getFunct("angular","iAngular").call()
        conf.com.each{ lang ,funct ->
            switcher.getFunct( lang , funct ).call()
        }
    }
    utils.isWindows = system
}

def sshDocker( Map conf ){
    def ssh = switcher.getLang("ssh")
    ssh.docker(conf)

}

def maven(){
    def script = utils.script
    def cmd = script.cmd


    script.withMaven{
        cmd "mvn -v"
    }


}







