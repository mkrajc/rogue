package org.mech.rougue.playground;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.AbstractMapObjectRenderer;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.play.component.map.GameMapTerminal;

public class MonsterRenderer extends AbstractMapObjectRenderer {

	@Override
	public void doRender(MapObject mapObject, GameContext context, GameMapTerminal mapTerminal) {
		renderObject(mapObject, context, mapTerminal);
	}

	@Override
	public String getType() {
		return "monster";
	}

}
