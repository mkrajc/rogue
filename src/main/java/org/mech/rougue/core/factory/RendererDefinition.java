package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.model.light.render.LightMaskRenderer;
import org.mech.rougue.core.game.model.map.render.DefaultMapObjectRenderer;
import org.mech.rougue.core.game.model.map.render.MapObjectOrdererRenderer;
import org.mech.rougue.core.game.model.map.render.SeenMapRenderer;
import org.mech.rougue.core.game.model.map.tile.TileTheme;
import org.mech.rougue.core.game.model.player.render.PlayerRenderer;
import org.mech.rougue.factory.AbstractDefinition;
import org.mech.rougue.playground.MonsterRenderer;

public class RendererDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(LightMaskRenderer.class);
		singleton(SeenMapRenderer.class);
		singleton(MapObjectOrdererRenderer.class);

		singleton(PlayerRenderer.class);
		singleton(MonsterRenderer.class);
		
		singleton(TileTheme.class);
				
		singleton(DefaultMapObjectRenderer.class);
	}

}
