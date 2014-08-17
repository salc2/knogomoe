package controllers

import play.api._
import play.api.mvc._
import utils.Lang
import java.io.InputStream
import utils.SimpleTTS
import play.api.libs.iteratee.Enumerator
import scala.util.{Success, Failure}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws._

object Application extends Controller {

  def index = Action {
    Ok(views.html.processing())
  }

  def tts(msg:String) = Action.async {
   val s = msg.split("""\.""")(0) 
   val tts = new SimpleTTS(Lang.Es)
   tts.getTTS(s).map(response =>{
	val asStream:Array[Byte] = response.ahcResponse.getResponseBodyAsBytes
	 Ok(asStream).as("audio/mpeg")
    })
   //WS.url("https://translate.google.com/translate_tts?tl=es&q=hola").get().map(response => {
    //val asStream:Array[Byte] = response.ahcResponse.getResponseBodyAsBytes
    //Ok(asStream).as("audio/mpeg")
  //})
}
}
