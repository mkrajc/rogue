package org.mech.rogue.game.context

import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable

class State {
  private val LOG: Logger = LoggerFactory.getLogger(classOf[State])
  private var paused: Boolean = false
  private var frozen: Boolean = false
  private val stateSwitchMap = new mutable.HashMap[String, Boolean]()

  def isPaused: Boolean = paused

  def setPaused(paused: Boolean) {
    this.paused = paused
    LOG.info("Game pause set to " + paused)
  }

  def isTurnFrozen: Boolean = frozen

  def setTurnFrozen(frozen: Boolean) {
    this.frozen = frozen
    LOG.debug("Turn freeze set to " + frozen)
  }

  def setSwitch(key: String, value: Boolean): Unit = this.stateSwitchMap.put(key, value)

  def getSwitch(key: String, defaultValue: Boolean): Boolean = stateSwitchMap.getOrElse(key, defaultValue)

  def getSwitch(key: String): Boolean = getSwitch(key, defaultValue = false)

  def switchState(key: String): Unit = {
    val value: Boolean = getSwitch(key)
    val newVal: Boolean = !value
    setSwitch(key, newVal)
    LOG.debug("State [" + key + "] set to [" + newVal + "]")
  }
}
