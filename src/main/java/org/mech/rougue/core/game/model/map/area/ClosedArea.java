package org.mech.rougue.core.game.model.map.area;

import java.util.List;
import org.mech.terminator.geometry.Position;

public interface ClosedArea extends Area {
	List<Position> getWall();
}
