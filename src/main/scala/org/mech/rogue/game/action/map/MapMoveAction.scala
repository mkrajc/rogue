package org.mech.rogue.game.action.map

import org.mech.rogue.game.model.map.{Ground, Map, MapTile}
import org.mech.rougue.core.game.GameContext
import org.mech.rougue.core.r.`object`.GObjectUtils
import org.mech.rougue.core.r.model.door.Door
import org.mech.rougue.core.r.model.geom.Move
import org.mech.terminator.geometry.Position

trait MapMovement {
  def move(origin: Position, dest: Position, map: Map, context: GameContext): Option[Position]

  def move(origin: Position, move: Move, map: Map, context: GameContext): Option[Position]

  def place(dest: Position, map: Map, context: GameContext): Option[Position]
}

class NormalMapMovement extends MapMovement {
  private val canMove: (MapTile => Boolean) = { t =>
    t.config.tileType == Ground
  }

  override def move(origin: Position, dest: Position, map: Map, context: GameContext): Option[Position] =
    if (origin == dest) None
    else place(dest, map, context)

  override def move(origin: Position, move: Move, map: Map, context: GameContext): Option[Position] =
    this.move(origin, move.shift(origin), map, context)

  override def place(dest: Position, map: Map, context: GameContext): Option[Position] = {

    // if tile exist allow only ground type
    val tileOk = map.get(dest).exists(canMove)

    val door = GObjectUtils.getObjectOfType(context.getRenderObjects(dest),classOf[Door])
    val objectsOk = (door != null && door.isOpen) || door == null

    if(tileOk && objectsOk) Some(dest)
    else None

  }


}
