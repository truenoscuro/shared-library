package es.tresdigits.jenkins.utils

class DockerFile {


    static void generate( String workspace, String image ){

        File dockerFile = new File("Dockerfile.${image}")
        dockerFile.createNewFile()
        /*
        String content=
        """
        FROM ${image}

        """
        dockerFile.write(content)
        */
    }





}