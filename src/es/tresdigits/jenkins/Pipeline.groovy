package es.tresdigits.jenkins

class Pipeline implements Serializable{

    def stages



    Pipeline(){
        stages=[:]
    }

    def add(String name, stage){
        stages[name] = stage
    }



    def run(){
        for( stage in stages ){ stage }
    }


}