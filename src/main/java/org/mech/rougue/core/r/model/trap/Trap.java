package org.mech.rougue.core.r.model.trap;

import org.mech.rogue.game.render.map.Invisible$;
import org.mech.rogue.game.render.map.Memorable$;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rogue.game.render.map.RenderOption;
import org.mech.rougue.core.game.GameContext;
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

public abstract class Trap implements GObject, PlayerMoveEvent.Handler, RenderObject {

	private static final long serialVersionUID = -3432038588851206027L;

	private final static Logger LOG = LoggerFactory.getLogger(Trap.class);

	private static RenderOption ACTIVATED_RENDER = Memorable$.MODULE$;

	private final GId gId;
	private final RenderId rId;

	private Position position;
	private boolean activated = false;

	private RenderOption renderOptions = Invisible$.MODULE$;

	private transient Registration playerMoveListener;

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
	public RenderOption getRenderOptions() {
		return renderOptions;
	}

	@Override
	public RenderId getRenderId() {
		return rId;
	}

	public String getType() {
		return "trap";
	}

}
