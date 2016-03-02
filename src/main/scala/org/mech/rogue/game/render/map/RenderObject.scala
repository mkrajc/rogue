package org.mech.rogue.game.render.map

import org.mech.rougue.core.r.model.common.GObject
import org.mech.rougue.core.r.model.geom.Positionable
import org.mech.rougue.core.r.render.RenderId

/**
  * Represents object that can be rendered on map on screen
  */
trait RenderObject extends Positionable with GObject {
  /**
    * Option how to render object
    *
    * @return option
    */
  def getRenderOptions: RenderOption = Normal

  /**
    * Gets id for rendering
    *
    * @return
    */
  def getRenderId: RenderId
}
