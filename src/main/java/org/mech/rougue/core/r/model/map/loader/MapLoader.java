package org.mech.rougue.core.r.model.map.loader;

import java.util.List;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.terminator.geometry.Dimension;

public abstract class MapLoader {

	public Map load(){
		final Map m = new Map(getDimension()); 
		m.registerGameObjects(getGameObjects());
		
		return m;
		
	}

	protected abstract List<GObject> getGameObjects();

	protected abstract Dimension getDimension();
}
