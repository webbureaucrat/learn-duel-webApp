package controllers

import javax.inject._
import play.api._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

import scala.concurrent.Future
import play.api.mvc._
import de.htwg.se.learn_duel.controller.Controller
import forms.PlayerForm
import play.api.i18n.I18nSupport
import play.api.libs.json.Json

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, controllerServer: Controller) extends AbstractController(cc) with I18nSupport {
  var counter = 1;
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

  def about() = Action {
    //val menuAsText = controllerServer.menuToText
    Ok(views.html.about())
  }

  def viewPlayers() = Action{ implicit  request =>
    Ok(views.html.players(PlayerForm.form))
  }

  // GET
  def startQuestions() = Action{
//    implicit request =>
//      val formData: PlayerForm = PlayerForm.form.bindFromRequest.get
//      println(formData.name.toString)
//      addPlayer(formData.name)
//      //println(player)
////      Ok(views.html.questions())
      Redirect(routes.HomeController.start())
  }

  def start() = Action {
    controllerServer.reset()
    counter = controllerServer.getGameState.questionCount()
    controllerServer.onStartGame()
    val question = Json.toJson(controllerServer.getGameState.currentQuestion.get)
    Ok(views.html.questions(question))
  }

//  def nextQuestion() = Action {
//
//    if(counter > 0) {
//      val question = controllerServer.getGameState.currentQuestion.get
//      Ok(views.html.questions(question))
//    } else {
//      Ok(views.html.score(controllerServer.getGameState.players))
//    }
//  }

  def onAnswerChosen(position: Int) = Action {
    println("i am controller")
    controllerServer.onAnswerChosen(position)
    counter = counter -1
    if(counter > 0) {
      val question = Json.toJson(controllerServer.getGameState.currentQuestion.get)
      Ok(views.html.questions(question))
    } else {
      Ok(views.html.score(controllerServer.getGameState.players))
    }
  }

//  // post
//  def addPlayer(p: String)(implicit request: Request[_]) = {
//    // do something that needs access to the request
//    val param = p.isEmpty match {
//      case true => None
//      case false => Option(p)
//    }
//
//    controllerServer.getPlayerNames.foreach(f => println(f))
//
//    val tmpPlayer = controllerServer.getPlayerNames(0)
//
//    if(!controllerServer.getPlayerNames.contains(p)){
//      controllerServer.onAddPlayer(param)
//      controllerServer.onRemovePlayer(tmpPlayer)
//    }
//
//    controllerServer.getPlayerNames.foreach(f => println(f))
//
//    Future.successful(NoContent)
//  }
}
