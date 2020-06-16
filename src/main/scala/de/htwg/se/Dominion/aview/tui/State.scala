package de.htwg.se.Dominion.aview.tui

trait State {
  def processInputLine(input:String)
  def printTui()
  def handle()
}
