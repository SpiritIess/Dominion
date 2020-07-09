package de.htwg.se.Dominion.aview.gui

import de.htwg.se.Dominion.controller.controllerComponent.Controller
import de.htwg.se.Dominion.model.playerComponent.Player

import scala.swing._
import scala.swing.event._
import Swing._
import scala.collection.parallel.immutable

class GuiEndScreen(controller: Controller) extends BoxPanel(Orientation.Vertical){
//  val myFont = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 15)
  val quitButton = new Button("Quit")
  listenTo(quitButton)

  val scorePanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    val score: Map[Player, Int] = controller.getScore
    val labelList: Iterable[Label] = for (i <- score) yield new Label {
      text = i._1 + ", Points: " + i._2
//      font = myFont
    }
    labelList.foreach(x => contents += x)
  }

  val scorePanelWQuit: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += scorePanel
    contents += quitButton
  }

  contents += new BorderPanel {
    layout(scorePanelWQuit) = BorderPanel.Position.Center
  }

  reactions += {
    case ButtonClicked(`quitButton`) => System.exit(0)
  }
}
