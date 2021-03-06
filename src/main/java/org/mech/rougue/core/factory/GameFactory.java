package org.mech.rougue.core.factory;

import org.mech.rougue.factory.Factory;

public class GameFactory extends Factory {

	public GameFactory() {
		register(new CoreDefinition());
		register(new ActionDefinition());
		register(new RendererDefinition());
		register(new ComponentDefinition());
		register(new DecoratorDefinition());
		register(new LoaderDefinition());
	}

}
