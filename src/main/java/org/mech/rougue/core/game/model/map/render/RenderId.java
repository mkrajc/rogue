package org.mech.rougue.core.game.model.map.render;

public class RenderId {

	private String id;
	private String suffix;
	private String ornament;

	private boolean decorated = false;

	private String finalId;

	public RenderId() {}

	public RenderId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		this.finalId = null;
		this.decorated = false;
	}

	public String getOrnament() {
		return ornament;
	}

	public void setOrnament(String ornament) {
		if (this.ornament != null) {
			throw new IllegalArgumentException("ornament=" + this.ornament + " set=" + ornament);
		}
		this.ornament = ornament;
	}

	public String getFinalId() {
		return finalId;
	}

	public void setFinalId(String finalId) {
		this.finalId = finalId;
	}

	public boolean isDecorated() {
		return decorated;
	}

	public void setDecorated(boolean decorated) {
		this.decorated = decorated;
	}

	public String getIdString() {
		return id + (suffix == null ? "" : ("." + suffix)) + (ornament == null ? "" : ("." + ornament));
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
