package es.tresdigits.jenkins.languages


class Maven {

    def utils

    Maven( utils ){  this.utils = utils  }
    
    def pack = { utils.cmd "mvn clean package"}
    def install = { utils.cmd "mvn clean install" }
    def compile = { utils.cmd "mvn clean compile" }
    




}
