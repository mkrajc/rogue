package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.model.map.Type;

public interface EnvironmentObject extends MapObject{
    Type getTileType();
}