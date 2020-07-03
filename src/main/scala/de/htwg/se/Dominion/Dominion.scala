package de.htwg.se.Dominion

import de.htwg.se.Dominion.aview.gui.SwingGui
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.model.{Board, Player}

import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine

object Dominion {
  var board :Board = Board()
  val controller = new Controller()
  var tui : Tui = Tui(controller)
  var gui : SwingGui = new SwingGui(tui, controller)
  controller.notifyObservers
  var playerList = new ListBuffer[Player]

  def main(args: Array[String]): Unit = {
    var input: String = ""
    var test = 0
    println("please type in the number of players (from 2 to 4) or press 'q' to quit")
    do {
      input = readLine()
      tui.state.processInputLine(input)
    } while (input != "q")
  }
}