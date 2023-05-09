package es.tresdigits.jenkins


class Ssh  implements Serializable {

    def remote
    def credentialsId
    def script
    def utils

    def init(script,utils){
        script.echo "init service"
        this.script = script
        this.utils = utils
    }


    def addRemote(Map conf){
        remote = [:]
        remote.name =  "server" ?: conf.name 
        remote.host = conf?.host 
        remote.allowAnyHosts = true
        credentialsId = conf?.credentialsId 
        if(credentialsId == null){
            remote.user = conf?.user
            remote.password = conf?.password
        }

    }

    def credentials(Closure body){
         script.withCredentials([script.sshUserPrivateKey(
                credentialsId: "${credentialsId}", 
                keyFileVariable: 'keyFile',
                passphraseVariable: '', 
                usernameVariable: 'userName')]) {
                body
        }


    }

    def command(Map conf, String command,boolean isSudo){
        
        //TODO te configuracion --> no afegir
        addRemote(conf)
        
        if(credentialsId == null){
            script.sshCommand remote: this.remote, command: command
        }else{
            credentials({
            remote.user =  script.userName
            remote.identityFile = script.keyFile 
            script.sshCommand remote: remote , command: "${command}", sudo: isSudo
            })
        }
    }


    def docker(Map conf,String compiler){


    }


}