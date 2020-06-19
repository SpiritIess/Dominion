package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.Player
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable.ListBuffer

class TuiTwoPlayersSpec extends WordSpec with Matchers {
  "Dominion Tui with state twoPlayers" should {
    val controller = new Controller
    val tui = Tui(controller)
    controller.gameState = GameState.twoPlayers
    tui.state = TuiTwoPlayers(controller, tui)

    "have an initial gameState" in {
      controller.gameState should be (GameState.twoPlayers)
    }
    "when given two strings, seperated by a space as input " +
    "it should add the two names to playerList and have the " +
    "gameState set to 'playerOneTurn'" in {
      Dominion.playerList = new ListBuffer[Player]
      tui.state.processInputLine("Jakob Karsten")
      Dominion.playerList.head.toString should be("Jakob")
      Dominion.playerList(1).toString should be("Karsten")
      controller.gameState should be (GameState.playerOneTurn)
      tui.state should be (TuiPlayerTurn(controller,tui))
    }
  }
}
