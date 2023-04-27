
class Utilities implements Serializable{

    // funcion para un step
    def steps
    Utilities(steps){ this.steps = steps }
    def mvn(args){
        steps.sh "alg"
    }

    // per tot el node
    def call(Map config) {
        node {
            git url: "https://github.com/jenkinsci/${config.name}-plugin.git"
            sh 'mvn install'
            mail to: '...', subject: "${config.name} plugin build", body: '...'
        }
    }
    // buildPlugin name: 'git'

    // vars/windows.groovy
    def call(Closure body) {
        node('windows') {
            body()
        }
    }
    //windows {
    //    bat "cmd /?"
    //}
}