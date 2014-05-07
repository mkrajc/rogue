package org.mech.rougue.core.game.model.map.area;

import org.mech.rougue.core.game.model.map.Map;
import org.mech.terminator.geometry.Dimension;

public interface Area {

	String getId();

	Dimension getSize();

	Map toMap();
}
