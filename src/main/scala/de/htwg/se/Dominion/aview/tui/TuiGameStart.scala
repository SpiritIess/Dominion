package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.Board

case class TuiGameStart(controller: Controller, tui: Tui) extends State{
  override def processInputLine(input: String): Unit = {
    input match {
      case "2" => {
        controller.setGameState(GameState.twoPlayers)
        tui.state = TuiTwoPlayers(controller, tui)
        printTui()
        handle()
      }
      /*case "3" => {
        controller.setGameState(GameState.threePlayers)
        //tui.state = TuiThreePlayers(controller, tui)
        printTui();
        handle()
      }
      case "4" => {
        controller.setGameState(GameState.fourPlayers)
        //tui.state = TuiFourPlayers(controller, tui)
        printTui();
        handle()
      }
       */
      case "q" => {
        controller.setGameState(GameState.endScreen)
        println("you chose to quit the game\n")
      }
      case _ => println("type a number between '2' and '4' or press 'q' to quit!\n")
    }
  }

  override def handle(): Unit = {
    println("type in the names of the players, using a space as seperator\n")
  }

  override def printTui(): Unit = {
    val board = Board().toString()
    print(board)
  }
}
