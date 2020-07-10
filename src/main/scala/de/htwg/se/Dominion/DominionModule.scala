package de.htwg.se.Dominion


import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.Dominion.controller.controllerComponent.{Controller, ControllerInterface, RoundManager, RoundManagerInterface}
import net.codingwell.scalaguice.ScalaModule


class DominionModule extends AbstractModule with ScalaModule {
  def configure(): Unit = {
    bind[ControllerInterface].to[Controller]
    bind[RoundManagerInterface].to[RoundManager]
  }

}
