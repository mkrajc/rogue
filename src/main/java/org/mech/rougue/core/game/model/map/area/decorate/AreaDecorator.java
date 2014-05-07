package org.mech.rougue.core.game.model.map.area.decorate;

import org.mech.rougue.core.game.model.map.area.Area;

public interface AreaDecorator<AREA extends Area> {
	void decorate(AREA area);
}
