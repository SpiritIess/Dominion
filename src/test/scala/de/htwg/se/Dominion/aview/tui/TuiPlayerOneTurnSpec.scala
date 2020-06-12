package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.{Controller, GameState}
import org.scalatest.{Matchers, WordSpec}

class TuiPlayerOneTurnSpec extends WordSpec with Matchers{
  "A Dominion Tui with the state 'playerOneTurn'" should {
    val controller = new Controller
    val tui = new Tui(controller)
    controller.setGameState(GameState.twoPlayers)
    tui.state = TuiTwoPlayers(controller, tui)
    tui.state.processInputLine("Jakob Carsten")
    val player = Dominion.playerList(0)

    "have a player whos turn it is" in {
      player should be (Dominion.playerList(0))
    }
    "have an initial gameState" in {
      controller.gameState should be (GameState.playerOneTurn)
    }
    "process the effect of a card, when given the number of the card " +
      "(only card1 works up until now with only 'moat':(draw 2), being implemented in processEffect)" in {
      tui.state.processInputLine("1")
      player.hand.size should be (6)
      player.playerDrawPile.size should be (3)
    }
  }

}
