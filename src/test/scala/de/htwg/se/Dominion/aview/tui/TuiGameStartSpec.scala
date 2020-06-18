package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.{Controller, GameState}
import org.scalatest.{Matchers, WordSpec}

class TuiGameStartSpec extends WordSpec with Matchers {
  "Dominion Tui with state GameStart" should {
    val controller = new Controller
    val tui = new Tui(controller)

    "have an initial state" in {
      controller.gameState should be (GameState.startScreen)
    }
    "do nothing other than printing a error message on bad input like '5'" in {
      tui.state.processInputLine("5")
      controller.gameState should be (GameState.startScreen)
    }
    "change its gameState to 'endScreen' when one inputs 'q'" in {
      tui.state.processInputLine("q")
      controller.gameState should be (GameState.endScreen)
    }
    "change its gameState to 'twoPlayers' when '2' is put as input" in {
      tui.state.processInputLine("2")
      controller.gameState should be (GameState.twoPlayers)
    }
  }
}
