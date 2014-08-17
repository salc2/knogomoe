package utils

import play.api._
import akka.actor._

case class Message(msg:String)
case class Operation(number:Int,operation:BigInt => BigInt)

class BranchActor extends Actor with ActorLogging {

	def receive = {
		case Message(msg) => {
			sender ! s"From Actor: $msg"	
		}
		case Operation(n,f) => {
			sender ! f(n)
		}
	}
}
