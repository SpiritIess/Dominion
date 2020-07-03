package de.htwg.se.Dominion.aview.gui

import java.awt.Color

import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.model.Player
import javax.swing.BorderFactory

import scala.swing.BorderPanel.Position._
import scala.swing._
import scala.swing.event._
import Swing._

class GuiPlayerTurn(controller: Controller) extends BoxPanel(Orientation.Vertical){
  preferredSize = new Dimension(1800, 1200)
  val player: Player = controller.getPlayer match {
    case Some(p) => p
    case None => print("Failed getting a player"); System.exit(-1); Player("?")
  }
  val infoPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Player: " + player)
    contents += new Label("Actions: " + player.mayPlayAction)
    contents += new Label("Buys: " + player.mayBuy)
    contents += new Label("Money: " + player.handValue)
//    font = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 20)
    border = BorderFactory.createLineBorder(Color.BLACK, 2)
  }

  contents += new BorderPanel {
    layout(infoPanel) = North
  }
}
