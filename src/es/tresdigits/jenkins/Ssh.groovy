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
        echo "${conf?.name}"
        remote.name = (conf?.name != null )? conf.name : "server" 
        remote.host = conf?.host 
        remote.allowAnyHosts = true
        credentialsId = cont?.credentialsId 
        if(credentialsId == null){
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
                credentialsId: "${credentialsId}", 
                keyFileVariable: 'keyFile',
                passphraseVariable: '', 
                usernameVariable: 'userName')]) {
            remote.user =  userName
            remote.identityFile = keyFile 
            sshCommand remote: remote , command: "${command}", sudo: isSudo
            }
        }
    }


}