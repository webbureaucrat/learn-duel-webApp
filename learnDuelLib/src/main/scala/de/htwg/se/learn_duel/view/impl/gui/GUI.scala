package de.htwg.se.learn_duel.view.impl.gui

import java.io.{ PrintWriter, StringWriter }
import java.util.concurrent.CountDownLatch
import javafx.application.Platform

import com.typesafe.scalalogging.LazyLogging
import de.htwg.se.learn_duel.controller.{ Controller, ControllerException }
import de.htwg.se.learn_duel.model.{ Player, Question }
import de.htwg.se.learn_duel.view.UI
import de.htwg.se.learn_duel.{ Observer, UpdateAction, UpdateData }

import scalafx.application.JFXApp

class GUI(controller: Controller, latch: CountDownLatch) extends JFXApp with UI with Observer with LazyLogging {
  controller.addObserver(this)

  // handle self defined exception in a 'global' exception handler
  Thread.currentThread().setUncaughtExceptionHandler((t: Thread, e: Throwable) => {
    e.getCause match {
      case cause: ControllerException => {
        val infoPopup = new InfoPopup("Error", cause.message)
        infoPopup.getDialogPane().getStylesheets().add("styles.css")
        infoPopup.show
      }
      case _ => {
        val sw = new StringWriter()
        e.printStackTrace(new PrintWriter(sw))
        logger.error(Console.RED + sw.toString + Console.RESET)
        controller.onClose
      }
    }
  })

  // signal initialization down
  latch.countDown()

  // scalastyle:off
  override def update(updateParam: UpdateData): Unit = {
    // every update needs to be run on the JavaFX Application thread
    Platform.runLater { () =>
      updateParam.getAction() match {
        case UpdateAction.BEGIN => {
          displayMenu()
          this.stage.onCloseRequest = { (_) =>
            controller.onClose
          }
        }
        case UpdateAction.CLOSE_APPLICATION => this.stage.close()
        case UpdateAction.SHOW_HELP => {
          val helpText = updateParam.getState().helpText
          val dlg = new InfoPopup("Learn Duel Help", helpText.mkString("\n\n"))
          dlg.getDialogPane().getStylesheets().add("styles.css")
          dlg.show
        }
        case UpdateAction.PLAYER_UPDATE => displayMenu
        case UpdateAction.SHOW_GAME => {
          displayGame(
            updateParam.getState().currentQuestion.get,
            updateParam.getState().players.length > 1)
        }
        case UpdateAction.TIMER_UPDATE => {
          updateParam.getState().currentQuestionTime match {
            case Some(time) => {
              if (this.stage.isInstanceOf[GameStage]) {
                this.stage.asInstanceOf[GameStage].updateTime(time)
              }
            }
            case _ =>
          }
        }
        case UpdateAction.SHOW_RESULT => {
          displayResult(updateParam.getState().players)
        }
        case _ =>
      }
    }
  }
  // scalastyle:on

  override def displayMenu(): Unit = {
    this.stage = new MenuStage(
      _ => controller.onStartGame,
      _ => controller.onHelp,
      (controller.getPlayerNames, controller.nextPlayerName),
      (name) => controller.onAddPlayer(Some(name)),
      controller.onRemovePlayer)
  }

  override def displayGame(question: Question, multiplayer: Boolean): Unit = {
    this.stage = new GameStage(
      question,
      !multiplayer,
      controller.onAnswerChosen)
  }

  override def displayResult(players: List[Player]): Unit = {
    this.stage = new ResultStage(players, controller.reset)
  }
}
