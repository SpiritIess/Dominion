package de.htwg.se.Dominion.aview.tui
import de.htwg.se.Dominion.controller.controllerComponent.Controller
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends  WordSpec with Matchers{
  "A Dominion Tui" should {
    val controller = new Controller()
    val tui = Tui(controller)
    "have an initial state" in {
      tui.state should be (TuiGameStart(controller, tui))
    }
    /*
    "do nothing yet when calling update method" in {
      val updated =  tui
      tui.update should be ()
    }*/
  }
}
