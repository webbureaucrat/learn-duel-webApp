package controllers

import akka.actor.ActorSystem
import akka.stream.Materializer
import de.htwg.se.learn_duel.{Observer, UpdateAction, UpdateData}
import javax.inject._
import javax.inject.Inject
import play.api.http.MimeTypes
import play.api.routing._
import play.api.mvc._
import de.htwg.se.learn_duel.controller.Controller
import play.api.i18n.I18nSupport
import play.api.libs.json.{JsObject, Json}
import play.api.libs.streams.ActorFlow
import play.api.routing.JavaScriptReverseRouter

import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.{ HandlerResult, Silhouette }
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.impl.providers.GoogleTotpInfo
import org.webjars.play.WebJarsUtil
import play.api.i18n.I18nSupport
import utils.auth.DefaultEnv

import models.User

import scala.concurrent.{ ExecutionContext, Future }

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, controllerServer: Controller,
                               silhouette: Silhouette[DefaultEnv],
                               authInfoRepository: AuthInfoRepository)
                              (implicit system: ActorSystem,
                               mat: Materializer, ec: ExecutionContext,
                               webJarsUtil: WebJarsUtil,
                               assets: AssetsFinder)
                              extends AbstractController(cc) with Observer with I18nSupport {
//  var questionCount = 0;
  controllerServer.addObserver(this);
  var actor: List[WebSocketActor] = List();

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  //  def about() = Action { implicit request: Request[AnyContent] =>
  //    Ok(views.html.about())
  //  }

//  def countQuestion(): Unit = {
//    questionCount = questionCount + 1
//  }

  def offline() = silhouette.UnsecuredAction.async {
    implicit request: Request[AnyContent] =>
      Future.successful(Ok(views.html.offline()))
  }

//s

//  def about(): Action[AnyContent] = silhouette.SecuredAction.async {
//    implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
//      authInfoRepository.find[GoogleTotpInfo](request.identity.loginInfo).map { totpInfoOpt =>
//        Ok(views.html.about(request.identity, totpInfoOpt))
//      }
//  }

//    def viewPlayers(): Action[AnyContent] = silhouette.SecuredAction.async {
//      implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
//        authInfoRepository.find[GoogleTotpInfo](request.identity.loginInfo).map { totpInfoOpt =>
//      Ok(views.html.players(PlayerForm.form))
//    }

  // GET
//  def startQuestions(): Action[AnyContent] = silhouette.SecuredAction.async {implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
//    authInfoRepository.find[GoogleTotpInfo](request.identity.loginInfo).map { totpInfoOpt =>
//      Redirect(routes.HomeController.start())
//    }
//  }

  def start(): Action[AnyContent] = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
      controllerServer.onStartGame()
//      resetCount()
//      val question = Json.toJson(controllerServer.getGameState.currentQuestion.get)
      Future.successful((NoContent))
//    }
  }

  def onAnswerChosen(position: Int): Action[AnyContent] = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
//      countQuestion()
      controllerServer.onAnswerChosen(position)
      Future.successful((NoContent))
//      if (questionCount < controllerServer.getGameState.questionCount()) {
//        val question = Json.toJson(controllerServer.getGameState.currentQuestion.get)
//        Ok(question)
//      } else {
//        // Remove this in separate function
////        Ok(views.html.score(controllerServer.getGameState.players))
//        Ok(views.html.score())
//      }
//    }
  }

  def javascriptRoutes: Action[AnyContent] = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    authInfoRepository.find[GoogleTotpInfo](request.identity.loginInfo).map { totpInfoOpt =>
      Ok(
        JavaScriptReverseRouter("jsRoutes")(
          routes.javascript.HomeController.onAnswerChosen,
        )
      ).as(MimeTypes.JAVASCRIPT)
    }
  }

  def addWebsocket(actor: WebSocketActor): Unit = {
    this.actor = this.actor :+ actor
  }

  def socket: WebSocket = WebSocket.acceptOrResult[String, String] { request =>
    implicit val req = Request(request, AnyContentAsEmpty)
    silhouette.SecuredRequestHandler { securedRequest =>
      Future.successful(HandlerResult(Ok, Some(securedRequest.identity)))
    }.map {
      case HandlerResult(r, Some(user)) => Right(ActorFlow.actorRef { out =>
        println("Connect received")
        WebSocketActorFactory.props(out, this)
      })
      case HandlerResult(r, None) => Left(r)
    }
  }

  override def update(updateData: UpdateData): Unit = {
    println(updateData.getAction())
      updateData.getAction() match {
        case action if Seq(UpdateAction.BEGIN,
          UpdateAction.SHOW_HELP,
          UpdateAction.PLAYER_UPDATE,
          UpdateAction.SHOW_GAME,
          UpdateAction.TIMER_UPDATE,
          UpdateAction.SHOW_RESULT
        ).contains(action) =>
          val json = Json.toJson(updateData.getState()).as[JsObject] + ("action" -> Json.toJson(updateData.getAction().toString))
          actor.foreach(actor => actor.sendJsonToClient(json))
        case _ =>
    }
  }
}
