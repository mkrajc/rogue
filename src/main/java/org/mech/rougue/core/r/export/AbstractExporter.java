package org.mech.rougue.core.r.export;


public class AbstractExporter<T extends Exportable> {
	private ObjectImporter<T> defaultImporter;
	private ObjectImporter<T> importer;
	private ObjectExporter<T> defaultExporter;
	private ObjectExporter<T> exporter;

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
		} else if (defaultImporter.fileExist(id)) {
			object = defaultImporter.deserialize(id);
		} else {
			throw new IllegalArgumentException("Default file does not exist for " + defaultImporter.getFilename(id));
		}

		return object;

	}

	public void save(final T obj) {
		exporter.serialize(obj);
	}
	
	public void saveDefault(final T obj) {
		defaultExporter.serialize(obj);
	}
}
