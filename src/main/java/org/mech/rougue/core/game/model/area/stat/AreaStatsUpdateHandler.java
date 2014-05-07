package org.mech.rougue.core.game.model.area.stat;

import java.util.List;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.room.Room;
import org.mech.rougue.core.r.handler.game.UpdateAwareGObject;
import org.mech.rougue.core.r.object.GId;
import org.mech.rougue.core.r.object.GIdFactory;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Position;

public class AreaStatsUpdateHandler implements UpdateAwareGObject {

	private final GId gId;

	@Inject
	private LocalizedResourceBundle bundle;

	public AreaStatsUpdateHandler() {
		this.gId = GIdFactory.next();
	}

	@Override
	public void update(final GameContext gameContext) {
		updateAreaName(gameContext);
		updateRoomName(gameContext);
	}

	private void updateAreaName(final GameContext gameContext) {
		final String areaNameId = gameContext.getData().getArea().getName();
		gameContext.getData().getAreaStats().setAreaName(bundle.getMessage(areaNameId));
	}

	private void updateRoomName(final GameContext gameContext) {
		final List<Room> rooms = gameContext.getData().getArea().getRooms();
		final Position pp = gameContext.getData().getPlayer().getPosition();

		if (CollectionUtils.isNotEmpty(rooms)) {
			for (final Room room : rooms) {
				if (room.inRoom(pp)) {
					gameContext.getData().getAreaStats().setRoomName(bundle.getMessage(room.getName()));
					break;
				}
			}
		}
	}

	@Override
	public GId id() {
		return gId;
	}

}
