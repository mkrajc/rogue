package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.GameMapTerminal;

public class DefaultMapObjectRenderer extends AbstractMapObjectRenderer<MapObject> implements MapObjectRenderer<MapObject> {

	@Override
	public void doRender(MapObject mapObject, GameContext context, GameMapTerminal mapTerminal) {
		renderObject(mapObject, context, mapTerminal);
	}

	@Override
	public String getType() {
		return null;
	}


}
