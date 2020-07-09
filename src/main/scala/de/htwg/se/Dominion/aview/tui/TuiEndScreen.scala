package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.controllerComponent.Controller
import de.htwg.se.Dominion.util.Observer

case class TuiEndScreen(controller:Controller) extends Observer with State {
  override def update: Boolean = {true}

  override def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case "n" => controller.startGame
      case _ => println("press q to quit or press n to start new game!\n")
    }
  }
}
