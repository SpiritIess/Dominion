package de.htwg.se.Dominion.aview.gui

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.controllerComponent.Controller
import javax.swing.ImageIcon

import scala.swing._
import scala.swing.event._
import Swing._

class GuiPlayerSetup(tui: Tui, controller: Controller) extends BoxPanel(Orientation.Vertical) {


  val nameTextBox: TextField = new TextField() {
    listenTo(keys)
    reactions += {
      case KeyPressed(_, Key.Enter, _, _) => controller.updatePlayerList(tui, text)
    }
  }

  contents += new FlowPanel() {
    contents += new Label {
      private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/Dominion.jpg").getImage
      private val resize = temp.getScaledInstance(800, 800, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
    }
  }

  contents += new FlowPanel() {
    contents += new Label("type in the names of the players, using a space as seperator") {
    }
  }

  contents += nameTextBox
  contents += Swing.RigidBox(new Dimension(0,20))


//  reactions += {
//    case ButtonClicked(`someButton`) => {
//      controller.setUpPlayers(tui, 2)
//      print("Button 2 pressed")
//    }
//  }

}
