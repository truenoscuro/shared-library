package es.tresdigits.jenkins.languages


class Angular  {

    def utils 
    Angular( utils  ){  
        this.utils =  utils  
    }

    def iAngular = { utils.cmd "npm install -g @angular/cli"}
    
    //install package.json
    def install = {utils.cmd "npm i"}
    //ng funct
    
    def build = { utils.cmd "ng build"}

    
   





}