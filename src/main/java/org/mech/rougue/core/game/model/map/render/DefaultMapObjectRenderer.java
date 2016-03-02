package org.mech.rougue.core.game.model.map.render;

import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.MapTerminalAdapter;

public class DefaultMapObjectRenderer extends AbstractMapObjectRenderer<RenderObject> implements MapObjectRenderer<RenderObject> {

	@Override
	public void doRender(RenderObject mapObject, GameContext context, MapTerminalAdapter mapTerminal) {
		renderObject(mapObject, context, mapTerminal);
	}

	@Override
	public String getType() {
		return "default";
	}


}
