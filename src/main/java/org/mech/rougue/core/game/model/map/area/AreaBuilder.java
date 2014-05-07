package org.mech.rougue.core.game.model.map.area;

import org.mech.rougue.core.game.model.map.area.config.AreaConfig;

public interface AreaBuilder<AREA extends Area, CONFIG extends AreaConfig> {

	AREA build(CONFIG config);

}
