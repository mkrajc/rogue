package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.model.map.TileConfig;

public interface EnvironmentObject extends MapObject{
    TileConfig getConfig();
}