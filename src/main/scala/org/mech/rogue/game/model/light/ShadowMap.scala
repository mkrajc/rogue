package org.mech.rogue.game.model.light

import org.mech.rogue.game.model.map.Map
import org.mech.terminator.geometry.{Dimension, Position}


trait ShadowMap {
  def size: Dimension

  def isShadow(pos: Position): Boolean

  def setShadow(pos: Position, obstacle: Boolean): ShadowMap
}

class ArrayShadowMap(val size: Dimension, val shadows: Array[Boolean]) extends ShadowMap {

  private def index(pos: Position): Int = size.width * pos.x + pos.y

  override def isShadow(pos: Position): Boolean = shadows(index(pos))

  override def setShadow(pos: Position, obstacle: Boolean): ShadowMap = {
    shadows(index(pos)) = obstacle
    this
  }
}

object ShadowMap {
  def create(map: Map): ShadowMap = {
    val lm = Array.fill(map.size.width * map.size.height)(false)
    for{
      x <- 0 to map.size.width
      y <- 0 to map.size.height
      t <- map.get(x,y)
    } yield {
      lm(map.size.width * x + y) = !t.config.tileType.isTransparent
    }

    new ArrayShadowMap(map.size, lm)
  }
}
