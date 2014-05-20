package org.mech.rougue.core.r.model.inv;

import java.util.List;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.render.MapObject;
import org.mech.rougue.core.game.model.map.render.RenderId;
import org.mech.rougue.core.game.model.map.render.RenderOptions;
import org.mech.rougue.core.r.action.object.InteractiveObject;
import org.mech.rougue.core.r.action.object.PlayerMoveOnItemInteraction;
import org.mech.rougue.core.r.context.ContextAwareGObject;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.model.inv.action.TakeItemAction;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Position;

public class ItemMapObject implements MapObject, InteractiveObject, ContextAwareGObject {

	private Position position;
	private final Item item;
	private final RenderId renderId;
	private GameContext context;

	private final PlayerMoveOnItemInteraction interaction;

	public ItemMapObject(final Item item) {
		this.item = item;
		renderId = new RenderId(getType());
		interaction = new PlayerMoveOnItemInteraction(this);
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
	public GId id() {
		return item.id();
	}

	@Override
	public int getRenderOptions() {
		return 0 | RenderOptions.MEMORABLE;
	}

	@Override
	public RenderId getRenderId() {
		return renderId;
	}
	@Override
	public String getType() {
		return "item_" + item.getType().name().toLowerCase();
	}

	@Override
	public List getActions() {
		return CollectionUtils.asList(new TakeItemAction(this, context));
	}

	@Override
	public void onAdd(final GameContext context) {
		this.context = context;
		context.eventBus.addHandler(PlayerMoveEvent.class, interaction);
	}

	@Override
	public void onRemove(final GameContext context) {
		interaction.destroy();
		context.eventBus.removeHandler(interaction);
	}

	public Item getItem() {
		return item;
	}

	public GameContext getContext() {
		return context;
	}

}
