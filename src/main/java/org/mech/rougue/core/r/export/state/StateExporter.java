package org.mech.rougue.core.r.export.state;

import java.io.File;
import org.mech.rougue.core.r.export.AbstractExporter;

public class StateExporter extends AbstractExporter<State> {
	public static final String INITIAL = "initial";

	public StateExporter() {
		super("save/s/", "data/i/");
	}

	public State loadLast() {
		final File lastSave = importer.lastFile();

		if (lastSave == null) {
			return defaultImporter.deserialize(INITIAL);
		} else {
			return importer.deserializeWithFile(lastSave);
		}
	}

	@Override
	public void saveDefault(final State obj) {
		super.saveDefault(obj, INITIAL);
	}

	public static void main(final String[] args) {
		final State state = new State();

		state.setMapId("test_2");
		state.setPlayerId("player");

		new StateExporter().saveDefault(state);
	}
}
