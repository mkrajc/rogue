package org.mech.rougue.core.r.model.inv;

import java.util.List;

import org.mech.rogue.game.render.map.Memorable$;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rogue.game.render.map.RenderOption;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.action.object.InteractiveObject;
import org.mech.rougue.core.r.action.object.PlayerMoveOnItemInteraction;
import org.mech.rougue.core.r.context.ContextAwareGObject;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.model.inv.action.TakeItemAction;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Position;

public class ItemMapObject implements RenderObject, InteractiveObject, ContextAwareGObject {

	private static final long serialVersionUID = -7874765175139493785L;
	private Position position;
	private final Item item;
	private final RenderId renderId;

	private transient GameContext context;
	private transient PlayerMoveOnItemInteraction interaction;

	public ItemMapObject(final Item item) {
		this.item = item;
		renderId = new RenderId(getType());
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
	public RenderOption getRenderOptions() {
		return Memorable$.MODULE$;
	}

	@Override
	public RenderId getRenderId() {
		return renderId;
	}

	public String getType() {
		return "item." + item.getRenderType();
	}

	@Override
	public List getActions() {
		return CollectionUtils.asList(new TakeItemAction(this, context));
	}

	@Override
	public void onAdd(final GameContext context) {
		this.context = context;
		this.interaction = new PlayerMoveOnItemInteraction(this);
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

	@Override
	public String toString() {
		return item.getName();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof ItemMapObject) {
			return item.equals(((ItemMapObject) obj).getItem());
		}
		return false;
	}

}
