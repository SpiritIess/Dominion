package de.htwg.se.Dominion.model

import de.htwg.se.Dominion.Dominion

//cardtype: 1-> money, 2-> points, 3-> action
case class Card(cardID: Int, name: String, cardType: Int, cost:Int,
                extraBuys: Int, extraActions: Int, extraGold: Int,
                extraDraws: Int) {

  val player1 = Dominion.playerList(0)
  val player2 = Dominion.playerList(1)
  if(Dominion.playerList.length > 2 && Dominion.playerList.length <5) {
    val player3 = Dominion.playerList(2)
  }
  if(Dominion.playerList.length > 3 && Dominion.playerList.length < 5) {
    val player4 = Dominion.playerList(3)
  }
  def usesAction: Boolean = cardType == 3

  override def toString: String = name

    def processEffect(position : Int, currentHand: List[Card], drawPile: List[Card]): (List[Card], List[Card]) = {
    if (cardType != 3) {
      println("error: only an action card has an effect when played!")
      (currentHand, drawPile)
    }
    else {
      name match {
        case "moat" => {
          val (newCards,newDrawPile) = PlayerDrawPile(drawPile).drawAdditional(extraDraws)
          def remove(playedCard: Card, list: List[Int]) = list diff List(playedCard)
          currentHand = remove(Card, currentHand)
        }
      }
    }
  }
}