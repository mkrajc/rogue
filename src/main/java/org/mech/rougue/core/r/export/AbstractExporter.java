package org.mech.rougue.core.r.export;

import org.mech.rogue.game.export.Exportable;

public class AbstractExporter<T extends Exportable> {
	protected ObjectImporter<T> defaultImporter;
	protected ObjectImporter<T> importer;
	protected ObjectExporter<T> defaultExporter;
	protected ObjectExporter<T> exporter;

	public AbstractExporter(final String folder, final String defFolder) {
		defaultImporter = new ObjectImporter<T>(defFolder);
		defaultExporter = new ObjectExporter<T>(defFolder);
		exporter = new ObjectExporter<T>(folder);
		importer = new ObjectImporter<T>(folder);
	}

	public T load(final String id) {
		T object = null;
		if (importer.fileExist(id)) {
			object = importer.deserialize(id);
		} else if (defaultExist(id)) {
			object = loadDefault(id);
		} else {
			throw new IllegalArgumentException("Default file does not exist for " + defaultImporter.getFilename(id));
		}

		return object;

	}

	protected boolean defaultExist(final String id) {
		return defaultImporter.fileExist(id);
	}

	protected T loadDefault(final String id) {
		return defaultImporter.deserialize(id);
	}

	public void save(final T obj) {
		exporter.serialize(obj);
	}
	

	public void quicksave(final T obj) {
		exporter.serialize(obj);
	}

	public void saveDefault(final T obj) {
		defaultExporter.serialize(obj);
	}

	public void saveDefault(final T obj, final String name) {
		defaultExporter.serialize(obj, name);
	}
}
