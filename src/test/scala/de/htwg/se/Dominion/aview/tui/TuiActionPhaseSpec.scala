package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.TuiPlayerSetup
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.controller.{GameState, TurnState}
import org.scalatest.{Matchers, WordSpec}

class TuiActionPhaseSpec extends WordSpec with Matchers{
  "A Dominion ActionPhaseTui " should{
    Dominion.playerList = Dominion.playerList.drop(Dominion.playerList.size)
    val controller = new Controller
    val tui = Tui(controller)
    controller.gameState = GameState.setUpPlayers
    tui.state = TuiPlayerSetup(controller, tui, 2)
    tui.state.processInputLine("Jakob Karsten")
    val player = Dominion.playerList.head

    "have an initial gameState and an initial TurnState" in {
      controller.gameState should be (GameState.playerTurn)
      controller.turnState should be (TurnState.actionPhase)
    }
    "process the effect of a card, when given the number of the card " +
      "only 'moat':(draw 2), is implemented in processEffect)" in {
      tui.state.processInputLine("1")
      player.hand.handCards.size should be (6)
      player.playerDrawPile.pile.size should be (3)
    }
  }
}