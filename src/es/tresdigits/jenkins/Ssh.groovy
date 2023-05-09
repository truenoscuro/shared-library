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
    //TODO Podria cojer el script o no
    def scriptC = { String c ->  script.sshCommand([remote: this.remote , command: "${c}",sudo: false])}
    def put = { String f,String i ->  script.sshCommand([remote: this.remote , from: "${f}",into:"${i}",sudo: false])}
    def get = { String f,String i ->  script.sshCommand([remote: this.remote , from: "${f}",into:"${i}",sudo: false, override: true])}
    def remove = { String p ->  script.sshCommand([remote: remote , path: "${p}",sudo: false])}
    
    
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
                remote.user =  script.userName
                remote.identityFile = script.keyFile 
                body()
        }


    }


    def command(Map conf, String command,boolean isSudo){
        
        //TODO te configuracion --> no afegir
        addRemote(conf)
        def com = {scriptC(command)}
        if(credentialsId == null){
            com.call()
        }else{
            credentials(com)
        }
    }


    def docker(Map conf,String compiler){
        addRemote(conf)
        if( credentialsId == null ){
            script.sshPut remote: this.remote, from:'Dockerfile'
        }
    }


}