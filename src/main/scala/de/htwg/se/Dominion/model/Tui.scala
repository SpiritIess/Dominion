package de.htwg.se.Dominion.model

import scala.collection.mutable.ListBuffer

case class Tui() {
  def printTui(): Unit = {
    val board = Board().toString()
    print(board)
  }

  def readPlayerCount(): Int = {
    print("Input number of Players: ")
    scala.io.StdIn.readInt()
  }

  def getPlayerNames(playerCount: Int = readPlayerCount()) : List[String] = {
    var playerList = new ListBuffer[String]()
    for (x <- 1 to playerCount) {
      print("Input player " + x  + " name: ")
      playerList += scala.io.StdIn.readLine()
    }
    playerList.toList
  }

  def getPlayerList(playerNames: List[String] = getPlayerNames()): List[Player] = {
    var playerList = new ListBuffer[Player]
    for (x <- playerNames) {
      playerList += Player(x)
    }
    playerList.toList
  }

  def processInputLine(input: String): Int = {
    input match {
      case "q" => -1
      case "n" => 5
      case "s" => val players = getPlayerList(); println(players.head.startingHand); players.length
      case _ => 2
    }
  }
}
