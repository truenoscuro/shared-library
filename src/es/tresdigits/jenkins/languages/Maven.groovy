package es.tresdigits.jenkins.languages


class Maven {

    def utils
    def conf
    Maven( utils,conf ){  
        this.utils = utils  
        this.conf = conf    
    }
    def withMaven( Closure body){
        def script = utils.script
        if(conf == null){
            script.withMaven{
                body()
            }
        }else{
            script.withMaven(conf){
                body()
            }
        }
    }
    def pack() { withMaven({ utils.cmd "mvn clean package" })}
    def install() { withMaven({utils.cmd "mvn clean install"}) }
    def compile () { withMaven({utils.cmd "mvn clean compile"}) }
    




}
