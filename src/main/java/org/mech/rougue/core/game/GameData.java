package org.mech.rougue.core.game;

import org.mech.rogue.game.model.map.Map;
import org.mech.rougue.core.game.model.player.Player;


public class GameData {
	public Map map;
	public Player player;

	public Map getMap() {
		return map;
	}

	public Player getPlayer() {
		return player;
	}

	public void setMap(final Map map) {
		this.map = map;
	}

}
