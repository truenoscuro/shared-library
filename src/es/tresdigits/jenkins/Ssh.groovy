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

    def switchLanguage(String language){
        def sshCom = { script.error "Has escrito mal el language"}
        String name = "docker-${language}"
        String path ="./${name}"
        //TODO cmabiar por una lista 
        switch(language) {
            case "maven":
                String[] listFiles = utils.listFiles("target")
                String pack = "./target/${utils.findJarWar()}"
                sshCom = {
                    //com("rm -fr ${path}",isSudo) // eliminacion de carpeta
                    com("mkdir ${path}")
                    put("Dockerfile","${path}")  
                    com("mkdir ${path}/target")
                    put( pack ,"${path}/target") 
                    com("docker build ${path} -t ${name} ",isSudo)
                    com("docker run ${name} -p 8080:8080",isSudo) // esto cambia
                }
            break
            case "angular":
                sshCom = {
                    com("rm -fr ${path}") // eliminacion de carpeta
                    com("mkdir ${path}")
                    script.sh "zip -r dist.zip dist"
                    put("dist.zip",path)
                    put("Dockerfile",path)
                    script.sh "rm dist.zip" //TODO continaur dema qui
                    com("unzip ${path}/dist.zip ${path}")
                    //com("docker build ${path} -t ${name}",isSudo)
                    //com("docker run ${name} -p 80:6000",isSudo)   
                }
            break
        }

        return sshCom
    }

    


    //TODO args docker 
    def docker( Map conf , String language ,  boolean isSudo){
        addRemote(conf)

            applySsh( switchLanguage( language ) )
        try{
            // applySsh( switchLanguage( language ) )
        }catch(Exception ex){
            script.error "Ha habido un error en la ejecucion del docker"

        }
       
    }


}