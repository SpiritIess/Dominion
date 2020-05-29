package de.htwg.se.Dominion.model

import org.scalatest._

class TuiSpec extends WordSpec with Matchers {
  val tui: Tui = Tui()
  "The Tui" when { "generating players" should {
    val testPlayers = tui.getPlayerList(List("Test Player1", "Test Player2"))
    "have a List of 2 Players" in {
      testPlayers.length should be(2)
    }
    "have a Player 1" in {
      testPlayers.head.name should be("Test Player1")
    }
    "have a Player 2" in {
      testPlayers(1).name should be("Test Player2")
    }
  }}

}