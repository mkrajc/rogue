package org.mech.rougue.core.r.render.tile;

import java.util.HashMap;
import java.util.Map;
import org.mech.rougue.core.game.model.map.decorator.themes.IceTempleTileDecorator;
import org.mech.rougue.core.game.model.map.decorator.themes.NorthForestTileDecorator;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TileTheme {

	private static final Logger LOG = LoggerFactory.getLogger(TileTheme.class);

	@Inject
	private TilesProperties properties;

	private Map<String, TileDecorator> decorators = new HashMap<String, TileDecorator>();

	public TileTheme() {
		decorators.put("ice.temple", new IceTempleTileDecorator());
		decorators.put("north.forest", new NorthForestTileDecorator());
	}

	private String theme;

	public String getTheme() {
		return theme;
	}

	public void setTheme(final String theme) {
		this.theme = theme;
	}

	public String themed(final String s, final Position position) {
		String themed = s;

		final TileDecorator tileDecorator = decorators.get(theme);
		if (tileDecorator != null) {
			final String decor = tileDecorator.decorate(s, position);

			if (decor != null) {
				themed += "." + decor;
			}
		}

		themed += "." + theme;

		return themed;
	}

}
