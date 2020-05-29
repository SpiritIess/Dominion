package de.htwg.se.Dominion.model

import scala.collection.mutable.ListBuffer

case class Tui(pile: Pile) {
  def printTui(): Unit = {

//    println("Dominion\n")
//    val student1 = Player("Jakob Strakhof")
//    val student2 = Player("Karsten Huber")
//    println("Hello, " + student1.name + " and " + student2)

    val board = Board(pile).toString()
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
    var playerListTemp = new ListBuffer[Player]
    for (x <- playerNames) {
      playerListTemp += Player(x)
    }
    val playerList = playerListTemp.toList
    playerList
  }

}
