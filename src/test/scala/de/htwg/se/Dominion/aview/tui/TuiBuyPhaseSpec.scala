package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.{Controller, GameState, RoundManager, TurnState}
import de.htwg.se.Dominion.model.CardSet
import org.scalatest.{Matchers, WordSpec}

class TuiBuyPhaseSpec extends WordSpec with Matchers{
  "A Dominion TuiBuyPhase" should {
    Dominion.playerList = Dominion.playerList.drop(Dominion.playerList.size)
    val controller = new Controller
    val tui = Tui(controller)
    controller.gameState = GameState.setUpPlayers
    tui.state = TuiPlayerSetup(controller, tui, 2)
    tui.state.processInputLine("Jakob Karsten")
    val player = Dominion.playerList.head
    controller.roundManager.actionToBuyPhase(tui,player)
    "have an initial gameState and an initial TurnState" in {
      controller.gameState should be (GameState.playerTurn)
      controller.turnState should be (TurnState.buyingPhase)
    }
    "buy a card, when input = '2', a silverCard, put it on the DiscardPile and subtract its cost " +
      "and make it the next players turn" in {
      val oldValue = player.handValue
      tui.state.processInputLine("2")
      player.playerDiscardPile.pile.length should be (6)
      player.handValue should be (oldValue - CardSet.silverCard.cost)
    }
  }

}
