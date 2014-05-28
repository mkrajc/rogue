package org.mech.rougue.core.r.model.map.loader;

import org.mech.rougue.core.r.export.AbstractExporter;
import org.mech.rougue.core.r.export.Folders;
import org.mech.rougue.core.r.model.map.Map;

public class MapExporter extends AbstractExporter<Map>{

	public MapExporter() {
		super(Folders.MAP_FOLDER, Folders.MAP_DEFAULT_FOLDER);
	}

}
