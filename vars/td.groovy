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

//script
def init( script , env , globalConfig ){
    utils = new Utils(script , env, globalConfig.isWindows)
    switcher = new Switcher( utils , globalConfig)
}

def git ( Map conf = switcher.globalConfig.git ){
    def script = utils.script
    def git = utils.git
    script.stage("git clone de ${conf.url}"){
        git(conf)
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
            stages["${name}"] =  switcher.getFunct(key,funct)
    }
    script.stage("parallel stage"){
         script.parallel(stages).call()
    }
   
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







