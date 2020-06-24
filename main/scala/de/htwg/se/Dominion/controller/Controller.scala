package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.{TuiActionPhase, TuiBuyPhase, TuiPlayerSetup}
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.model.{Board, Card, DiscardPile, DrawPile, Hand, Player}
import de.htwg.se.Dominion.util.Observable

class Controller(var gameState: GameState.Value = GameState.startScreen,
                 var turnState: TurnState.Value = TurnState.actionPhase) extends Observable{

  def printBoard:Unit = {
    val boardString = Board.toString
    println(boardString)
  }

  def startGame: Unit = {
    gameState = GameState.startScreen
    notifyObservers
    println("enter the number of players\n")
  }

  def startTurn:Unit = {
    turnState = TurnState.actionPhase
    notifyObservers
  }
  def actionToBuyPhase(tui:Tui, player: Player): Unit = {
    println("Ending Action-Phase. Beginning Buy-Phase. Please Press Enter to confirm!\n")
    turnState = TurnState.buyingPhase
    tui.state = TuiBuyPhase(this, tui, player)
    notifyObservers
    printBoard
    println(s"Player ${player.name}, has ${player.hand.value} money, which card/s do you want to buy (one by one)?\n")
  }

  def processCardEffect(tui:Tui, player: Player, index: Int):Unit = {
    val (handList, playerDrawPile) = player.hand.handCards(index - 1).processEffect(index - 1, player.hand, player.playerDrawPile)
    notifyObservers
    if (player.hand.mayPlayAction == 0) {
      print(s"No more Actions available for ${player.name}, moving to Buying-Phase, please press 'Enter' to confirm\n")
      actionToBuyPhase(tui, player)
    }
    player.hand = handList
    player.playerDrawPile = playerDrawPile
    notifyObservers
  }

  def firstTurn(tui:Tui): Unit ={
    gameState = GameState.playerOneTurn
    tui.state = TuiActionPhase(this, tui, Dominion.playerList.head)
    println(s"${Dominion.playerList.head}, choose an action-card from your hand, " +
      s"or press '0' to skip to the Buying-Phase and confirm your decision by pressing 'Enter'!\n")
    notifyObservers
  }

  def setUpPlayers(tui:Tui, amount:Int): Unit = {
    gameState = GameState.setUpPlayers
    tui.state = TuiPlayerSetup(this, tui, amount)
    print(Board.toString())
    println("type in the names of the players, using a space as seperator\n")
    notifyObservers
  }

  def quitGame(tui:Tui): Unit = {
    gameState = GameState.endScreen
    //tui.state = TuiEndScreen(this, tui)
    println("you chose to quit the game\n")
    notifyObservers
  }

  def nextPlayer(tui:Tui, player: Player): Unit = {
    if (player.name.equals(Dominion.playerList.head.name)) {
      tui.state = TuiActionPhase(this, tui, Dominion.playerList(1))
      gameState = GameState.playerTwoTurn
      notifyObservers
    }
    else if (player.name.equals(Dominion.playerList(1).name)) {
      tui.state = TuiActionPhase(this, tui, Dominion.playerList(2))
      gameState = GameState.playerThreeTurn
      notifyObservers
    }
    else if (player.name.equals(Dominion.playerList(2).name)) {
      tui.state = TuiActionPhase(this, tui, Dominion.playerList(3))
      gameState = GameState.playerFourTurn
      notifyObservers
    }
    else if (player.name.equals(Dominion.playerList(3).name)) {
      tui.state = TuiActionPhase(this, tui, Dominion.playerList.head)
      gameState = GameState.playerOneTurn
      notifyObservers
    }
  }

  def discardCard(player: Player, card: Card) : Unit = {
    player.playerDiscardPile.discardCard(card)
    notifyObservers
  }
  def discardCards(player:Player, cards: List[Card]) :Unit = {
    player.playerDiscardPile.discardCards(cards)
    notifyObservers
  }
}