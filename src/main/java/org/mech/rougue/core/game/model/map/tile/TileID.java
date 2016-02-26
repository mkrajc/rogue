package org.mech.rougue.core.game.model.map.tile;


import org.mech.rogue.game.model.map.TileConfig;

public class TileID {

	private String id;
	private String finalId;
	private String ornament;

	public TileID(TileConfig configuration) {
		this.id = configuration.id();
	}

	public TileID(String id) {
		this.id = id;
	}

	public String getFinalId() {
		return finalId;
	}

	public String getOrnament() {
		return ornament;
	}

	public void setOrnament(String ornament) {
		this.ornament = ornament;
	}

	public void setFinalId(String finalId) {
		this.finalId = finalId;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + "" + (ornament == null ? "" : ("." + ornament));
	}


}
