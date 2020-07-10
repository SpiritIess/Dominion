package de.htwg.se.Dominion.controller.controllerComponent

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.model.cardComponent.Card
import de.htwg.se.Dominion.model.playerComponent.Player
import de.htwg.se.Dominion.util.Observable
import de.htwg.se.Dominion.controller.{GameState, TurnState}

trait ControllerInterface extends Observable{

  val roundManager: RoundManager
  var turnState: TurnState.Value
  var gameState: GameState.Value

  def startGame: Unit

  def notifyInController: Unit

  def startTurn(tui: Tui): Unit

  def getScore: Map[Player, Int]

  def getDeckScore(player: Player): Int

  def getCardScore(player: Player, card: Card): Int

  def actionToBuyPhase(tui: Tui, player: Player): Unit

  def play(tui: Tui, player: Player, index: Int): Unit

  def buyCard(tui: Tui, player: Player, card: Card): Unit

  def getPlayer: Option[Player]

  def isEndGame(tui: Tui): Unit

  def setUpPlayers(tui: Tui, amount: Int): Unit

  def updatePlayerList(tui: Tui, playerString: String): List[Player]

  def quitGame(tui: Tui): Unit

  def refreshPlayer(player: Player): Unit

  def callNextPlayer(tui: Tui, player: Player): Unit

  def playerReset(): Unit

  def callPreviousPlayer(tui: Tui, player: Player): Unit

  def discardCard(player: Player, card: Card, positon: Int): Unit

  def discardCards(player: Player, cards: List[Card]): Unit

  def putOnDiscardPile(player: Player, card: Card): Unit

  def undo: Unit

  def redo: Unit
}
