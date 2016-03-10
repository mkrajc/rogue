package org.mech.rogue.game.render.map

import org.mech.rougue.core.game.GameContext
import org.mech.rougue.core.game.model.map.render.RTileRenderer
import org.mech.rougue.core.game.model.map.tile.TileConstants
import org.mech.rougue.core.game.play.component.map.{MapTerminalAdapter, RenderedMapTile}
import org.mech.rougue.core.r.render.RenderId
import org.mech.rougue.core.r.render.terminal.DefaultTerminalConfigProvider
import org.mech.terminator.geometry.Position

class SeenMapRenderer(configProvider: DefaultTerminalConfigProvider) extends Renderer{
  private val covered = new RenderId(TileConstants.COVERED)
  private val coveredDot = new RenderId(TileConstants.COVERED_DOT)
  private val coveredEmpty = new RenderId(TileConstants.COVERED_EMPTY)

  def render(at: Position, context: GameContext, mapTerminal: MapTerminalAdapter): Unit = {
    val rTile: RenderedMapTile = mapTerminal.get(at)
    if (rTile == null) {
      return
    }
    val seen: Boolean = seePosition(context, at)
    if (seen) {
      RTileRenderer.render(configProvider, rTile, rTile.getTile.renderId, at)
    }
    else {
      RTileRenderer.render(configProvider, rTile, getCoveredId(at), at)
    }
  }

  private def seePosition(context: GameContext, position: Position): Boolean = {
    context.getState.getSwitch(SeenMapRenderer.SEE_ALL_SWITCH) || context.getData.getMap.stats.seen(position)
  }

  private def getCoveredId(p: Position): RenderId = {
    if (p.x % 2 == 0 && p.y % 2 == 0 && (p.x + p.y) % 4 == 2) covered
    else if (p.x % 2 == 1 && p.y % 2 == 1) coveredDot
    else coveredEmpty
  }
}

object SeenMapRenderer {
  val SEE_ALL_SWITCH: String = "game.map.see.all"
}
