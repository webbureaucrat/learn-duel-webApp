package controllers

import javax.inject._
import play.api._
import scala.concurrent.Future

import play.api.mvc._
import de.htwg.se.learn_duel.controller.Controller

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, controllerServer: Controller) extends AbstractController(cc) {

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

  def viewPlayers() = Action{
    Ok(views.html.players())
  }

  // GET
  def startQuestions(player: String) = Action{
    implicit request =>
      addPlayer(player)
      //println(player)
      Ok(views.html.questions())
  }

  // post
  def addPlayer(p: String)(implicit request: Request[_]) = {
    // do something that needs access to the request
    val param = p.isEmpty match {
      case true => None
      case false => Option(p)
    }

    if(!controllerServer.getPlayerNames.contains(p)){
      controllerServer.onAddPlayer(param)
    }

    println(controllerServer.getPlayerNames)

    Future.successful(NoContent)
  }
}
