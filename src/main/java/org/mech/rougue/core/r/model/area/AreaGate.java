package org.mech.rougue.core.r.model.area;

import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.LoadAreaEvent;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.terminator.geometry.Position;

public class AreaGate implements MapObject, PlayerMoveEvent.Handler  {
	private final RenderId renderId;
	private GId gId;

	private Position position;
	private String areaId;
	private Position destinationPosition;

	public AreaGate(final int x, final int y, final String id) {
		this();
		this.position = Position.at(x, y);
		this.areaId = id;
		this.gId = GIdFactory.next();
	}

	public AreaGate() {
		this.renderId = new RenderId(getType());
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(final String areaId) {
		this.areaId = areaId;
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
		return "[" + position + "] -> " + areaId + " [" + destinationPosition + "]";
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
		return "GATE";
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
			new LoadAreaEvent(getAreaId()).fire(event.getContext());
		}
	}
	
	

}
