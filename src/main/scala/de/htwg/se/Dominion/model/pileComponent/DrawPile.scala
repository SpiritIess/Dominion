package de.htwg.se.Dominion.model.pileComponent

import de.htwg.se.Dominion.model.cardComponent.Card
import de.htwg.se.Dominion.model.playerComponent.Player

import scala.util.Random

case class DrawPile(pile : List[Card]) extends PlayerPile(pile) {

  def drawOne(player: Player) : (Card, DrawPile) = {
    val temp = ensureDrawCapacity(1,player)
    (temp._1.head,temp._2)
  }


  def drawAdditional(amount: Int) : (List[Card], DrawPile) = (pile.take(amount), DrawPile(pile.takeRight(pile.length - amount)))

  def ensureDrawCapacity(amount: Int, player: Player) : (List[Card], DrawPile) = {
    if(amount > pile.size) {
      val temp = amount - pile.size
      val tempCards = drawAdditional(pile.size)._1
      player.playerDrawPile = refresh(player.playerDiscardPile)
      player.playerDiscardPile.refresh
      val tempTupel = player.playerDrawPile.drawAdditional(temp)
      (tempTupel._1 ::: tempCards, tempTupel._2)
    } else {
      drawAdditional(amount)
    }
  }

  def refresh(discardPile: DiscardPile): DrawPile = DrawPile(discardPile.pile).shuffle

  def shuffle : DrawPile = DrawPile(Random.shuffle(pile))

}
