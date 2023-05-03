package es.tresdigits.jenkins.utils
class DockerFile {

    //generador de Dockerfile
    static void generate( String workspace, String content ){
        File dockerFile = new File("${workspace}/Dockerfile")
        dockerFile.write(content)
    }

    //util funcion
    static String getDirectoryGit( String gitUrl ){
        def regex1 = /\/(\w|-|\d)+\.(?=(git))/
        def regex2 = /(\w|-|\d)+/
    
        def directory = (gitUrl =~ regex1)[0][0]
        return (directory =~ regex2)[0][0]
    }


    //generador especifico

    static void generateAngular(String workspace,String gitUrl , String tagNode,String tagApache){
        String content = contentAngular( gitUrl , tagNode, tagApache)
        generate( workspace, content )
    }
    static void generateSpring(String workspace,String gitUrl , String tagNode,String tagTomcat){
        String content = contentSpring( gitUrl , tagNode, tagTomcat)
        generate( workspace, content )
    }

    //content builder
    static String contentAngular(String gitUrl , String tagNode,String tagApache){
        
        String directory = getDirectoryGit( gitUrl )

        String directoryBuilder = "/node/${directory}/dist/."
        String content= 
        """
        
        FROM node:${tagNode} AS builder
        USER root
        WORKDIR /node

        RUN npm install -g @angular/cli
        RUN git clone ${gitUrl}

        WORKDIR /node/${directory}

        RUN npm i
        RUN ng build

        ${contentApache(directoryBuilder,tagApache)}
        """

        return content 
    }


    static String contentSpring(String gitUrl, String tagMaven, String tagTomcat ){
        // TODO ara per defecta esta en tomcat
        String directory = getDirectoryGit( gitUrl )

        String directoryBuilder = "/maven/${directory}/target/*.war"
        String content=
        """
        FROM maven:${tagMaven} AS builder

        USER root
        WORKDIR /maven
        RUN git clone ${gitUrl}

        WORKDIR /maven/${directory}
        RUN mvn package

        ${contentApache(directoryBuilder,tagTomcat)}
        """


        return content
        
    }

    // content dels desplegadors

    static String contentApache(String directoryBuilder,String tagApache){

        String content = 
        """
        FROM php:${tagApache}-apache

        USER root
        WORKDIR /var/www/html
        COPY --from=builder  ${directoryBuilder} ./

        """
        return content
    }



    static String contentTomcat(String directoryBuilder,String tagTomcat){

        String content=
        """
        FROM tomcat:${tagTomcat}
        ARG catalina_dir=/usr/local/tomcat
        USER root
        COPY --from=builder ${directoryBuilder} /usr/local/tomcat/webapps/ROOT.war

        """
        return content
        

    }





}