package org.mech.rougue.core.r.model.map.loader;

import org.mech.rogue.game.model.map.Map;
import org.mech.rougue.core.r.export.AbstractExporter;
import org.mech.rougue.core.r.export.Folders;


public class MapExporter extends AbstractExporter<Map>{

	public MapExporter() {
		super(Folders.MAP_FOLDER, Folders.MAP_DEFAULT_FOLDER);
	}

}
