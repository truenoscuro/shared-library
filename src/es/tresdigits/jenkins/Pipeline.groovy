package es.tresdigits.jenkins

class Pipeline implements Serializable{

    def stages
    def script

    def init(script){
        stages = [:]
        this.script = script
    }

    def add(String name, stage){
        stages[name] = stage
    }



    def run(){
        for( stage in stages ){ script.stage }
    }


}