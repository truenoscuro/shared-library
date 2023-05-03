package es.tresdigits.jenkins.utils
import java.io.File
import java.util.regex.Matcher
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
       
        def firstMatch = (gitUrl =~ /\/(\w|-|\d)+\.(?=(git))/);
        String directoryAux = firstMatch[0]
        def secondMatch = (directoryAux =~ /(\w|-|\d)+/)
        String directory = secondMatch[0]
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

        COPY --from=builder  ${directoryBuilder} ./

        """

        
    }





}