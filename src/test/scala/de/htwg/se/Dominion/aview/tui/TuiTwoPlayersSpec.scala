package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.{Controller, GameState}
import org.scalatest.{Matchers, WordSpec}

class TuiTwoPlayersSpec extends WordSpec with Matchers {
  "Dominion Tui with state twoPlayers" should {
    val controller = new Controller
    val tui = Tui(controller)
    controller.setGameState(GameState.twoPlayers)
    tui.state = TuiTwoPlayers(controller, tui)

    "have an initial gameState" in {
      controller.gameState should be (GameState.twoPlayers)
    }
    "when given two strings, seperated by a space as input " +
    "it should add the two names to playerList and have the " +
    "gameState set to 'playerOneTurn'" in {
      tui.state.processInputLine("Jakob Carsten")
      Dominion.playerList(0).toString should be("Jakob")
      Dominion.playerList(1).toString should be("Carsten")
      controller.gameState should be (GameState.playerOneTurn)
      tui.state should be (TuiPlayerTurn(controller,tui))
    }
  }
}
