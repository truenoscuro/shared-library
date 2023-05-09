package es.tresdigits.jenkins


class Ssh  implements Serializable {

    def remote;
    def script;
    def utils;

    def init(script,utils){
        this.script = script
        this.utils = utils
    }


    def addRemote(Map conf){

        remote.name = (conf?.name != null )? conf.name : "server" 
        remote.host = conf?.host 
        remote.allowAnyHosts = true

        if(cont?.credentialId != null){
            def credential = script.withCredentials(
                [sshUserPrivateKey(
                    credentialsId: '${cont.credentialId}', 
                    keyFileVariable: 'keyFile',
                    passphraseVariable: '', 
                    usernameVariable: 'userName'
                )])
            remote.user = credential.userName
            remote.identityFile = credential.keyFile

        }else{

            remote.user = conf?.user
            remote.password = conf?.password
            
        }

    }





}