package org.mech.rougue.core.game.model.area.converter;

import static org.mech.rougue.utils.PropertyUtils.*;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.mech.rougue.core.game.model.area.Area;
import org.mech.rougue.factory.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AreaPropertiesConverter {

	private static final Logger LOG = LoggerFactory.getLogger(AreaPropertiesConverter.class);

	public static final String THEME = "theme";


	@Inject
	private List<AreaItemPropertiesConverter> converters;

	public Area load(String fileName, String areaId) {
		final Properties properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream(fileName));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}

		Area a = new Area();

		Properties areaProps = handleArea(properties, a, areaId);

		for (AreaItemPropertiesConverter converter : converters) {
			LOG.debug("processing " + converter.getClass().getSimpleName() + " for area id=" + areaId);
			converter.convert(areaProps, a);
		}

		return a;
	}
	private Properties handleArea(Properties p, Area area, String id) {
		Properties matchingSubset = matchingSubset(p, id + ".", false);
		area.setAreaId(id);
		area.setName(loadOrThrowString(matchingSubset, AreaItemPropertiesConverter.NAME));
		area.setTheme(loadOrThrowString(matchingSubset, THEME));

		return matchingSubset;
	}

	public static void main(String[] args) throws IOException {
		AreaPropertiesConverter areaPropertiesConverter = new AreaPropertiesConverter();
		areaPropertiesConverter.load("maps/testmap.properties", "test_1");
	}

}
