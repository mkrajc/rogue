package org.mech.rougue.core.r.model.door;

import java.util.List;

import org.mech.rogue.game.model.map.Ground$;
import org.mech.rogue.game.model.map.TileConfig;
import org.mech.rogue.game.model.map.Type;
import org.mech.rogue.game.model.map.Wall$;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.EnvironmentObject;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderOptions;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.action.object.InteractiveObject;
import org.mech.rougue.core.r.action.object.PlayerMoveInFrontOfItemInteraction;
import org.mech.rougue.core.r.context.ContextAwareGObject;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.model.door.action.AbstractDoorAction;
import org.mech.rougue.core.r.model.door.action.CloseDoorAction;
import org.mech.rougue.core.r.model.door.action.OpenDoorAction;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Position;

public class Door implements MapObject, EnvironmentObject, InteractiveObject, ContextAwareGObject {

    public static final String TYPE = "DOOR";

    private GameContext context;

    private final GId gId;
    private final RenderId renderId;

    private Position position;
    private boolean open = true;

    private final PlayerMoveInFrontOfItemInteraction interaction;

    public Door() {
        this.renderId = new RenderId(getType());
        this.gId = GIdFactory.next();
        this.interaction = new PlayerMoveInFrontOfItemInteraction(this);
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
    public List<AbstractDoorAction> getActions() {
        final OpenDoorAction openDoorAction = new OpenDoorAction(context, this);
        final CloseDoorAction closeDoorAction = new CloseDoorAction(context, this);
        return (List<AbstractDoorAction>) (isClosed() ? CollectionUtils.asList(openDoorAction) : CollectionUtils.asList(closeDoorAction));
    }

    @Override
    public void onAdd(final GameContext context) {
        this.context = context;
        this.context.eventBus.addHandler(PlayerMoveEvent.class, interaction);
    }

    @Override
    public void onRemove(final GameContext context) {
        interaction.destroy();
        this.context.eventBus.removeHandler(interaction);
    }

    @Override
    public Type getTileType() {
        return isOpen() ? Ground$.MODULE$ : Wall$.MODULE$;
    }
}
