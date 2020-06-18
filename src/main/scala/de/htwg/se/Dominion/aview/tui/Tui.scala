package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.model.{Board, Player}

import scala.collection.mutable.ListBuffer

case class Tui(controller: Controller) {
  var state : State = TuiGameStart(controller ,this)

}
