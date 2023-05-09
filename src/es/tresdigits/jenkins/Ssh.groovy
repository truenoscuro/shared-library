package es.tresdigits.jenkins


class Ssh  implements Serializable {

    def remote
    def credentialId
    def script
    def utils

    def init(script,utils){
        this.script = script
        this.utils = utils
    }


    def addRemote(Map conf){

        remote.name = (conf?.name != null )? conf.name : "server" 
        remote.host = conf?.host 
        remote.allowAnyHosts = true

        if(cont?.credentialId != null){
           credentialId = cont.credentialId
        }else{
            credentialId = null
            remote.user = conf?.user
            remote.password = conf?.password
        }

    }

    def command(Map conf, String command,boolean isSudo){
        addRemote(conf)

        if(credentialId == null){
            script.sshCommand remote: this.remote, command: command
        }else{
            withCredentials([sshUserPrivateKey(
                credentialsId: 'server-docker', 
                keyFileVariable: 'keyFile',
                passphraseVariable: '', 
                usernameVariable: 'userName')]) {
            remote.user =  userName
            remote.identityFile = keyFile 
            sshCommand remote: remote , command: 'command',sudo: isSudo
            }
        }
    }


}