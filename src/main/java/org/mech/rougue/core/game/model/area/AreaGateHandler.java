package org.mech.rougue.core.game.model.area;

import java.util.List;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.game.model.map.MapUtils;
import org.mech.rougue.core.game.play.update.GameUpdateHandler;
import org.mech.rougue.core.r.model.area.AreaGate;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.utils.CollectionUtils;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AreaGateHandler implements GameUpdateHandler {

	private static final Logger LOG = LoggerFactory.getLogger(AreaGateHandler.class);

	@Inject
	private AreaLoader areaLoader;
	
	private Position lastPlayerPosition;

	@Override
	public void update(GameContext gameContext) {
		List<AreaGate> gates = MapUtils.getObjectsOfType(gameContext.getData().getMap(), AreaGate.class);
		
		if (CollectionUtils.isNotEmpty(gates)) {
			Position position = gameContext.getData().getPlayer().getPosition();
			for (AreaGate gate : gates) {
				if (gate.getPosition().equals(position) && !gate.getPosition().equals(lastPlayerPosition)) {
					LOG.info("area gate update " + gate);
					areaLoader.load(gameContext, gate.getAreaId());
					gameContext.getData().getPlayer().setPosition(gate.getDestinationPosition());
					break;
				}
			}
			lastPlayerPosition = gameContext.getData().getPlayer().getPosition();   
		}

	}

	@Override
	public boolean isActive() {
		return true;
	}

}
