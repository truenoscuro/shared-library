package es.tresdigits

class Step implements Serializable {

  def script

  def init(script){
    this.script = script
  }

  def echo(args) {  script.echo "${args}" }
  def holaMundo(){ script.echo "hola mundo"; }

  def echoScript(){ echo "hola mundo"}


}