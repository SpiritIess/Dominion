package de.htwg.se.Dominion.util

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}
