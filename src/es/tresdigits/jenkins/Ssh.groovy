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
    def com = { String c,boolean isSudo=false ->  script.sshCommand([remote: this.remote , command: "${c}",sudo: isSudo])}
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

    def applySsh(Closure command){
        if(credentialsId == null){
            command.call()
        }else{
            credentials(command)
        }

    }


    def command(Map conf, String command,boolean isSudo){
        
        //TODO te configuracion --> no afegir
        addRemote(conf)
        def sshCom = { com(command) }

        applySsh(sshCom)
       
    }

    


    //TODO args docker 
    def docker(Map conf,String language, boolean isSudo){
        addRemote(conf)

        String[] listFiles = utils.listFiles("target")
        String pack = "./target/${utils.findJarWar()}"
        String path ="./docker"
        def sshCom = {
            com("mkdir ${path}")
            put("Dockerfile","${path}")  // esto 
            com("mkdir ${path}/target")
            put( pack ,"${path}/target") // esto cambia
            //com("docker build ${path}",isSudo)
            //com("docker run  -p 8080:8080",isSudo) // esto cambia
        }

        applySsh( sshCom )
    }


}