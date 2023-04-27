package es.tresdigits

class Step implements Serializable {
  def steps
  Step() {}
  def setStep(steps){this.steps = steps}
  def mvn(args) {
      steps.echo "${args}"
    }
  
}