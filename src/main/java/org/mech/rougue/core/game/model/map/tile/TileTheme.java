package org.mech.rougue.core.game.model.map.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mech.rougue.core.config.ui.provider.TilesProperties;
import org.mech.rougue.core.game.model.map.decorator.Decorator;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TileTheme {

	private static final Logger LOG = LoggerFactory.getLogger(TileTheme.class);

	private final static String DOT = ".";

	@Inject
	private TilesProperties properties;

	@Inject
	private Decorator decorator;

	private final Map<String, String> map = new HashMap<String, String>();

	private String theme;

	public String getTheme() {
		return theme;
	}

	public void setTheme(final String theme) {
		this.theme = theme;
		this.decorator.setup();
		this.map.clear();
	}

	public void put(final String id, final String finalId) {
		map.put(id, finalId);
	}

	public void updateRenderId(final RenderId id) {
		final String mapId = id.getIdString();
		String finalId = map.get(mapId);
		if (finalId == null) {
			final String[] ids = getIds(id);

			for (final String d : ids) {
				if (properties.contains(d)) {
					finalId = d;
					put(mapId, d);
					break;
				}
			}
			LOG.debug(mapId + "=" + finalId);
		}

		if (finalId == null) {
			LOG.warn("Config not found for id=[" + id + "]");
		}

		id.setFinalId(finalId);

	}

	private String[] getIds(final RenderId id) {
		final List<String> ids = new ArrayList<String>();

		final String rendId = id.getId();
		final String orn = id.getOrnament();
		final String suffix = id.getSuffix();

		if (orn != null && suffix != null) {
			ids.add(theme + DOT + rendId + DOT + suffix + DOT + orn);
		}

		if (suffix != null) {
			ids.add(theme + DOT + rendId + DOT + suffix);
		}

		if (orn != null) {
			ids.add(theme + DOT + rendId + DOT + orn);
		}

		ids.add(theme + DOT + rendId);

		if (orn != null) {
			ids.add(id + DOT + orn);
		}

		ids.add(rendId);

		return ids.toArray(new String[ids.size()]);
	}

}
