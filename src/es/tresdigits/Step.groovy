package es.tresdigits

class Step  implements Serializable {

  def script
  def env

  def init(script, env){
    this.script = script
    this.env = env
  }

  def echo(args) {  script.echo "${args}" }
  def holaMundo(){ script.echo "hola mundo"; }


}