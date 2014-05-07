package org.mech.rougue.core.game.model.map.decorator.tile;

import org.mech.rougue.core.game.model.map.decorator.Decorator.IdDecorator;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractIdDecorator implements IdDecorator {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	int i = 0;
	@Override
	public void decorate(RenderId renderId, Position position) {

		if (!renderId.isDecorated()) {
//			System.out.println(getClass().getSimpleName() + " decorator invoked " + i++ );
			renderId.setOrnament(getOrnament(renderId.getId(), position));
			renderId.setDecorated(true);
		}
	}

	protected abstract String getOrnament(String id, Position position);
}
