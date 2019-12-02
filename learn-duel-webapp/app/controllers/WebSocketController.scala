package controllers

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.stream.Materializer
import com.google.inject.Inject
import play.api.libs.streams.ActorFlow
import play.api.mvc.{AbstractController, ControllerComponents, WebSocket}

// source https://www.playframework.com/documentation/2.7.x/ScalaWebSockets
class WebSocketController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer)
  extends AbstractController(cc) {

  def socket: WebSocket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      WebSocketActor.props(out)
    }
  }
}

object WebSocketActor {
  def props(out: ActorRef): Props = Props(new WebSocketActor(out))
}

class WebSocketActor(out: ActorRef) extends Actor {
  def receive: Receive = {
    case msg: String =>
      out ! ("I received your message: " + msg)
  }
}
