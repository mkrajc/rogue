package org.mech.rougue.core.r.render;

public class RenderId {
	private String id;
	private String generatedId;

	public RenderId(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setId(final String id) {
		this.id = id;
		this.generatedId = null;
	}

	public void append(final String s) {
		if (s == null) {
			id += "." + s;
		}
	}

	public String getGeneratedId() {
		return generatedId;
	}
	
	public void setGeneratedId(final String generatedId) {
		this.generatedId = generatedId;
	}

	public void invalidate() {
		this.generatedId = null;
	}
}
