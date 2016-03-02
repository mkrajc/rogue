package org.mech.rogue.game.render.map

sealed trait RenderOption
case object Normal extends RenderOption
/** always render **/
case object Fixed extends RenderOption
/** do not render **/
case object Invisible extends RenderOption
/** render if once seen **/
case object Memorable extends RenderOption
