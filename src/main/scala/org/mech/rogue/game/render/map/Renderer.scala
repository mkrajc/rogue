package org.mech.rogue.game.render.map

import org.mech.rougue.core.game.GameContext
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter
import org.mech.terminator.geometry.{Position, Rectangle}

class MapRenderer(val renderers: List[Renderer]) {

  def render(context: GameContext, mapTerminal: MapTerminalAdapter): Unit = {
    val rectangle: Rectangle = mapTerminal.getBoundary
    val start: Position = rectangle.getTopLeftPosition
    val end: Position = rectangle.getBottomRightPosition

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

object Rendering {


}
