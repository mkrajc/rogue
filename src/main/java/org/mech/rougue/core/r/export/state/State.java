package org.mech.rougue.core.r.export.state;

import org.mech.rougue.core.r.export.Exportable;

public class State implements Exportable {
	private static final long serialVersionUID = 5455310750758637040L;
	private String playerId;
	private String mapId;
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(final String playerId) {
		this.playerId = playerId;
	}
	public String getMapId() {
		return mapId;
	}
	public void setMapId(final String mapId) {
		this.mapId = mapId;
	}
	@Override
	public String getObjectId() {
		return "state";
	}
}
