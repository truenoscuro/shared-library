package es.tresdigits.jenkins.biblio

class Biblio{

    private static languagues =[

        maven:["maven","m","mvn","spring","springboot","boot"],                 //m
        angular:["angular","a","ng","node","npm"],                              //a
        sonar:["sonar","s","sonarq","scanner","sonar-scanner","sonarscaner"]    //s


    ]

    private static funct = [

        build:["build","b","construir","constr"],                               //b
        install:["install","i","instalar","inst"],                              //i
        scanner:["scanner","s","scan","escaner"],                               //s
        pack:["pack","p","package","empaquetar"],                               //p
        compile:["compile","c","comp","compilar"],                              //c




    ]


    static String getLang(String lang){
        String solv = ""
        languagues.each{ name,list ->
            list.each{ alias ->
                if(alias == lang) {
                    solv = name
                    break
                }
            }
            if(solv != "") break
        }
        return solv

    }





















}