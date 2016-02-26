package org.mech.rougue.core.game.model.map.generator;

import org.mech.rougue.core.game.model.map.tile.GroundTile;
import org.mech.rougue.core.game.model.map.tile.NewMapTile;
import org.mech.rougue.core.game.model.map.tile.Tiles;
import org.mech.rougue.core.r.model.map.Map;
import org.mech.terminator.geometry.Dimension;

public class MapGenerator {

    public static Map generateRoom(Dimension dim) {
        int height = dim.height;
        int width = dim.width;

        final Map map = new Map(dim);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                NewMapTile tile = new NewMapTile();
                GroundTile groundTile = new GroundTile(Tiles.ROOM_GROUND);
                if ((i == 2 && j % 3 == 0) || j == 0 || i == width - 1 || j == height - 1) {
                    groundTile = new GroundTile(Tiles.ROOM_WALL);
                }
                tile.setGround(groundTile);
                map.put(tile, i, j);
            }
        }

        return map;
    }

}
