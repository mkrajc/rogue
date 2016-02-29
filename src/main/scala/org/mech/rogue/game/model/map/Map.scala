package org.mech.rogue.game.model.map

import org.mech.rogue.game.export.Exportable
import org.mech.rougue.core.game.model.map.MapStats
import org.mech.rougue.core.r.model.area.Area
import org.mech.rougue.core.r.model.common.GObject
import org.mech.terminator.geometry.{Dimension, Position}

import scala.collection.mutable.ListBuffer

class Map(val mapId: String, val size: Dimension, val area: Area, defaultTile: TileConfig) extends Exportable {
  private val gameObjects: ListBuffer[GObject] = new ListBuffer[GObject]
  val tiles: Array[Array[MapTile]] = Array.fill(size.width, size.height)(new MapTile(defaultTile))
  val stats: MapStats = new MapStats(this)

  override def objectId: String = mapId

  def add(gameObject: GObject): Unit = gameObjects += gameObject

  def remove(gameObject: GObject): Unit = gameObjects -= gameObject

  def get(pos: Position): Option[MapTile] = {
    if (size.isIn(pos)) {
      Some(tiles(pos.x)(pos.y))
    } else {
      None
    }
  }

  def objects(): List[GObject] = gameObjects.toList

  def get(x: Int, y: Int): Option[MapTile] = get(Position.at(x, y))

  def put(tile: MapTile, pos: Position): Unit = put(tile, pos.x, pos.y)

  def put(tile: MapTile, x: Int, y: Int): Unit = tiles(x)(y) = tile
}
