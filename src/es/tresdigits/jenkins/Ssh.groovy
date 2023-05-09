package es.tresdigits.jenkins


class Ssh  implements Serializable {

    def remote
    def credentialsId
    def script
    def utils
    def isSudo 
    def init(script,utils){
        script.echo "init service"
        this.script = script
        this.utils = utils
    }
    //TODO Podria cojer el script o no
    def com = { String c ->  script.sshCommand([remote: this.remote , command: "${c}",sudo: false])}
    def put = { String f,String i ->  script.sshPut([remote: this.remote , from: "${f}",into:"${i}"])}
    def get = { String f,String i ->  script.sshGet([remote: this.remote , from: "${f}",into:"${i}", override: true])}
    def rm = { String p ->  script.sshRemove([remote: remote , path: "${p}"])}
    
    
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
        def sshCom = { com(command) }

        applySsh(sshCom)
       
    }

    def applySsh(Closure command){
        if(credentialsId == null){
            command.call()
        }else{
            command(sshCom)
        }

    }


    def docker(Map conf,String language){
        addRemote(conf)

        Closure sshCom = { 
            put("Dockerfile",".") 
            }

        applySsh(sshCom)
    }


}