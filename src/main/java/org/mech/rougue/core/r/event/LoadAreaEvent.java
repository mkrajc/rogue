package org.mech.rougue.core.r.event;

import org.mech.rougue.core.r.event.LoadAreaEvent.Handler;

public class LoadAreaEvent extends Event<Handler> {
	public interface Handler extends Event.Handler {
		void onAreaLoad(LoadAreaEvent event);
	}
	private final String areaId;

	public LoadAreaEvent(final String areaId) {
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
