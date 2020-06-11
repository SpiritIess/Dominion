package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.Board

case class TuiGameStart(controller: Controller, tui: Tui) extends State{
  override def processInputLine(input: String): Unit = {
    input match {
      case "2" => controller.gameState = GameState.twoPlayers; printTui()
      case "3" => controller.gameState = GameState.threePlayers; printTui()
      case "4" => controller.gameState = GameState.fourPlayers; printTui()
      case "q" => println("you chose to quit the game\n")
      case _ => println("press a number between '2' and '4' or press 'q' to quit!\n")
    }
  }

  override def handle(): Unit = {
    val e = controller.gameState
    /*
    e match {
      case GameState.startScreen => println("error, still in startscreen")
      case GameState.twoPlayers => {
        println("input players names, seperated by a space\n")

      }
    }

    */
  }

  override def printTui(): Unit = {
    val board = Board().toString()
    print(board)
  }
}
