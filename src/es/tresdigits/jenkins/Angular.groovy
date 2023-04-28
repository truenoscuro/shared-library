package es.tresdigits.jenkins


class Angular  implements Serializable {

    def step
    def utils


    // init angular
    def init(step,utils){
        step.echo "Init angular..."
        this.step = step
        this.utils = utils
    }

    def newProject(nameProject){
        step.echo "New Project..."
        step.sh "ng new ${nameProject}"
        //step.sh ng new nameProject
        //step.sh cd ${nameProject}
    }
}