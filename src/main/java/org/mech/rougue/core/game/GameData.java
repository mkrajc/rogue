package org.mech.rougue.core.game;

import org.mech.rogue.game.model.light.ShadowMap;
import org.mech.rogue.game.model.light.ShadowMap$;
import org.mech.rogue.game.model.map.Map;
import org.mech.rougue.core.game.model.player.Player;


public class GameData {
	private Map map;
	public Player player;
	public ShadowMap shadowMap;

	public Map getMap() {
		return map;
	}

	public Player getPlayer() {
		return player;
	}

	public void setMap(final Map map) {
		this.map = map;
		this.shadowMap = ShadowMap$.MODULE$.create(map);
	}

}
