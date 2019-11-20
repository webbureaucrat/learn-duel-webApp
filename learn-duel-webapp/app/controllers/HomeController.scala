package controllers

import javax.inject._
import javax.inject.Inject
import play.api.http.MimeTypes
import play.api.routing._

import play.api.mvc._
import de.htwg.se.learn_duel.controller.Controller
import forms.PlayerForm
import play.api.i18n.I18nSupport
import play.api.libs.json.Json
import play.api.routing.JavaScriptReverseRouter

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, controllerServer: Controller) extends AbstractController(cc) with I18nSupport {
  var questionCount = 0;

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

  def countQuestion(): Unit = {
    questionCount = questionCount + 1
  }

  def resetCount(): Unit = {
    questionCount = 0
  }

  def about(): Action[AnyContent] = Action {
    //val menuAsText = controllerServer.menuToText
    Ok(views.html.about())
  }

  def viewPlayers(): Action[AnyContent] = Action { implicit request =>
    Ok(views.html.players(PlayerForm.form))
  }

  // GET
  def startQuestions(): Action[AnyContent] = Action {
    //    implicit request =>
    //      val formData: PlayerForm = PlayerForm.form.bindFromRequest.get
    //      println(formData.name.toString)
    //      addPlayer(formData.name)
    //      //println(player)
    ////      Ok(views.html.questions())
    Redirect(routes.HomeController.start())
  }

  def start(): Action[AnyContent] = Action {
    controllerServer.reset()
    controllerServer.onStartGame()
    resetCount()
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

  def onAnswerChosen(position: Int): Action[AnyContent] = Action {
    countQuestion()
    controllerServer.onAnswerChosen(position)
    if (questionCount < controllerServer.getGameState.questionCount()) {
      val question = Json.toJson(controllerServer.getGameState.currentQuestion.get)
      Ok(question)
    } else {
      Ok(views.html.score(controllerServer.getGameState.players))
    }
  }

  def javascriptRoutes: Action[AnyContent] = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.HomeController.onAnswerChosen,
      )
    ).as(MimeTypes.JAVASCRIPT)
  }
}
