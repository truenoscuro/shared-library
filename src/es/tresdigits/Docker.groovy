package es.tresdigits

class Docker implements Serializable{


    run(String img,String arg, Closure body){
        docker.image("${img}").inside("${arg}"){
            body();
        }
    }
}
