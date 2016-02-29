package org.mech.rogue.game.model.map

import org.mech.rougue.core.r.render.RenderId


// represents characteristic of tile
sealed trait Type {
  def isFreeToMove: Boolean
  def isTransparent: Boolean
}
case object Ground extends Type {
  override def isFreeToMove: Boolean = true
  override def isTransparent: Boolean = true
}
case object Wall extends Type {
  override def isFreeToMove: Boolean = false
  override def isTransparent: Boolean = false
}

/**
  * Tile configuration represent defined characteristic of tile
  *
  * @param id id of configuration
  * @param tileType move type of configuration
  */
case class TileConfig(id: String, tileType: Type)


case class MapTile(config: TileConfig){
  lazy val renderId: RenderId = new RenderId(config.id)
}