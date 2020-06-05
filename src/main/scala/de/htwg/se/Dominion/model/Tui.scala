package de.htwg.se.Dominion.model

import de.htwg.se.Dominion.model.GameState.gameState
import scala.collection.mutable.ListBuffer


case class Tui(var state: gameState = GameState.init, var playerList: ListBuffer[Player] = new ListBuffer[Player]) {
  def printTui(): Unit = {
    val board = Board().toString()
    print(board)
  }

  def handleInit(input: String): Unit = {
    input match {
      case "q" => print("-1")
      case "n" => print("5")
      case "print" => printTui()
      case "s" => state = GameState.initPlayers; print("Input player name or 'c' to cancel input: ")
      case _ =>
    }
  }

  def addPlayer(input: String): List[Player] = {
    input match {
      case "c" => state = GameState.play
      case "" => print("Input player name or 'c' to cancel input: ");
      case _ => playerList += Player(input); print("Input player name or 'c' to cancel input: ");
    }
    playerList.toList
  }

  def handlePlay(input: String): Unit = {
    input match {
      case "q" => print("end of game")
      case "n" => printTui()
      case "p" => print("do something\n")
      case _ => print(input + "\n")
    }
  }

  def processInputLine(input: String): Unit = {
    state match {
      case GameState.init => handleInit(input)

      case GameState.initPlayers => addPlayer(input)

      case GameState.play => handlePlay(input)
    }
  }
}
