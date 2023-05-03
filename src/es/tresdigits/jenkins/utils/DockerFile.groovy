package es.tresdigits.jenkins.utils
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
        
        //TODO extreure per treure el directory d'un github
        def regex1 = /\/(\w|-|\d)+\.(?=(git))/
        def regex2 = /(\w|-|\d)+/
    
        def directory = (gitUrl =~ regex1)[0][0]
        directory = (directory =~ regex2)[0][0]
        //-----------

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

    static String contentApache(String directoryBuilder,String tagApache){

        String content = 
        """
        FROM php:${tagApache}-apache

        USER root
        WORKDIR /var/www/html
        COPY --from=builder  ${directoryBuilder} ./

        """

        
    }





}