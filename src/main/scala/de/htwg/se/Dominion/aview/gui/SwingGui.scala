package de.htwg.se.Dominion.aview.gui

import java.awt.BorderLayout

import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.util.{Observable, Observer}
import javax.swing.{JFrame, JScrollPane, JTextArea}

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui(controller: Controller) extends Frame with Observer{
  controller.add(this)
//  listenTo(controller)

  title = "Dominion"

  contents = new GuiGameStart(controller)

//  contents = new BorderPanel {
//  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
//      contents += new MenuItem(Action("Save") {controller.save()})
//      contents += new MenuItem(Action("Load") {controller.load()})
      contents += new MenuItem(Action("Quit") {System.exit(0)})

    }
  }

  visible = true
  centerOnScreen()
  resizable = true
  pack()

  def redraw():Unit = {
    repaint
  }

  override def update: Boolean = {
    contents = SwingGui.getPanel(controller)

    redraw()
    true
  }
}
object SwingGui {
  def getPanel(controller: Controller): Panel = {
    controller.gameState match {
      case GameState.startScreen => new GuiGameStart(controller)
      case GameState.setUpPlayers => new GuiPlayerSetup(controller)
      case GameState.playerTurn => new GuiPlayerTurn(controller)
      case GameState.endScreen => new GuiEndScreen(controller)
    }
  }
}