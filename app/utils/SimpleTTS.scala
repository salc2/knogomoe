package utils

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import play.api.libs.iteratee.Enumerator
import java.io.FileOutputStream
import play.api._
import play.api.mvc._
import play.api.libs.ws._
import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global




object Lang extends Enumeration {
      type Lang = Value
      val Es, En = Value
}
class SimpleTTS(lang:Lang.Value){

val TEXT_TO_SPEECH_SERVICE:String = "http://translate.google.com/translate_tts"

def getTTS(text:String):Future[InputStream] = {
     WS.url(TEXT_TO_SPEECH_SERVICE + "?" + "tl="+lang.toString().toLowerCase() + "&q=" + text).get()
	.map(response => {response.ahcResponse.getResponseBodyAsStream()})
  }
def getTTSAndSave(text:String):Unit = {
     WS.url(TEXT_TO_SPEECH_SERVICE + "?" + "tl="+lang.toString().toLowerCase() + "&q=" + text).get().map(response => {
       val bis = new BufferedInputStream(response.ahcResponse.getResponseBodyAsStream())
        val fos = new FileOutputStream("/home/chucho/fileaudio.mp3")
        Iterator 
	.continually (bis.read)
	.takeWhile (-1 !=)
	.foreach (fos.write)
        fos.flush();
        fos.close()
      })
  }
}

