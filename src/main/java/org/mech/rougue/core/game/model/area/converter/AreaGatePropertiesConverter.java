package org.mech.rougue.core.game.model.area.converter;

import java.util.Properties;
import org.mech.rougue.core.game.model.area.Area;
import org.mech.rougue.core.r.model.area.AreaGate;
import org.mech.rougue.utils.PropertyUtils;

public class AreaGatePropertiesConverter extends AreaItemPropertiesConverter {
	private static final String GATE = "gate";
	private static final String AREA_ID = "areaId";
	private static final String DEST_POSITION = "destination";

	@Override
	protected String getItemKey() {
		return GATE;
	}

	@Override
	protected void doConvert(Properties itemProperties, Area area, String id) {
		AreaGate gate = new AreaGate();

		gate.setPosition(PropertyUtils.loadOrThrowPosition(itemProperties, POSITION));
		gate.setAreaId(PropertyUtils.loadOrThrowString(itemProperties, AREA_ID));
		gate.setDestinationPosition(PropertyUtils.loadOrThrowPosition(itemProperties, DEST_POSITION));

		area.getGates().add(gate);
	}

}
