package org.mech.rougue.core.game.model.trap;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.r.object.GId;
import org.mech.terminator.geometry.Position;

public class TileTrap implements Trap {
	public static String TYPE = "TRAP";
	private TrapEffect effect;

	private RenderId id;
	private Position position;
	private boolean discovered;
	private boolean activated = false;

	@Override
	public int getRenderOptions() {
		return 0;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public TrapEffect getEffect() {
		return effect;
	}

	public void setEffect(TrapEffect effect) {
		this.effect = effect;
	}

	@Override
	public void update(GameContext context) {
		if(!activated && isInvoked(context)){
//			context.registerObject(getEffect());
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public String toString() {
		return "Trap [" + position + "]";
	}

	@Override
	public boolean isInvoked(GameContext context) {
//		MapTile mapTile = context.getData().getMap().get(getPosition());
//		activated = mapTile.isOccupied();
		return activated;
	}

	@Override
	public boolean isTrapActivated() {
		return activated;
	}

	@Override
	public RenderId getRenderId() {
		return id;
	}

	@Override
	public GId id() {
		return null;
	}
	
	



}
