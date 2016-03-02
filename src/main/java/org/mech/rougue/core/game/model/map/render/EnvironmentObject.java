package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.model.map.Type;
import org.mech.rogue.game.render.map.RenderObject;

public interface EnvironmentObject extends RenderObject {
    Type getTileType();
}