package org.mech.rougue.core.r.model.door;

import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.game.model.map.render.RenderOptions;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.game.play.action.Action;
import org.mech.rougue.core.game.play.action.ActionDispatcher;
import org.mech.rougue.core.r.action.object.ObjectActionHelper;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.PlayerMoveEvent;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.handler.register.BulkRegistration;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.handler.register.action.ActionOnDispatcherRegistrationHandler;
import org.mech.rougue.core.r.model.map.EnvironmentObject;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.terminator.geometry.GeometryUtils;
import org.mech.terminator.geometry.Position;

public class Door implements MapObject, UpdateAwareGObject, EnvironmentObject, PlayerMoveEvent.Handler {

	public static final String TYPE = "DOOR";

	private final GId gId;
	private final RenderId renderId;

	private Position position;
	private boolean open = true;

	private Registration doorActionsRegistrations;

	public Door() {
		this.renderId = new RenderId(getType());
		this.gId = GIdFactory.next();
	}

	public Door(final Position at) {
		this();
		this.position = at;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(final Position position) {
		this.position = position;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(final boolean open) {
		this.open = open;
		this.renderId.setId(open ? Tiles.DOOR_OPENED_ID : Tiles.DOOR_CLOSED_ID);
		destroyActions();
	}

	public boolean isClosed() {
		return !open;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + id() + "[" + position.toString() + "]";
	}

	@Override
	public int getRenderOptions() {
		return 0 | RenderOptions.FIXED;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public RenderId getRenderId() {
		return renderId;
	}

	@Override
	public GId id() {
		return gId;
	}

	public void open() {
		setOpen(true);
	}

	public void close() {
		setOpen(false);
	}

	@Override
	public void update(final GameContext gameContext) {
		if (playerStoodInFrontOfDoor(gameContext)) {
			refreshActions(gameContext);
		} 
	}

	private boolean playerStoodInFrontOfDoor(final GameContext context) {
		return GeometryUtils.distPyth(context.getData().getPlayer().getPosition(), position) <= 1.0;
	}

	private void destroyActions() {
		if (doorActionsRegistrations != null) {
			doorActionsRegistrations.destroy();
			doorActionsRegistrations = null;
		}
	}

	private void refreshActions(final GameContext context) {
		if (doorActionsRegistrations == null) {
			// TODO let action dispatcher handle transformation
			doorActionsRegistrations = new BulkRegistration<ActionDispatcher, Action>(context.actionDispatcher, ObjectActionHelper.toInputActions(
					this, context.actions.doorActions.getActiveDoorActions(this)), new ActionOnDispatcherRegistrationHandler());
			doorActionsRegistrations.register();
		}
	}

	@Override
	public boolean isPassable() {
		return isOpen();
	}

	@Override
	public boolean isLightObstacle() {
		return isClosed();
	}

	@Override
	public void onPlayerMove(final PlayerMoveEvent event) {
		if (playerStoodInFrontOfDoor(event.getContext())) {
			refreshActions(event.getContext());
		} else {
			destroyActions();
		}
	}

	@Override
	public void registerHandlers(final EventBus bus) {
		bus.addHandler(PlayerMoveEvent.class, this);
	}
}
