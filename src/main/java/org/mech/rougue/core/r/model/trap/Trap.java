package org.mech.rougue.core.r.model.trap;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderOptions;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.model.common.GObject;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Trap implements GObject, PlayerMoveEvent.Handler, MapObject {

	private static final long serialVersionUID = -3432038588851206027L;

	private final static Logger LOG = LoggerFactory.getLogger(Trap.class);

	private static int ACTIVATED_RENDER = 0 | RenderOptions.MEMORABLE;

	private final GId gId;
	private final RenderId rId;

	private Position position;
	private boolean activated = false;

	private int renderOptions = 0 | RenderOptions.INVISIBLE;

	private Registration playerMoveListener;

	public Trap() {
		this.gId = GIdFactory.next();
		rId = new RenderId(getType());
	}

	@Override
	public GId id() {
		return gId;
	}

	public boolean isActivated() {
		return activated;
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		playerMoveListener = bus.addHandler(PlayerMoveEvent.class, this);
	}

	@Override
	public void onPlayerMove(final PlayerMoveEvent event) {
		if (shouldActivate(event.getCurrentPosition())) {
			activateTrap(event.getContext());
		}
	}

	protected void activateTrap(final GameContext gameContext) {
		LOG.debug("Activated trap [trap=" + this + "]");
		this.activated = true;

		if (playerMoveListener != null) {
			playerMoveListener.unregister();
		}
		renderOptions = ACTIVATED_RENDER;

		doActivateTrap(gameContext);
	}

	protected abstract void doActivateTrap(GameContext context);

	protected boolean shouldActivate(final Position playerPosition) {
		return !activated && getPosition().equals(playerPosition);
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
	public int getRenderOptions() {
		return renderOptions;
	}

	@Override
	public RenderId getRenderId() {
		return rId;
	}

	@Override
	public String getType() {
		return "trap";
	}

}
