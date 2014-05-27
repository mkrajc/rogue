package org.mech.rougue.core.factory;

import org.mech.rougue.core.r.render.tile.TilesProperties;
import org.mech.rougue.factory.AbstractDefinition;

public class DecoratorDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(TilesProperties.class);
//		singleton(NorthForestTileDecorator.class);
//		singleton(IceTempleTileDecorator.class);
//		singleton(Decorator.class);
	}
}
