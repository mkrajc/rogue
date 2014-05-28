package org.mech.rougue.core.r.handler.game.player;

import java.util.Collection;
import org.mech.rougue.core.game.model.player.Player;
import org.mech.rougue.core.r.event.EventBus;
import org.mech.rougue.core.r.event.RebuildLightEvent;
import org.mech.rougue.core.r.event.player.PlayerMoveEvent;
import org.mech.rougue.core.r.handler.game.light.LightMask;
import org.mech.rougue.core.r.model.light.CircleLightSource;
import org.mech.terminator.geometry.Position;

public class PlayerSight extends CircleLightSource implements PlayerMoveEvent.Handler {

	private static final long serialVersionUID = -7023773998751749425L;
	public final static int DEFAULT_PLAYER_SIGHT_RADIUS = 7;
	public static final double DEFAULT_BORDER_INTENSITY = LightMask.DEFAULT_SHADOW_INTENSITY + 0.2;

	private Player player;

	public PlayerSight(final Player player) {
		super(DEFAULT_PLAYER_SIGHT_RADIUS);
		this.player = player;
	}

	@Override
	public void setPosition(final Position position) {
		throw new UnsupportedOperationException("Calling #setPosition() is not supported operation on player sight");
	}

	@Override
	public Position getPosition() {
		return player.getPosition();
	}
	
	// TODO improve stats
	
	@Override
	public void onPlayerMove(final PlayerMoveEvent event) {
		rebuildLights(event.getContext().getData().getMap());
		event.getContext().getData().getMap().getStats().seeAll((Collection) getLights());
	}
	
	@Override
	public void onLightRebuild(final RebuildLightEvent event) {
		super.onLightRebuild(event);
		event.getContext().getData().getMap().getStats().seeAll((Collection) getLights());
	}
	
	@Override
	public void registerHandlers(final EventBus bus) {
		super.registerHandlers(bus);
		bus.addHandler(PlayerMoveEvent.class, this);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "#" + id();
	}

}
