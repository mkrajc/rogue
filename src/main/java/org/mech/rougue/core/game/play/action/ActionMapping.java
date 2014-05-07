package org.mech.rougue.core.game.play.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.mech.rougue.core.engine.handler.input.InputEvent;

public class ActionMapping {

	private static final String CONFIG_NAME = "action.properties";
	private static final String KEY_CONST = "key.properties";

	private Map<Integer, String> actions = new HashMap<Integer, String>();
	private Map<String, Integer> keys = new HashMap<String, Integer>();

	private Properties actionProperties;

	@PostConstruct
	public void init() throws FileNotFoundException, IOException {
		loadKeys();

		actionProperties = new Properties();
		actionProperties.load(ClassLoader.getSystemResourceAsStream(CONFIG_NAME));

		final Set<Entry<Object, Object>> entrySet = actionProperties.entrySet();
		for (Entry e : entrySet) {
			final String type = String.valueOf(((String) e.getKey()).trim());

			String key = (String) e.getValue();
			if (key.contains(",")) {
				String[] codes = key.split(",");
				for (String c : codes) {
					putActionType(c, type);
				}
			} else {
				putActionType(key, type);
			}
		}
	}

	private void putActionType(String c, String actionType) {
		Integer keyValue = keys.get(c);
		if (keyValue == null) {
			throw new NullPointerException("Key [" + c + "] is not defined");
		}
		put(keyValue, actionType);
	}

	private void loadKeys() throws IOException {
		final Properties properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream(KEY_CONST));

		final Set<Entry<Object, Object>> entrySet = properties.entrySet();
		for (Entry e : entrySet) {
			String value = (String) e.getValue();
			String trim = value.trim();
			keys.put((String) e.getKey(), Integer.decode(trim));
		}

	}

	private void put(int value, String type) {
		actions.put(value, type);
	}

	public String getActionType(InputEvent event) {
		return actions.get(event.getCode());
	}

	public String getActionKey(String type) {
		return actionProperties.getProperty(type);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ActionMapping actionMapping = new ActionMapping();
		actionMapping.init();
	}
}
