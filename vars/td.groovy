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
def init( script , env , globalConfig ,systemConfig){
    utils = new Utils(script , env, systemConfig)
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
        jobs.each{ key,funct ->
            if(key !="name") switcher.returnClosureFunt(key,funct).call()
        }
    }
}

def parallel(Map jobs ){
    def script = utils.script
    def stages = [:]
    jobs.each{
        key,funct ->
            String name = "${key}"
            if(funct instanceof String ) name = "${name}-${funct}"
            stages["${name}"] =  switcher.returnClosureFunt(key,funct)
            //stages["${name}"] = switchFunction(key,funct)
    }
    script.parallel(stages)
}
def docker(Map conf){ 
    if(conf.arg == null) conf["arg"] = ""
    utils.image(conf.tag).inside(conf.arg){
        //if(conf.tag == "node")
            //switcher.returnClosureFunt("angular","iAngular").call()
        conf.com.each{ lang ,funct ->
            switcher.returnClosureFunt(lang,funct).call()
        }
    }
}





