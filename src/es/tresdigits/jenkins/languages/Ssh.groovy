package es.tresdigits.jenkins.languages


class Ssh  implements Serializable {

    
    def utils 
    def credentialsId
    def remote
    
    Ssh (utils,conf){
        this.utils = utils
        this.remote =[:]
        this.remote["name"] = conf.name
        this,remote["host"] = conf.host
        this.remote["allowAnyHost"] = conf.allowAnyHost
        this.credentialsId = conf?.credentialsId
        if(conf.credentialsId == null){
            this.remote["user"] = conf.user
            this.remote["password"] = conf.admin
        }
    }
    //TODO Podria cojer el script o no
    def com = { String c,boolean isSudo=false ->  utils.script.sshCommand([remote: this.remote , command: "${c}",sudo: isSudo])}
    def put = { String f,String i ->  utils.script.sshPut([remote: this.remote , from: "${f}",into:"${i}"])}
    def get = { String f,String i ->  utils.script.sshGet([remote: this.remote , from: "${f}",into:"${i}", override: true])}
    def rm = { String p ->  utils.script.sshRemove([remote: this.remote , path: "${p}"]) }
    
    
   
    def credentials(Closure body){
        def script = utils.script
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


    def switchLanguage(String tag  , String language,boolean isSudo){
        def sshCom = { utils.error "Has escrito mal el language"}
        String path ="./${name}"
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
                    com("docker run -dp 8080:8080 ${name} ",isSudo) // esto cambia
                }
            break

            case "angular":
                sshCom = {
                    com("rm -fr ${path}") // eliminacion de carpeta
                    com("mkdir ${path}")
                    utils.cmd "zip -r dist.zip dist"
                    put("dist.zip",path)
                    put("Dockerfile",path)
                    utils.cmd "rm dist.zip" //TODO continaur dema qui
                    com("unzip ${path}/dist -d ${path}")
                    com("docker build ${path} -t ${name}",isSudo)
                    com("docker run -d -p 80:80 ${name} ",isSudo)   
                }
            break
        }

        return sshCom
    }

    

    def docker(  Map conf ){   
        if( conf.language == null ){
            utils.error "ssh docker necesita que especifiques el lenguages"
            return
        }
        String name = conf.tag ?: "docker-${conf.language}"
        boolean isSudo= conf.isSudo?:false
        try{
             applySsh( switchLanguage( name ,conf.language ,isSudo ) )
            // applySsh( switchLanguage( language ) )
        }catch(Exception ex){
            script.error "Ha habido un error en la ejecucion del docker en ssh"
        }
       
    }


}