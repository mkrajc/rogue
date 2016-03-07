package org.mech.rogue.game.render.map

import org.mech.rogue.game.model.map.Map
import org.mech.rougue.core.game.GameContext
import org.mech.rougue.core.game.model.map.render.DefaultMapObjectRenderer
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter
import org.mech.rougue.core.r.handler.game.light.LightMask
import org.mech.terminator.geometry.{Position, Rectangle}

import scala.collection.JavaConversions


class ObjectRenderer(val defRenderer: DefaultMapObjectRenderer) extends MapSceneRenderer {
  private val renderStrategyMap = scala.collection.immutable.HashMap[RenderOption, ObjectRenderStrategy](
    Normal -> new NormalStrategy,
    Memorable -> new MemorableStrategy,
    Fixed -> new FixedStrategy,
    Invisible -> new InvisibleStrategy)

  def render(renderObject: RenderObject, context: GameContext, mapTerminal: MapTerminalAdapter): Unit = {
    renderStrategyMap.get(renderObject.getRenderOptions).get.render(defRenderer, renderObject, context, mapTerminal)
  }

  override def render(scene: Rectangle, context: GameContext, mapTerminal: MapTerminalAdapter): Unit = {
    val objs: List[RenderObject] = JavaConversions.asScalaBuffer(context.getRenderObjects).toList
    for (obj <- objs) {
      if (scene.isIn(obj.getPosition))
        render(obj, context, mapTerminal)
    }
  }
}

trait ObjectRenderStrategy {
  def render(defRenderer: DefaultMapObjectRenderer, obj: RenderObject, context: GameContext, mapTerminal: MapTerminalAdapter): Boolean
}

private class MemorableStrategy extends ObjectRenderStrategy {

  override def render(defRenderer: DefaultMapObjectRenderer, obj: RenderObject, context: GameContext, mapTerminal: MapTerminalAdapter): Boolean = {
    val cMap: Map = context.getData.getMap
    val at: Position = obj.getPosition
    if (cMap.stats.seen(at)) {
      defRenderer.render(obj, context, mapTerminal)
      true
    } else false
  }
}

private class NormalStrategy extends ObjectRenderStrategy {
  def render(defRenderer: DefaultMapObjectRenderer, obj: RenderObject, context: GameContext, mapTerminal: MapTerminalAdapter): Boolean = {
    val lightMask: LightMask = context.getLightMask
    val at: Position = obj.getPosition
    if (lightMask.isLighten(at)) {
      defRenderer.render(obj, context, mapTerminal)
      true
    } else false
  }

}

private class FixedStrategy extends ObjectRenderStrategy {

  override def render(defRenderer: DefaultMapObjectRenderer, obj: RenderObject, context: GameContext, mapTerminal: MapTerminalAdapter): Boolean = {
    defRenderer.render(obj, context, mapTerminal)
    true
  }
}

private class InvisibleStrategy extends ObjectRenderStrategy {
  override def render(defRenderer: DefaultMapObjectRenderer, obj: RenderObject, context: GameContext, mapTerminal: MapTerminalAdapter): Boolean = false
}
