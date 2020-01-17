package de.htwg.se.learn_duel.view

import java.util.concurrent.CountDownLatch

import com.google.inject.Inject
import de.htwg.se.learn_duel.Observer
import de.htwg.se.learn_duel.controller.Controller
import de.htwg.se.learn_duel.model.{ Player, Question }
import de.htwg.se.learn_duel.view.impl.{ TUI => TUIImpl }
import de.htwg.se.learn_duel.view.impl.gui.{ GUI => GUIImpl }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UI extends Observer {
  def displayMenu(): Unit
  def displayGame(currentQuestion: Question, multiplayer: Boolean): Unit
  def displayResult(players: List[Player]): Unit
}

object TUI {
  def create(controller: Controller): TUIImpl = {
    new TUIImpl(controller)
  }
}

object GUI {
  def create(controller: Controller): Unit = {
    val latch = new CountDownLatch(1)
    val gui = new GUIImpl(controller, latch)

    // run GUI on its own thread
    Future {
      gui.main(Array())
    }

    // wait for initialization of JFXApp to be done
    latch.await()
  }
}
