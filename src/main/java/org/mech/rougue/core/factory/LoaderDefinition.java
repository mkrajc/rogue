package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.state.impl.GameLoader;
import org.mech.rougue.core.r.model.map.loader.GameMapLoader;
import org.mech.rougue.core.r.model.player.loader.GamePlayerLoader;
import org.mech.rougue.factory.AbstractDefinition;

public class LoaderDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(GameLoader.class);
		singleton(GameMapLoader.class);
		singleton(GamePlayerLoader.class);
	}
}
