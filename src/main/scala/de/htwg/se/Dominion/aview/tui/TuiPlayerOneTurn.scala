package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.Controller

case class TuiPlayerOneTurn(controller : Controller, tui:Tui) extends State {
  override def processInputLine(input: String): Unit = ???

  override def printTui(): Unit = ???

  override def handle(): Unit = ???
}
