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
    Ok(views.html.index("Scala TTS"))
  }

  def tts(msg:String) = Action {
   //val tts = new SimpleTTS(Lang.Es)
   //val gettts =  tts.getTTS(msg).map(response =>{
//	 Ok.stream(Enumerator.fromStream(response))
//	})
   Async {
   WS.url("https://translate.google.com/translate_tts?tl=es&q=hola").get().map(response => {
    val asStream: InputStream = response.ahcResponse.getResponseBodyAsStream
    Ok.stream(Enumerator.fromStream(asStream))
  })
}
}
}
