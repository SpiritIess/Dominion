package de.htwg.se.Dominion.model

import org.scalatest._

class TuiSpec extends WordSpec with Matchers {
  val tui: Tui = Tui()
  "The Tui" when { "adding a player" should {
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
  }}

}