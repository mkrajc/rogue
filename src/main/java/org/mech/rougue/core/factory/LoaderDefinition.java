package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.GameLoader;
import org.mech.rougue.factory.AbstractDefinition;

public class LoaderDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(GameLoader.class);
	}
}
