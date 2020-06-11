package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.model.{Board, Player}

import scala.collection.mutable.ListBuffer

case class Tui(controller: Controller) {
  var state : State = TuiGameStart(controller ,this)




























































  /*


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
      case "2" => {

      }
      case "3" => 5
      case "4" => val players = getPlayerList(); println(players.head.startingHand); players.length
      case _ => 2
    }
  }

   */
}
