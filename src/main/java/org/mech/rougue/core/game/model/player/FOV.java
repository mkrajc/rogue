package org.mech.rougue.core.game.model.player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.mech.rougue.core.game.model.map.Map;
import org.mech.terminator.geometry.GeometryUtils;
import org.mech.terminator.geometry.Position;

public class FOV {

	static int multipliers[][] = new int[][] { { 1, 0, 0, -1, -1, 0, 0, 1 }, { 0, 1, -1, 0, 0, -1, 1, 0 }, { 0, 1, 1, 0, 0, -1, -1, 0 },
			{ 1, 0, 0, 1, -1, 0, 0, -1 } };

	static void cast_light(Map map, int x, int y, int radius, int row, float start_slope, float end_slope, int xx, int xy, int yx, int yy,
			Set<Position> lightMapx) {
		if (start_slope < end_slope) {
			return;
		}
		float next_start_slope = start_slope;
		for (int i = row; i <= radius; i++) {
			boolean blocked = false;
			for (int dx = -i, dy = -i; dx <= 0; dx++) {
				float l_slope = (dx - 0.5f) / (dy + 0.5f);
				float r_slope = (dx + 0.5f) / (dy - 0.5f);
				if (start_slope < r_slope) {
					continue;
				} else if (end_slope > l_slope) {
					break;
				}

				int sax = dx * xx + dy * xy;
				int say = dx * yx + dy * yy;
				if ((sax < 0 && Math.abs(sax) > x) || (say < 0 && Math.abs(say) > y)) {
					continue;
				}
				int ax = x + sax;
				int ay = y + say;
				if (ax >= map.getSize().getWidth() || ay >= map.getSize().getHeight()) {
					continue;
				}

				float distance = GeometryUtils.distPyth(dx, dy);
				if (GeometryUtils.isDistanceInCircle(distance, radius)) {
					lightMapx.add(Position.at(ax, ay));
				}

				if (blocked) {
					if (map.get(ax, ay).isObstacle()) {
						next_start_slope = r_slope;
						continue;
					} else {
						blocked = false;
						start_slope = next_start_slope;
					}
				} else if (map.get(ax, ay).isObstacle()) {
					blocked = true;
					next_start_slope = r_slope;
					cast_light(map, x, y, radius, i + 1, start_slope, l_slope, xx, xy, yx, yy, lightMapx);
				}
			}
			if (blocked) {
				break;
			}
		}
	}

	public static Collection<Position> doFov(Map map, Position p, int radius) {
		Set<Position> lightMap = new HashSet<Position>();
		lightMap.add(p);
		for (int i = 0; i < 8; i++) {
			cast_light(map, p.x, p.y, radius, 1, 1.0f, 0.0f, multipliers[0][i], multipliers[1][i], multipliers[2][i], multipliers[3][i], lightMap);
		}
		return lightMap;
	}
}
