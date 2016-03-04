package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.model.map.render.DefaultMapObjectRenderer;
import org.mech.rougue.core.game.model.map.render.SeenMapRenderer;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.core.r.render.tile.TileTheme;
import org.mech.rougue.factory.AbstractDefinition;

public class RendererDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(LightMask.class);
		singleton(SeenMapRenderer.class);
		singleton(TileTheme.class);
		singleton(DefaultMapObjectRenderer.class);
	}

}
