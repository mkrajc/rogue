package org.mech.rougue.core.game.model.area;

import java.util.ArrayList;
import java.util.List;
import org.mech.rougue.core.game.model.map.Map;
import org.mech.rougue.core.game.model.room.Room;
import org.mech.rougue.core.r.model.area.AreaGate;
import org.mech.rougue.core.r.model.door.Door;

public class Area {
	private String areaId;
	private String name;
	private String theme;
	
	List<Room> rooms = new ArrayList<Room>();
	List<Door> doors = new ArrayList<Door>();
	List<AreaGate> gates = new ArrayList<AreaGate>();
	
	Map map;

	public List<Room> getRooms() {
		return rooms;
	}

	public List<Door> getDoors() {
		return doors;
	}

	public Map getMap() {
		return map;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void reset() {
		if (map != null) {
			map.reset();
		}
	}

	public List<AreaGate> getGates() {
		return gates;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
