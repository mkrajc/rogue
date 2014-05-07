package org.mech.rougue.core.game.model.area.converter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.mech.rougue.core.game.model.area.Area;
import org.mech.rougue.utils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AreaItemPropertiesConverter {
	private static final Logger LOG = LoggerFactory.getLogger(AreaItemPropertiesConverter.class);

	public static final String NAME = "name";
	public static final String POSITION = "position";
	public static final String DIMENSION = "dimension";

	protected abstract String getItemKey();
	protected boolean isSingleton() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	protected void convert(Properties areaProperties, Area area) {
		Properties matchSubset = PropertyUtils.matchingSubset(areaProperties, getItemKey() + ".", false);

		if (matchSubset.size() > 0) {
			if (isSingleton()) {
				doConvert(matchSubset, area, "singleton");
			} else {
				Map<String, Properties> itemsProps = new HashMap<String, Properties>();
				String key;
				String itemId;
				for (Enumeration e = matchSubset.propertyNames(); e.hasMoreElements();) {
					key = (String) e.nextElement();
					int dotIdx = key.indexOf('.');
					itemId = key.substring(0, dotIdx);

					Properties itemProp = itemsProps.get(itemId);

					if (itemProp == null) {
						itemProp = PropertyUtils.matchingSubset(matchSubset, itemId, false);
						itemsProps.put(itemId, itemProp);
					}

				}

				for (Entry<String, Properties> e : itemsProps.entrySet()) {
					LOG.trace(getClass().getSimpleName() + " processing [areaId=" + area.getAreaId() + ", id=" + getItemKey() + ", itemId="
							+ e.getKey() + "]");
					doConvert(e.getValue(), area, e.getKey());
				}
			}
		} else {
			LOG.debug("Skipping " + getClass().getSimpleName() + ". Id not found [areaId=" + area.getAreaId() + ", id=" + getItemKey() + "]");
		}
	}

	protected abstract void doConvert(Properties itemProperties, Area area, String id);
}
