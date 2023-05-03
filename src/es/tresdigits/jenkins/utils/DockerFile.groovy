package es.tresdigits.jenkins.utils

class DockerFile {


    static void generate( String workspace, String image ){

        File dockerFile = new File("${workspace}/Dockerfile.${image}")

        content=
        """
        FROM ${image}

        """
        dockerFile.write(content)
    }





}