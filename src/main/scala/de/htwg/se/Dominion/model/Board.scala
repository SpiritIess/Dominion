package de.htwg.se.Dominion.model

case class Board() {

  private val moneyStack = Seq[String]("Copper: " + Pile().copperPile.length, "Silver: " + Pile().silverPile.length, "Gold: " + Pile().goldPile.length)
  private val winninStack = Seq[String]("Property: " + Pile().propertyPile.length, "Dukedom: " + Pile().dukedomPile.length, "Province: " + Pile().provincePile.length)

  override def toString: String = {
    moneyStack.mkString("|", "| |", "|\n") + winninStack.mkString("|", "| |", "|\n")
  }
}