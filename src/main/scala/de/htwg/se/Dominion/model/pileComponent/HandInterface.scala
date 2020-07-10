package de.htwg.se.Dominion.model.pileComponent

import de.htwg.se.Dominion.model.cardComponent.Card

trait HandInterface {

  val handCards: List[Card]
  def toString: String
  def emtpyHand: Hand
  def removeCardFromHand(position: Int): Hand
  def countGold(): Int
}
