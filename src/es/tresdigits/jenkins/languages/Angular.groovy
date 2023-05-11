package es.tresdigits.jenkins.languages


class Angular  {

    def utils 
    Angular( utils  ){  
        this.utils =  utils  
    }

    def iAngular() { utils.cmd "npm install -g @angular/cli"}
    
    def install() {utils.cmd "npm i"}
   
    
    def build() { utils.cmd "ng build"}

    
   





}