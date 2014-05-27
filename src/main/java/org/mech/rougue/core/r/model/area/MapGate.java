package org.mech.rougue.core.r.model.area;

import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.LoadMapEvent;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.terminator.geometry.Position;

public class MapGate implements MapObject, PlayerMoveEvent.Handler {
	private static final long serialVersionUID = -530954101000740720L;
	private final RenderId renderId;
	private GId gId;

	private Position position;
	private String mapId;
	private Position destinationPosition;

	public MapGate(final int x, final int y, final String id) {
		this();
		this.position = Position.at(x, y);
		this.mapId = id;
		this.gId = GIdFactory.next();
	}

	public MapGate() {
		this.renderId = new RenderId(getType());
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(final Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "[" + position + "] -> " + mapId + " [" + destinationPosition + "]";
	}
	public Position getDestinationPosition() {
		return destinationPosition;
	}

	public void setDestinationPosition(final Position destinationPosition) {
		this.destinationPosition = destinationPosition;
	}

	@Override
	public int getRenderOptions() {
		return 0;
	}

	@Override
	public String getType() {
		return "gate";
	}

	@Override
	public RenderId getRenderId() {
		return renderId;
	}

	@Override
	public GId id() {
		return gId;
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(PlayerMoveEvent.class, this);
	}

	@Override
	public void onPlayerMove(final PlayerMoveEvent event) {
		if (event.getContext().getData().getPlayer().getPosition().equals(getPosition())) {
			new LoadMapEvent(getMapId()).fire(event.getContext());
		}
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(final String mapId) {
		this.mapId = mapId;
	}

}
