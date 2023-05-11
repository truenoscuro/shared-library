package es.tresdigits.jenkins.biblio

class Biblio{

    private static languages =[
        ssh:["ssh"]
        maven:["maven","m","mvn","spring","springboot","boot"],                 //m
        angular:["angular","a","ng","node","npm"],                              //a
        sonar:["sonar","s","sonarq","scanner","sonar-scanner","sonarscaner"]    //s

    ]

    private static functs = [

        build:["build","b","construir","constr"],                               //b
        install:["install","i","instalar","inst"],                              //i
        scanner:["scanner","s","scan","escaner"],                               //s
        pack:["pack","p","package","empaquetar"],                               //p
        compile:["compile","c","comp","compilar"],                              //c




    ]


    static String getLang(String lang){
        def solv = "error"
        String lowerLang = lang.toLowerCase()
        languages.each{ name,list ->
            if( list.contains(lowerLang) ) solv = name
        }
        return solv

    }

    static String getFunct(String funct){
        def solv = "error"
        String lowerFunct = funct.toLowerCase()
        functs.each{ name,list ->
            if( list.contains(lowerFunct) ) solv = name
        }
        return solv

    }





















}