package de.htwg.se.Dominion.model

case class Board() {

  private val moneyStack = Seq[String] ("Copper: " + Pile.piles(CardSet.copperCard), "Silver: " + Pile.piles(CardSet.silverCard), "Gold: " + Pile.piles(CardSet.goldCard))
  private val winninStack = Seq[String] ("Property: " + Pile.piles(CardSet.propertyCard), "Dukedom: " + Pile.piles(CardSet.dukedomCard), "Province: " + Pile.piles(CardSet.provinceCard))

  override def toString: String = {
    moneyStack.mkString("|", "| |", "|\n") + winninStack.mkString("|", "| |", "|\n")
  }
}