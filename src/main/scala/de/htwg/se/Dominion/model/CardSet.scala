package de.htwg.se.Dominion.model

object CardSet {
  val copperCard: Card = Card(1, "Copper", 1, 0, 0, 0, 0, 0)
  val silverCard: Card = Card(2, "Silver", 1, 0, 3, 0, 0, 0)
  val goldCard: Card = Card(3, "gold", 1, 0, 6, 0, 0, 0)
  val propertyCard: Card = Card(4, "property", 2, 2, 0, 0, 0, 0)
  val dukedomCard: Card = Card(5, "dukedom", 2, 5, 0, 0, 0, 0)
  val provinceCard: Card = Card(6, "province", 2, 8, 0, 0, 0, 0)
  val moatCard: Card = Card(8, "moat", 3, 2, 0, 0, 0, 2)
  val villageCard: Card = Card(9, "village", 3, 3, 0, 3, 0, 1)
  val lumberjackCard: Card = Card(10, "lumberjack", 3, 3, 1, 0, 2, 0)
  val cellarCard: Card = Card(11, "cellar", 3, 2, 0, 1, 0, 0)
  val marketCard: Card = Card(12, "market", 3, 5, 1, 1, 1, 1)
  val militiaCard: Card = Card(13, "militia", 3, 4, 0, 0, 2, 0)
  val mineCard: Card = Card(14, "mine", 3, 5, 0, 0, 0, 0)
  val smithyCard: Card = Card(15, "smithy", 3, 4, 0, 0, 0, 3)
  val conversionCard: Card = Card(16, "conversion", 3, 4, 0, 0, 0, 0)
  val workshopCard: Card = Card(17, "workshop", 3, 3, 0, 0, 0, 0)
}