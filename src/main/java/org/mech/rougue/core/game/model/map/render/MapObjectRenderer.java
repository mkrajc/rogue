package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.GameMapTerminal;

public interface MapObjectRenderer<M extends MapObject> {
	void render(M mapObject, GameContext context, GameMapTerminal mapTerminal);

	String getType();
}
