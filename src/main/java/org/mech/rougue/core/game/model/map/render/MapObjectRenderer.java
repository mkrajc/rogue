package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;

public interface MapObjectRenderer<M extends RenderObject> {
	void render(M mapObject, GameContext context, MapTerminalAdapter mapTerminal);

	String getType();
}
