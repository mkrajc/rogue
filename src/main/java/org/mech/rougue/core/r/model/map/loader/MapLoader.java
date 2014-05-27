package org.mech.rougue.core.r.model.map.loader;

import org.mech.rougue.core.r.export.Folders;
import org.mech.rougue.core.r.export.ObjectExporter;
import org.mech.rougue.core.r.export.ObjectImporter;
import org.mech.rougue.core.r.model.map.Map;

public class MapLoader {

	private ObjectImporter defaultImporter = new ObjectImporter(Folders.MAP_DEFAULT_FOLDER);
	private ObjectExporter exporter = new ObjectExporter(Folders.MAP_FOLDER);
	private ObjectImporter importer = new ObjectImporter(Folders.MAP_FOLDER);

	public Map load(final String mapId) {
		Map map = null;
		if (importer.fileExist(mapId)) {
			map = importer.deserialize(mapId, Map.class);
		} else if (defaultImporter.fileExist(mapId)) {
			map = defaultImporter.deserialize(mapId, Map.class);
		} else {
			throw new IllegalArgumentException("Default file does not exist for " + defaultImporter.getFilename(mapId));
		}

		return map;

	}

}
