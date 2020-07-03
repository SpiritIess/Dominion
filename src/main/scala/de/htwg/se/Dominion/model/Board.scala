package de.htwg.se.Dominion.model

case class Board() {

  private val moneyStack = Seq[String] ("Copper: " + Pile.piles(CardSet.copperCard), "Silver: " + Pile.piles(CardSet.silverCard), "Gold: " + Pile.piles(CardSet.goldCard))
  private val winninStack = Seq[String] ("Property: " + Pile.piles(CardSet.propertyCard), "Dukedom: " + Pile.piles(CardSet.dukedomCard), "Province: " + Pile.piles(CardSet.provinceCard), "Garden:" + Pile.piles(CardSet.gardenCard))
  private val actionStack1 = Seq[String] ("Moat: " + Pile.piles(CardSet.moatCard), "Village: " + Pile.piles(CardSet.villageCard), "Lumberjack: " + Pile.piles(CardSet.villageCard), "Smithy: "+ Pile.piles(CardSet.smithyCard))
  private val actionStack2 = Seq[String] ("Money Lender: " + Pile.piles(CardSet.moneyLenderCard), "Market: " + Pile.piles(CardSet.marketCard), "Adventurer: " + Pile.piles(CardSet.adventurerCard))
  private val actionStack3 = Seq[String] ("Laboratory: " + Pile.piles(CardSet.labCard), "Funfair: " + Pile.piles(CardSet.funFairCard))
  override def toString: String = {
    moneyStack.mkString("|", "| |", "|\n") + winninStack.mkString("|", "| |", "|\n") + actionStack1.mkString("|", "| |", "|\n") + actionStack2.mkString("|", "| |", "|\n") + actionStack3.mkString("|", "| |", "|\n")
  }
}