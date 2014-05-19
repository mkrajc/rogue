package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;

public interface MapRenderer {
	void render(GameContext context, MapTerminalAdapter mapTerminal);
}
