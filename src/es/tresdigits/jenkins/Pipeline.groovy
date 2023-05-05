package es.tresdigits.jenkins

class Pipeline implements Serializable (){

    def stages



    Pipeline(){
        stages=[:]
    }

    add(String name, stage){
        stages[name] = stage
    }



    run(){
        for( stage in stages ){ stage }
    }


}