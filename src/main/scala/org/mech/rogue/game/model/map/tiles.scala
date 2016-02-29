package org.mech.rogue.game.model.map

import org.mech.rougue.core.r.render.RenderId

// represents light characteristic of tile
sealed trait LightType
case object Void extends LightType
case object Light extends LightType

// represents move characteristic of tile
sealed trait Type
case object Ground extends Type
case object Wall extends Type

/**
  * Tile configuration represent defined characteristic of tile
  *
  * @param id id of configuration
  * @param tileType move type of configuration
  * @param lightType light type of configuration
  */
case class TileConfig(id: String, tileType: Type, lightType: LightType)

case class MapTile(config: TileConfig){
  lazy val renderId: RenderId = new RenderId(config.id)
}