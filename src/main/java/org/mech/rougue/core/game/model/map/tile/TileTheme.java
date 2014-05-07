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

	private Map<String, String> map = new HashMap<String, String>();

	private String theme;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
		this.decorator.setup();
		this.map.clear();
	}

	public void put(String id, String finalId) {
		map.put(id, finalId);
	}

	public void updateRenderId(RenderId id) {
		String mapId = id.getIdString();
		String finalId = map.get(mapId);
		if (finalId == null) {
			String[] ids = getIds(id);

			for (String d : ids) {
				if (properties.contains(d)) {
					finalId = d;
					put(mapId, d);
					break;
				}
			}
			LOG.debug(mapId + "=" + finalId);
		}

		if (finalId == null) {
			throw new IllegalArgumentException("not found config for = " + id);
		}

		id.setFinalId(finalId);

	}
	
	private String[] getIds(RenderId id) {
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
