package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui._
import de.htwg.se.Dominion.aview.tui.State
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.model.{Board, Player}
import de.htwg.se.Dominion.util.Observer

import scala.collection.mutable.ListBuffer

case class Tui(controller: Controller) extends Observer {
  controller.add(this)
  var state: State = TuiGameStart(controller, this)
  controller.startGame

  override def update: Boolean = {
    true
  }

}
