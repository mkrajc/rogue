package org.mech.rougue.core.r.event;

import org.mech.rougue.core.r.event.LoadMapEvent.Handler;

public class LoadMapEvent extends Event<Handler> {
	public interface Handler extends Event.Handler {
		void onAreaLoad(LoadMapEvent event);
	}
	private final String areaId;

	public LoadMapEvent(final String areaId) {
		this.areaId = areaId;
	}

	public String getAreaId() {
		return areaId;
	}

	@Override
	protected void onDispatch(final Handler h) {
		h.onAreaLoad(this);
	}

}
