package org.mech.rougue.core.r.render.terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mech.rougue.core.config.ui.CharConfig;
import org.mech.rougue.core.config.ui.ColorConfig;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.rougue.core.r.render.tile.TileTheme;
import org.mech.rougue.core.r.render.tile.TilesProperties;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.utils.Joiner;
import org.mech.terminator.geometry.Position;

public class DefaultTerminalConfigProvider {
	private static final Pattern pattern = Pattern.compile("([a-z]*)\\((.*)\\)");
	private static final TerminalCharConfig undef = new TerminalCharConfig();

	private static final String ATTR_CHAR = "ch";
	private static final String ATTR_FG = "fg";
	private static final String ATTR_BG = "bg";
	private static final String ATTR_BOLD = "b";

	Set<String> print = new HashSet<String>();

	@Inject
	private TilesProperties properties;

	@Inject
	private TileTheme theme;

	private Map<String, TerminalCharConfig> loaded = new HashMap<String, TerminalCharConfig>();

	public DefaultTerminalConfigProvider() {
		undef.setCharConfig(new CharConfig(' '));
		undef.setBgConfig(new ColorConfig(200, 46, 71));
	}

	public TerminalCharConfig provide(final String id) {
		if (!loaded.containsKey(id)) {
			loaded.put(id, load(id));
		}
		return loaded.get(id);
	}

	private TerminalCharConfig load(final String id) {
		final String property = properties.getValue(id);
		if (property == null) {
			throw new IllegalArgumentException("Config not found for id. id=" + id);
		}

		final String[] parts = property.split(";");
		final TerminalCharConfig charConfig = new TerminalCharConfig();

		for (final String part : parts) {
			final Matcher matcher = pattern.matcher(part);
			if (matcher.matches()) {
				final String attr = matcher.group(1);
				final String value = part.substring(attr.length() + 1, part.length() - 1);

				if (ATTR_CHAR.equals(attr)) {
					charConfig.setCharConfig(new CharConfig(value.charAt(0)));
				} else if (ATTR_FG.equals(attr)) {
					charConfig.setFgConfig(loadColor(value));
				} else if (ATTR_BG.equals(attr)) {
					charConfig.setBgConfig(loadColor(value));
				} else if (ATTR_BOLD.equals(attr)) {
					charConfig.setBold(true);
				}

			}
		}

		return charConfig;
	}

	private ColorConfig loadColor(final String rgbValue) {
		final String[] rgb = rgbValue.split(",");
		final ColorConfig colorConfig = new ColorConfig(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2]));

		return colorConfig;
	}

	public void regenerate(final RenderId renderId, final Position mapPosition) {
		if (renderId.getGeneratedId() == null) {
			final String decoratedId = theme.themed(renderId.getId(), mapPosition);
			final List<String> candidates = candidates(decoratedId);

			for (final String candidate : candidates) {
				if (properties.contains(candidate)) {
					renderId.setGeneratedId(candidate);
					break;
				}
			}
		}
	}

	protected List<String> candidates(final String s) {
		final String[] split = s.split("\\.");
		final Joiner on = Joiner.on('.');
		final List<String> candidates = new ArrayList<String>();

		for (int i = 1; i <= split.length; i++) {
			candidates.add(0, on.join(Arrays.copyOfRange(split, 0, i)));
		}

		return candidates;
	}

}
