package org.mech.rougue.core.game.model.map.render;

import org.mech.rougue.factory.Ordered;

public abstract class AbstractOrderedMapRenderer extends AbstractMapRenderer implements Ordered {
	
	@Override
	public int getOrder() {
		return Integer.MAX_VALUE;
	}

}
