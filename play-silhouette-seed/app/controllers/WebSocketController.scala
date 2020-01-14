package controllers

import akka.actor.{ Actor, ActorRef, ActorSystem, Props }
import akka.stream.Materializer
import com.google.inject.Inject
import javax.inject.Singleton
import play.api.libs.json.{ JsObject, JsValue }
import play.api.libs.streams.ActorFlow
import play.api.mvc.{ AbstractController, ControllerComponents, WebSocket }

// source https://www.playframework.com/documentation/2.7.x/ScalaWebSockets
@Singleton
class WebSocketController @Inject() (cc: ControllerComponents, homeController: HomeController)(implicit system: ActorSystem, mat: Materializer)
  extends AbstractController(cc) {

  def socket: WebSocket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      WebSocketActorFactory.props(out, homeController)
    }
  }
}

object WebSocketActorFactory {
  def props(out: ActorRef, homeController: HomeController): Props = Props(new WebSocketActor(out, homeController))
}

class WebSocketActor(out: ActorRef, homeController: HomeController) extends Actor {

  override def preStart(): Unit = {
    super.preStart()
    homeController.addWebsocket(this)
  }

  def receive: Receive = {
    case msg: String =>
      out ! ("I received your message: " + msg)
  }

  def sendJsonToClient(json: JsObject) = {
    //    println("Received message from Controller")
    out ! (json.toString())
  }
}
