package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.Board

case class TuiGameStart(controller: Controller, tui: Tui) extends State{
  override def processInputLine(input: String): Unit = {
    input match {
      case "2" =>
        controller.gameState = GameState.twoPlayers
        tui.state = TuiTwoPlayers(controller, tui)
        val board = Board().toString()
        print(board)
        println("type in the names of the players, using a space as seperator\n")

      /*case "3" =>
        controller.setGameState(GameState.threePlayers)
        //tui.state = TuiThreePlayers(controller, tui)
        printTui();
        handle()

      case "4" =>
        controller.setGameState(GameState.fourPlayers)
        //tui.state = TuiFourPlayers(controller, tui)
        printTui();
        handle()

       */
      case "q" =>
        controller.gameState = GameState.endScreen
        println("you chose to quit the game\n")

      case _ => println("type a number between '2' and '4' or press 'q' to quit!\n")
    }
  }
}
