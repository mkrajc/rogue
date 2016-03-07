package org.mech.rogue.game.render.map

import org.mech.rougue.core.game.GameContext
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter
import org.mech.terminator.geometry.{Position, Rectangle}

import scala.collection.mutable.ListBuffer

class MapRenderer(val sceneRenderers: List[MapSceneRenderer]) {

  def render(context: GameContext, mapTerminal: MapTerminalAdapter): Unit = {
    val rectangle: Rectangle = mapTerminal.getBoundary

    for {r <- sceneRenderers} {
      r.render(mapTerminal.getBoundary, context, mapTerminal)
    }

  }
}

class SceneToPositionRenderer(val renderers: List[Renderer]) extends MapSceneRenderer {
  override def render(scene: Rectangle, context: GameContext, mapTerminal: MapTerminalAdapter): Unit = {
    val start = scene.getTopLeftPosition
    val end = scene.getBottomRightPosition

    for {
      i <- start.x to end.x
      j <- start.y to end.y
      r <- renderers
    } yield {
      val pos = Position.at(i, j)
      r.render(pos, context, mapTerminal)
    }
  }
}


trait Renderer {
  def render(pos: Position, context: GameContext, mapTerminal: MapTerminalAdapter)
}

trait MapSceneRenderer {
  def render(scene: Rectangle, context: GameContext, mapTerminal: MapTerminalAdapter)
}

object Rendering {


}
