package org.mech.rougue.core.r.render;

import java.io.Serializable;

public class RenderId implements Serializable {
	private static final long serialVersionUID = 626516869352450642L;
	
	private String id;
	private transient String generatedId;

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
