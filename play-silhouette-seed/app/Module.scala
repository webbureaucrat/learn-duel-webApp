import com.google.inject.AbstractModule
import de.htwg.se.learn_duel.controller.Controller
import de.htwg.se.learn_duel.model.Question
import de.htwg.se.learn_duel.model.impl.Game

import play.api.libs.json.{ JsValue, Json }

import scala.io.Source

class Module extends AbstractModule {
  val jsonString: String = Source.fromFile(s"app/questions.json").getLines().mkString
  val json: JsValue = Json.parse(jsonString)
  println(json)
  val questions: List[Question] = Json.fromJson[List[Question]](json).get
  val gameState = Game(questions = questions)
  val serverCtrl: Controller = Controller.create(gameState)

  //  /**
  //   * Configures the module.
  //   */
  override def configure(): Unit = {
    bind(classOf[Controller]).toInstance(serverCtrl)
  }
}