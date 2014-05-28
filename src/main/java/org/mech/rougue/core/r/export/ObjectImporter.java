package org.mech.rougue.core.r.export;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.mech.rougue.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectImporter<T> extends AbstractObjectManipulator {
	private final static Logger LOG = LoggerFactory.getLogger(ObjectImporter.class);

	public ObjectImporter(final String folder) {
		super(folder);
	}

	@SuppressWarnings("unchecked")
	public T deserialize(final String objectId) {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		Object o = null;
		try {
			final String filename = getFilename(objectId);
			fileIn = new FileInputStream(filename);
			in = new ObjectInputStream(fileIn);
			o = in.readObject();
			LOG.info("Object deserialized from [" + filename + "]");
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

	public T deserializeWithFile(final File file) {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		Object o = null;
		try {
			fileIn = new FileInputStream(file);
			in = new ObjectInputStream(fileIn);
			o = in.readObject();
			LOG.info("Object deserialized from [" + file.getAbsolutePath() + "]");
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

	public File lastFile() {
		return lastFileModified(folder);
	}

	public static File lastFileModified(final String dir) {
		final File fl = new File(dir);
		final File[] files = fl.listFiles(new FileFilter() {
			@Override
			public boolean accept(final File file) {
				return file.isFile();
			}
		});
		long lastMod = Long.MIN_VALUE;
		File choise = null;
		if (files != null) {
			for (final File file : files) {
				if (file.lastModified() > lastMod) {
					choise = file;
					lastMod = file.lastModified();
				}
			}
		}
		return choise;
	}
}
