package de.htwg.se.Dominion.model

import de.htwg.se.Dominion.model.{Card,PlayerDrawPile,Hand,Pile}
case class ProcessEffect(player:Player, card:Card) {
  var drawPile = player.playerDrawPile
  var handList = player.handList
  if (card.cardType != 3)
    println("error: only an action card has an effect when played")
  else {
    card.name match {
      case "moat" => PlayerDrawPile(drawPile).drawAdditional(card.extraDraws)
      case "village" =>
    }
  }
}
