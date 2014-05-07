package org.mech.rougue.core.factory;

import org.mech.rougue.core.config.ui.provider.TilesProperties;
import org.mech.rougue.core.game.model.map.decorator.Decorator;
import org.mech.rougue.core.game.model.map.decorator.themes.IceTempleTileDecorator;
import org.mech.rougue.core.game.model.map.decorator.themes.NorthForestTileDecorator;
import org.mech.rougue.factory.AbstractDefinition;

public class DecoratorDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(TilesProperties.class);
		singleton(NorthForestTileDecorator.class);
		singleton(IceTempleTileDecorator.class);
		singleton(Decorator.class);
	}
}
