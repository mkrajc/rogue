package org.mech.rogue.game.model.map

// represents light characteristic of tile
sealed trait LightType
case object Void extends LightType
case object Light extends LightType

// represents move characteristic of tile
sealed trait MoveType
case object Free extends MoveType
case object Obstacle extends MoveType

/**
  * Tile configuration represent defined characteristic of tile
  *
  * @param id id of configuration
  * @param moveType move type of configuration
  * @param lightType light type of configuration
  */
case class TileConfig(id: String, moveType: MoveType, lightType: LightType){
  @deprecated
  def isPassable: Boolean = moveType == Free
  @deprecated
  def isObstacle: Boolean = lightType == Void
}