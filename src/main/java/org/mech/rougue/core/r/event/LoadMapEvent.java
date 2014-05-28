package org.mech.rougue.core.r.event;

import org.mech.rougue.core.r.event.LoadMapEvent.Handler;

public class LoadMapEvent extends Event<Handler> {
	public interface Handler extends Event.Handler {
		void onMapLoad(LoadMapEvent event);
	}
	private final String mapId;

	public LoadMapEvent(final String mapId) {
		this.mapId = mapId;
	}

	public String getMapId() {
		return mapId;
	}

	@Override
	protected void onDispatch(final Handler h) {
		h.onMapLoad(this);
	}

}
