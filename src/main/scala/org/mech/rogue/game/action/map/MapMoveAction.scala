package org.mech.rogue.game.action.map

import org.mech.rogue.game.model.map.{Ground, Map}
import org.mech.rougue.core.r.model.geom.Move
import org.mech.terminator.geometry.Position

trait MapMovement {
  def move(origin: Position, dest: Position, map: Map): Option[Position]

  def move(origin: Position, move: Move, map: Map): Option[Position]

  def place(dest: Position, map: Map): Option[Position]
}

class NormalMapMovement extends MapMovement {
  override def move(origin: Position, dest: Position, map: Map): Option[Position] =
    if (origin == dest) None
    else place(dest, map)

  override def move(origin: Position, move: Move, map: Map): Option[Position] =
    this.move(origin, move.shift(origin), map)

  override def place(dest: Position, map: Map): Option[Position] = {
    // if tile exist allow only ground type
    map.get(dest).filter(_.config.tileType == Ground).map(_ => dest)
  }


}
