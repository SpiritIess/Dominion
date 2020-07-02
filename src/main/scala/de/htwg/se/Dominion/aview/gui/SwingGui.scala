package de.htwg.se.Dominion.aview.gui

import de.htwg.se.Dominion.controller.Controller
import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui(controller: Controller) extends Frame {

//  listenTo(controller)

  title = "Dominion Test"







  contents = new BorderPanel {


  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F

    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo })
      contents += new MenuItem(Action("Redo") { controller.redo })
    }

    contents += new Menu("Highlight") {
      mnemonic = Key.H

    }
    contents += new Menu("Options") {
      mnemonic = Key.O


    }
  }

  visible = true
  redraw

//  reactions += {
//    case event: GridSizeChanged => resize(event.newSize)
//    case event: CellChanged     => redraw
//    case event: CandidatesChanged => redraw
//  }

  def resize(gridSize: Int):Unit = {

    contents = new BorderPanel {

    }
  }
  def redraw:Unit = {
    repaint
  }
}
