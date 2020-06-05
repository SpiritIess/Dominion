package de.htwg.se.Dominion.model

import org.scalatest._

import scala.collection.mutable.ListBuffer

class TuiSpec extends WordSpec with Matchers {
  "The Tui" when {
    val tui: Tui = Tui()
    "new" should {
      val tui: Tui = Tui()
      "be in gameState init" in {
        tui.state should be (GameState.init)
      }
      "have an empty Player list" in {
        tui.playerList should be (new ListBuffer[Player])
      }
    }

    "initializing the game" should {
      "switch GameState to initPlayers on input 's'" in {
        tui.handleInit("s")
        tui.state should be (GameState.initPlayers)
      }
    }

    "adding a player" should {
      val testPlayers = tui.addPlayer("Test Player 1")
      "have a List of 1 Player" in {
        testPlayers.length should be(1)
      }
      "have a Player 1" in {
        testPlayers.head.name should be("Test Player 1")
      }
    }

    "adding another player" should {
      val testPlayers = tui.addPlayer("Test Player 2")
      "have a List of 1 Player" in {
        testPlayers.length should be(2)
      }
      "have a Player 1" in {
        testPlayers.head.name should be("Test Player 1")
      }
      "have a Player 2" in {
        testPlayers(1).name should be("Test Player 2")
      }
    }

    "done adding players" should {
      "switch GameState to play on input 'c'" in {
        tui.addPlayer("c")
        tui.state should be (GameState.play)
      }
    }
  }
}