package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui.{State, TuiPlayerSetup}
import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.Board

case class TuiGameStart(controller: Controller, tui: Tui) extends State{
  override def processInputLine(input: String): Unit = {
    input match {
      case "2" => controller.setUpPlayers(tui,2)

      case "3" => controller.setUpPlayers(tui,3)

      case "4" => controller.setUpPlayers(tui,4)

      case "q" => controller.quitGame(tui)

      case _ => println("type a number between '2' and '4' or press 'q' to quit!\n")
    }
  }
}