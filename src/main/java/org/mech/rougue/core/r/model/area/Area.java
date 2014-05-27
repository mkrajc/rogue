package org.mech.rougue.core.r.model.area;

import java.io.Serializable;

public class Area implements Serializable {
	private static final long serialVersionUID = 6838174974582011448L;
	private String areaId;
	private String theme;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(final String areaId) {
		this.areaId = areaId;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(final String theme) {
		this.theme = theme;
	}

}
