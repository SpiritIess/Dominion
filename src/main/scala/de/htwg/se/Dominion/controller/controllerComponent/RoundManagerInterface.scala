package de.htwg.se.Dominion.controller.controllerComponent
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.TurnState
import de.htwg.se.Dominion.model.cardComponent.{Card, CardSet}
import de.htwg.se.Dominion.model.playerComponent.Player

import scala.collection.mutable.ListBuffer

trait RoundManagerInterface {
//  this: RoundManager =>
  val controller: ControllerInterface
  def processCardEffect(tui: Tui, player: Player, index: Int): Unit
  def processBuy(tui: Tui, player: Player, card: Card): Unit
  def actionToBuyPhase(tui: Tui, player: Player): Unit
  def processCommand(turnState: TurnState.Value, tui: Tui, player: Player,
                     index: Int, card: Card = CardSet.copperCard): (ListBuffer[Player], TurnState.Value)

//  def copy(controller: ControllerInterface = RoundManager.this.controller): RoundManager
}
