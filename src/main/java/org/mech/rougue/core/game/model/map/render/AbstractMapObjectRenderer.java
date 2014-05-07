package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.play.component.map.GameMapTerminal;

public abstract class AbstractMapObjectRenderer<M extends MapObject> extends AbstractMapRenderer implements MapObjectRenderer<M> {

	@Override
	public final void render(M mapObject, GameContext context, GameMapTerminal mapTerminal) {
		doRender(mapObject, context, mapTerminal);
	}

	protected abstract void doRender(M mapObject, GameContext context, GameMapTerminal mapTerminal);

	protected void renderObject(M mapObject, GameContext context, GameMapTerminal mapTerminal) {
		render(mapTerminal, mapTerminal.get(mapObject.getPosition()), mapObject.getRenderId(), mapObject.getPosition());
	}

	@Override
	public void render(GameContext context, GameMapTerminal mapTerminal) {
		System.out.println("not supported");
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
