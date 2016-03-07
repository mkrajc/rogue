package org.mech.rougue.core.r.model.map.gate;

import org.mech.rogue.game.render.map.Fixed$;
import org.mech.rogue.game.render.map.Memorable$;
import org.mech.rogue.game.render.map.RenderObject;
import org.mech.rogue.game.render.map.RenderOption;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.player.PlayerChangeMapRequestEvent;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.model.player.move.PlayerMover;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.core.r.render.RenderId;
import org.mech.terminator.geometry.Position;

public class MapGate implements RenderObject, PlayerMoveEvent.Handler {
	private static final long serialVersionUID = -530954101000740720L;
	private final RenderId renderId;
	private GId gId;

	private Position from;
	private Position to;

	private String fromMapId;
	private String toMapId;

	public MapGate(final Position from, final Position to, final String fromId, final String toId) {
		this();
		this.from = from;
		this.to = to;
		this.fromMapId = fromId;
		this.toMapId = toId;
		this.gId = GIdFactory.next();
	}

	public MapGate() {
		this.renderId = new RenderId(getType());
	}

	@Override
	public Position getPosition() {
		return from;
	}

	@Override
	public void setPosition(final Position position) {
		this.from = position;
	}

	@Override
	public String toString() {
		return "[from=" + fromMapId + ", pos=" + from + "] -> [to=" + toMapId + ", pos=" + to + "]";
	}

	@Override
	public RenderOption getRenderOptions() {
		return Memorable$.MODULE$;
	}

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
		final GameContext ctx = event.getContext();
		final Player player = ctx.getData().getPlayer();
		if (player.getPosition().equals(getPosition())) {
			if (!isOnSameMap()) {
				new PlayerChangeMapRequestEvent(fromMapId, toMapId, player, to).fire(ctx);
			} else {
				new PlayerMover().movePlayer(ctx, player, to, ctx.data.getMap());
			}

		}
	}

	public boolean isOnSameMap() {
		return fromMapId.equals(toMapId);
	}

	public Position getFrom() {
		return from;
	}

	public void setFrom(final Position from) {
		this.from = from;
	}

	public Position getTo() {
		return to;
	}

	public void setTo(final Position to) {
		this.to = to;
	}

	public String getFromMapId() {
		return fromMapId;
	}

	public void setFromMapId(final String fromMapId) {
		this.fromMapId = fromMapId;
	}

	public String getToMapId() {
		return toMapId;
	}

	public void setToMapId(final String toMapId) {
		this.toMapId = toMapId;
	}

	public MapGate twoWay() {
		return new MapGate(to, from, toMapId, fromMapId);
	}

}
