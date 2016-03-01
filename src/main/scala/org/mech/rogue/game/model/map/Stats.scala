package org.mech.rogue.game.model.map

import org.mech.terminator.geometry.Position


class Stats(val map: Map) {
  private val seenPositions = Array.fill(map.size.getArea)(false)

  private def index(pos: Position): Int = map.size.width * pos.x + pos.y

  def seen(pos: Position): Boolean = seenPositions(index(pos))

  def see(pos: Position): Unit = seenPositions(index(pos)) = true

  def seenSize: Int = seenPositions.count(identity)

  def reset: Unit = ???

  def seeAll(positions: Iterable[Position]): Unit = positions foreach see
}


