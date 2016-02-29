package org.mech.rogue.game.action.map

import org.mech.rogue.game.model.map.{Ground, Map, MapTile}
import org.mech.rougue.core.game.model.map.render.{EnvironmentObject, MapObject}
import org.mech.rougue.core.r.model.door.Door
import org.mech.rougue.core.r.model.geom.Move
import org.mech.terminator.geometry.Position

trait MapMovement {
  def move(origin: Position, dest: Position, map: Map): Option[Position]

  def move(origin: Position, move: Move, map: Map): Option[Position]

  def place(dest: Position, map: Map): Option[Position]
}

class NormalMapMovement extends MapMovement {
  private val canMove: (MapTile => Boolean) = { t =>
    t.config.tileType == Ground
  }

  override def move(origin: Position, dest: Position, map: Map): Option[Position] =
    if (origin == dest) None
    else place(dest, map)

  override def move(origin: Position, move: Move, map: Map): Option[Position] =
    this.move(origin, move.shift(origin), map)

  override def place(dest: Position, map: Map): Option[Position] = {
    // maybe later better grouping of object that can change this property
    val objects: List[EnvironmentObject] = Map.getObjects[EnvironmentObject](map,dest, classOf[EnvironmentObject])

    // if tile exist allow only ground type
    val tileOk = map.get(dest).exists(canMove)
    val objectsOk = objects.forall(_.getConfig.tileType.isFree)

    if(tileOk && objectsOk) Some(dest)
    else None

  }


}
