package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.{Controller, GameState, TurnState}
import org.scalatest.{Matchers, WordSpec}

class TuiPlayerTurnSpec extends WordSpec with Matchers{
  "A Dominion Tui with the state 'playerTurn'" should {
    val controller = new Controller
    val tui = Tui(controller)
    controller.gameState = GameState.twoPlayers
    tui.state = TuiTwoPlayers(controller, tui)
    tui.state.processInputLine("Jakob Carsten")
    val player = Dominion.playerList.head

    "have a player whose turn it is" in {
      player should be (Dominion.playerList.head)
    }
    "have an initial gameState and an initial TurnState" in {
      controller.gameState should be (GameState.playerOneTurn)
      controller.turnState should be (TurnState.actionPhase)
    }
    "process the effect of a card, when given the number of the card " +
      "(only card1 works up until now with only 'moat':(draw 2), being implemented in processEffect)" in {
      tui.state.processInputLine("1")
      player.hand.handCards.size should be (6)
      player.playerDrawPile.pile.size should be (3)
    }
  }

}
