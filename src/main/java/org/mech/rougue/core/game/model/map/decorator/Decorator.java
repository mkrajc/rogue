package org.mech.rougue.core.game.model.map.decorator;

import java.util.List;
import org.mech.rougue.core.game.model.map.Map;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.rougue.core.game.model.map.tile.TileTheme;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Decorator {
	private static final Logger LOG = LoggerFactory.getLogger(Decorator.class);

	@Inject
	private TileTheme theme;

	@Inject
	private List<IdDecorator> decorators;

	private IdDecorator current;

	public interface IdDecorator {
		void decorate(RenderId id, Position position);
		String getId();
	}

	public void decorate(Map map) {
		Dimension mapSize = map.getSize();
		if (current != null) {
			LOG.debug("Decorating map=" + map + ", decorator=" + current);
			for (int i = 0; i < mapSize.width; i++) {
				for (int j = 0; j < mapSize.height; j++) {
					final Position at = Position.at(i, j);
					final List<RenderId> list = map.get(at).getRenderIds();
					for (RenderId id : list) {
						current.decorate(id, at);
					}
				}
			}
		}
	}

	public void setup() {
		String t = theme.getTheme();
		LOG.debug("Decorator setup for theme=" + t);

		for (IdDecorator idDecorator : decorators) {
			if (idDecorator.getId().equals(t)) {
				current = idDecorator;
				LOG.debug("Decorator found theme=" + t + ", decorator=" + current);
				break;
			}
		}

	}

	public IdDecorator getIdDecorator() {
		return current;
	}

}
