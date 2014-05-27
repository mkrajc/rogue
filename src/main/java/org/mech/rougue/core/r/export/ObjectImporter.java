package org.mech.rougue.core.r.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.mech.rougue.utils.IOUtils;

public class ObjectImporter extends AbstractObjectManipulator {

	public ObjectImporter(final String folder) {
		super(folder);
	}

	@SuppressWarnings("unchecked")
	public <T extends Exportable> T deserialize(final String objectId, final Class<T> clazz) {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		Object o = null;
		try {
			fileIn = new FileInputStream(getFilename(objectId));
			in = new ObjectInputStream(fileIn);
			o = in.readObject();
		} catch (final IOException i) {
			i.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(fileIn);
		}
		return (T) o;
	}

	public boolean fileExist(final String objectId) {
		final File f = new File(getFilename(objectId));
		return f.exists() && !f.isDirectory();
	}
}
