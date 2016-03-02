package org.mech.rougue.playground;

import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.AbstractMapObjectRenderer;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;

public class MonsterRenderer extends AbstractMapObjectRenderer {

	@Override
	public void doRender(RenderObject mapObject, GameContext context, MapTerminalAdapter mapTerminal) {
		renderObject(mapObject, context, mapTerminal);
	}

	@Override
	public String getType() {
		return "monster";
	}

}
