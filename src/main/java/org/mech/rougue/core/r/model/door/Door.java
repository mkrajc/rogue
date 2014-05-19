package org.mech.rougue.core.r.model.door;

import java.util.List;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.game.model.map.render.RenderOptions;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.action.object.InteractionRegistration;
import org.mech.rougue.core.r.action.object.InteractiveObject;
import org.mech.rougue.core.r.context.ContextAwareGObject;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.PlayerMoveEvent;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.handler.register.Registration;
import org.mech.rougue.core.r.model.door.action.AbstractDoorAction;
import org.mech.rougue.core.r.model.door.action.CloseDoorAction;
import org.mech.rougue.core.r.model.door.action.OpenDoorAction;
import org.mech.rougue.core.r.model.map.EnvironmentObject;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.GeometryUtils;
import org.mech.terminator.geometry.Position;

public class Door implements MapObject, UpdateAwareGObject, EnvironmentObject, PlayerMoveEvent.Handler, InteractiveObject, ContextAwareGObject {

	public static final String TYPE = "DOOR";

	private GameContext context;

	private final GId gId;
	private final RenderId renderId;

	private Position position;
	private boolean open = true;

	private Registration interaction;

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
		return GeometryUtils.distPyth(context.getData().getPlayer().getPosition(), position) == 1.0;
	}

	private void destroyActions() {
		if (interaction != null) {
			interaction.destroy();
			interaction = null;
		}
	}

	private void refreshActions(final GameContext context) {
		if (interaction == null) {
			interaction = new InteractionRegistration(context, this);
			interaction.register();
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

	@Override
	public List<AbstractDoorAction> getActions() {
		final OpenDoorAction openDoorAction = new OpenDoorAction(context, this);
		final CloseDoorAction closeDoorAction = new CloseDoorAction(context, this);
		return (List<AbstractDoorAction>) (isClosed() ? CollectionUtils.asList(openDoorAction) : CollectionUtils.asList(closeDoorAction));
	}

	@Override
	public void onAdd(final GameContext context) {
		this.context = context;
	}

	@Override
	public void onRemove(final GameContext context) {

	}

}
