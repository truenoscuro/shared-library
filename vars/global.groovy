/* Variables globales */
package var

def email( String mailVar, String subjectVar, String bodyVar){
    always{
        mail to: "${mailVar}",
        subject: "${subjectVar}",
        body: "${bodyVar}"
    }
}

def sonar(){

}

