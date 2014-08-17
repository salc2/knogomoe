import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import akka.actor._
import play.api.libs._
import play.api.libs.concurrent.Akka
import play.api.test._
import play.api.test.Helpers._
import utils.BranchActor
import utils._
import akka.pattern.ask
import akka._
import play.api._
import scala.concurrent._
import ExecutionContext.Implicits.global
/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "testing Akka actors" in new WithApplication{
	def fact(n:BigInt):BigInt = if(n==0) 1 else n * fact(n-1)
	/*var a = 0
	for (a <- 1 to 9999){
		println( s"${fact(a)}" )
	} */
	def innerLoop(l:List[Future[Any]],i:Int):List[Future[Any]] =
		if(i < 9999){
			val b = Akka.system.actorOf(Props[BranchActor], name = s"b$i")
			val lst = l :+ b ? new Operation(i,fact)
			innerLoop(lst,i+1)
		}else l
	val lst = innerLoop(List(),0)
	for (l <- lst){
		l.map { r => println(r) }
	}
    }

    /*"render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Your new application is ready.")
    }*/
  }
}
