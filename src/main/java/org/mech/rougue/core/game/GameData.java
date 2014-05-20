package org.mech.rougue.core.game;

import org.mech.rougue.core.game.model.area.Area;
import org.mech.rougue.core.game.model.area.stat.AreaStats;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.model.map.Map;

public class GameData {
	private Area area;
	private Player player;
	
	private AreaStats areaStats = new AreaStats();

	public Map getMap() {
		return area.getMap();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public void reset() {
		if (area != null) {
			area.reset();
		}

	}

	public AreaStats getAreaStats() {
		return areaStats;
	}

	public void setAreaStats(AreaStats areaStats) {
		this.areaStats = areaStats;
	}

}
