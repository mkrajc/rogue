package org.mech.rougue.core.config.ui.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mech.rougue.core.config.ui.CharConfig;
import org.mech.rougue.core.config.ui.ColorConfig;
import org.mech.rougue.core.config.ui.TerminalCharConfig;
import org.mech.rougue.factory.Inject;

public class SimpleTerminalConfigProvider implements TerminalCharConfigProvider {
	private static final Pattern pattern = Pattern.compile("([a-z]*)\\((.*)\\)");
	private static final TerminalCharConfig undef = new TerminalCharConfig();

	private static final String ATTR_CHAR = "ch";
	private static final String ATTR_FG = "fg";
	private static final String ATTR_BG = "bg";
	private static final String ATTR_BOLD = "b";

	@Inject
	private TilesProperties properties;

	private Map<String, TerminalCharConfig> loaded = new HashMap<String, TerminalCharConfig>();

	public SimpleTerminalConfigProvider() {
		undef.setCharConfig(new CharConfig(' '));
		undef.setBgConfig(new ColorConfig(200, 46, 71));
	}

	@Override
	public TerminalCharConfig provide(String id) {
		if (!loaded.containsKey(id)) {
			loaded.put(id, load(id));
		}
		return loaded.get(id);
	}

	private TerminalCharConfig load(String id) {
		final String property = properties.getValue(id);
		if (property == null) {
			throw new IllegalArgumentException("Config not found for id. id=" + id);
		}

		final String[] parts = property.split(";");
		final TerminalCharConfig charConfig = new TerminalCharConfig();

		for (String part : parts) {
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

	private ColorConfig loadColor(String rgbValue) {
		final String[] rgb = rgbValue.split(",");
		final ColorConfig colorConfig = new ColorConfig(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2]));

		return colorConfig;
	}
}
