package es.tresdigits.jenkins.utils

import java.io.File 



class DockerFile {

    //generador de Dockerfile
    static void generate( String workspace, String content ){
        // TODO A vegades funciona a vegades no . Eliminar workspace por si acaso
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
    static void generateSpring(String workspace,String gitUrl ,boolean isJBoss, String tagMaven,String tagTomcat){
        String content = contentSpring( gitUrl ,isJBoss, tagMaven, tagTomcat)
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


    static String contentSpring(String gitUrl, boolean isJBoss, String tagMaven, String tagTomcat ){
        // TODO ara per defecta esta en tomcat
        String directory = getDirectoryGit( gitUrl )

        String directoryBuilder = "/maven/${directory}/target/*.war"
        
        String contentPort=contentTomcat(directoryBuilder,tagTomcat)
        if(isJBoss) contentPort = contentJboss(directoryBuilder)


        String content=
        """
        FROM maven:${tagMaven} AS builder

        USER root
        WORKDIR /maven
        RUN git clone ${gitUrl}

        WORKDIR /maven/${directory}
        RUN mvn package

        ${contentTomcat(directoryBuilder,tagTomcat)}
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
        USER root
        COPY --from=builder ${directoryBuilder} /usr/local/tomcat/webapps/ROOT.war
        CMD [ "catalina.sh","run"]
        """
        return content
        

    }

    static String contentJboss(String directoryBuilder){
        String content=
        """
        FROM jboss/wildfly
        COPY --from=builder ${directoryBuilder} /opt/jboss/wildfly/standalone/deployments/ROOT.war

        """
    }


    static String contentTomee(String directoryBuilder,String tagTomee){

        String content=
        """
        FROM tomee:${tagTomee}
        USER root
        COPY --from=builder ${directoryBuilder} /usr/local/tomee/webapps/ROOT.war
        CMD [ "catalina.sh","run"]
        """
        return content
        

    }





}