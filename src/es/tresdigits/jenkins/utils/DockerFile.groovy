package es.tresdigits.jenkins.utils
import java.io.File 
class DockerFile {


    static void generate( String workspace, String image ){

        File dockerFile = new File("workspace/Dockerfile")
       
        String content=
        """
        FROM ${image}

        """
        dockerFile.write(content)
        
    }





}