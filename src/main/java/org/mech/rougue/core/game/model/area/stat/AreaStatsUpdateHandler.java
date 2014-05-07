package org.mech.rougue.core.game.model.area.stat;

import java.util.List;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.room.Room;
import org.mech.rougue.core.game.play.update.GameUpdateHandler;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Position;

public class AreaStatsUpdateHandler implements GameUpdateHandler {
	@Inject
	private LocalizedResourceBundle bundle;

	@Override
	public void update(GameContext gameContext) {
		updateAreaName(gameContext);
		updateRoomName(gameContext);
	}

	private void updateAreaName(GameContext gameContext) {
		String areaNameId = gameContext.getData().getArea().getName();
		gameContext.getData().getAreaStats().setAreaName(bundle.getMessage(areaNameId));
	}

	private void updateRoomName(GameContext gameContext) {
		List<Room> rooms = gameContext.getData().getArea().getRooms();
		Position pp = gameContext.getData().getPlayer().getPosition();

		if (CollectionUtils.isNotEmpty(rooms)) {
			for (Room room : rooms) {
				if (room.inRoom(pp)) {
					gameContext.getData().getAreaStats().setRoomName(bundle.getMessage(room.getName()));
					break;
				}
			}
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
