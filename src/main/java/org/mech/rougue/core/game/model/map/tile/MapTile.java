package org.mech.rougue.core.game.model.map.tile;

import org.mech.rogue.game.model.map.Free$;
import org.mech.rogue.game.model.map.TileConfig;
import org.mech.rogue.game.model.map.Void$;

public class MapTile {
    private TileConfig config;
    private TileID id;
    private boolean covered = true;
    private boolean occupied = false;

    public MapTile(TileConfig t) {
        this.config = t;
        this.id = new TileID(t);
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isFreeForMove() {
        return !isOccupied() && isPassable();
    }

    public boolean isPassable() {
        return getTileConfig().moveType().equals(Free$.MODULE$);
    }

    public boolean isObstacle() {
        return getTileConfig().lightType().equals(Void$.MODULE$);
    }

    public TileConfig getTileConfig() {
        return config;
    }

    public TileID getId() {
        return id;
    }

    public void setTileConfig(TileConfig tileConfiguration) {
        this.config = tileConfiguration;
        this.id = new TileID(tileConfiguration);
    }

}
