package de.htwg.se.Dominion.aview.tui
import de.htwg.se.Dominion.controller.Controller
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends  WordSpec with Matchers{
  "A Dominion Tui" should {
    val controller = new Controller()
    val tui = Tui(controller)
    "have an initial state" in {
      tui.state should be (TuiGameStart(controller, tui))
    }
  }
}
