package de.htwg.se.Dominion.aview.gui

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.Controller
import javax.swing.ImageIcon

import scala.swing._
import scala.swing.event._
import Swing._

class GuiGameStart(tui: Tui, controller: Controller) extends BoxPanel(Orientation.Vertical){

  val twoPlayerButton: Button = new Button("2 Players") {
  }

  val threePlayerButton: Button = new Button("3 Players") {
  }

  val fourPlayerButton: Button = new Button("4 Players") {
  }
  contents += new FlowPanel() {
    contents += new Label("Welcome to our version of Dominion!")
  }

  contents += new FlowPanel() {
    contents += new Label {
      private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/Dominion.jpg").getImage
      private val resize = temp.getScaledInstance(800, 800, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
    }
  }

  contents += new FlowPanel() {
    contents += new Label("How many Players are you?") {
    }
  }

  contents += new FlowPanel() {
    contents += twoPlayerButton
    contents += threePlayerButton
    contents += fourPlayerButton
  }


  listenTo(twoPlayerButton)
  listenTo(threePlayerButton)
  listenTo(fourPlayerButton)

  reactions += {
//    case ButtonClicked(`twoPlayerButton`) => controller.eval("2")
//    case ButtonClicked(`threePlayerButton`) => controller.eval("3")
//    case ButtonClicked(`fourPlayerButton`) => controller.eval("4")
    case ButtonClicked(`twoPlayerButton`) => {
      controller.setUpPlayers(tui, 2)
      print("Button 2 pressed")
    }
    case ButtonClicked(`threePlayerButton`) => {
      controller.setUpPlayers(tui, 3)
      print("Button 3 pressed")
    }
    case ButtonClicked(`fourPlayerButton`) => {
      controller.setUpPlayers(tui, 4)
      print("Button 4 pressed")
    }
  }
}
