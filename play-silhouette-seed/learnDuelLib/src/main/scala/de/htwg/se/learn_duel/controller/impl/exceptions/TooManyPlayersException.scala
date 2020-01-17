package de.htwg.se.learn_duel.controller.impl.exceptions

import de.htwg.se.learn_duel.controller.ControllerException

case class TooManyPlayersException(message: String) extends Exception(message) with ControllerException {

}
