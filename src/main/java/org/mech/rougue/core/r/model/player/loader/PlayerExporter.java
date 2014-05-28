package org.mech.rougue.core.r.model.player.loader;

import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.export.AbstractExporter;
import org.mech.rougue.core.r.export.Folders;

public class PlayerExporter extends AbstractExporter<Player>{

	public PlayerExporter() {
		super(Folders.PLAYER_FOLDER, Folders.PLAYER_DEFAULT_FOLDER);
	}

	
	public static void main(final String[] args) {
		final Player base = new Player();
		new PlayerExporter().saveDefault(base);
	}
}
