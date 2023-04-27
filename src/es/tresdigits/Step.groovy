package es.tresdigits

class Step implements Serializable {
  def steps
  Step(steps) {this.steps = steps}
  def mvn(args) {
      steps.sh "echo ${args}"
    }
}