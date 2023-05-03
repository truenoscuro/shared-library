package es.tresdigits.jenkins.utils
import java.io.File 
class DockerFile {


    static void generate( String workspace, String content ){
        File dockerFile = new File("${workspace}/Dockerfile")
        dockerFile.write(content)
    }

    static void generateAngular(String workspace,String gitUrl , String tagNode,String tagApache){
        String content = contentAngular( gitUrl , tagNode, tagApache)
        generate( workspace, content )
    }

    static String contentAngular(String gitUrl , String tagNode,String tagApache){
       
        String directory = (gitUrl =~ /\/(\w|-|\d)+\.(?=(git))/).findAll()*.first()
        directory = (directory =~ /(\w|-|\d)+/).findAll()*.first()
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

        ${contentApache(tagApache,directoryBuilder)}
        """

        return content 
    }

    static String contentApache(String tagApache,String directoryBuilder){

        String content = 
        """
        FROM php:${tagApache}-apache

        USER root
        WORKDIR /var/www/html
        ARG directory_builder=/node/angular-testing-course/dist/.
        COPY --from=builder  ${directoryBuilder} ./

        """

        
    }





}