package org.mech.rougue.core.factory;

import org.mech.rougue.core.game.model.area.converter.AreaDoorPropertiesConverter;
import org.mech.rougue.core.game.model.area.converter.AreaGatePropertiesConverter;
import org.mech.rougue.core.game.model.area.converter.AreaPropertiesConverter;
import org.mech.rougue.core.game.model.area.converter.AreaRoomPropertiesConverter;
import org.mech.rougue.factory.AbstractDefinition;

public class ConvertersDefinition extends AbstractDefinition {

	@Override
	public void definitions() {
		singleton(AreaDoorPropertiesConverter.class);
		singleton(AreaRoomPropertiesConverter.class);
		singleton(AreaPropertiesConverter.class);
		singleton(AreaGatePropertiesConverter.class);
	}

}
