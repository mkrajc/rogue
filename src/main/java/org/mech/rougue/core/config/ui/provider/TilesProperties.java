package org.mech.rougue.core.config.ui.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.annotation.PostConstruct;

public class TilesProperties {
	private static final String CONFIG_NAME = "tiles.properties";

	private Properties properties;

	@PostConstruct
	public void init() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(ClassLoader.getSystemResourceAsStream(CONFIG_NAME));
	}

	public Properties getProperties() {
		return properties;
	}

	public boolean contains(String id) {
		if(id == null){
			return false;
		}
		return properties.containsKey(id);
	}

	public String getValue(String id) {
		if(id == null){
			System.out.println("bad " + id);
		}
		return properties.getProperty(id);
	}
}
