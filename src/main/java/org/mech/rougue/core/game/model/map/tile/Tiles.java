package org.mech.rougue.core.game.model.map.tile;

import org.mech.rogue.game.model.map.Ground$;
import org.mech.rogue.game.model.map.Light$;
import org.mech.rogue.game.model.map.TileConfig;
import org.mech.rogue.game.model.map.Void$;
import org.mech.rogue.game.model.map.Wall$;

public class Tiles {

    public static final String VOID_ID = "void";
    public static final TileConfig VOID = new TileConfig(VOID_ID, Wall$.MODULE$, Void$.MODULE$);


    public static final String ROOM_GROUND_ID = "room.ground";

    public static final String DOOR_OPENED_ID = "door.opened";
    public static final String DOOR_CLOSED_ID = "door.closed";

    public static final String ROOM_WALL_ID = "room.wall";
    public static final String SECRET_ID = "secret";

    public final static TileConfig ROOM_WALL = new TileConfig(ROOM_WALL_ID, Wall$.MODULE$, Void$.MODULE$);
//	public final static TileConfiguration ROOM_WALL_NORTH = new TileConfiguration("room.wall.north", false, true);
//	public final static TileConfiguration ROOM_WALL_SOUTH = new TileConfiguration("room.wall.south", false, true);
//	public final static TileConfiguration ROOM_WALL_WEST = new TileConfiguration("room.wall.west", false, true);
//	public final static TileConfiguration ROOM_WALL_EAST = new TileConfiguration("room.wall.east", false, true);
//	public final static TileConfiguration ROOM_WALL_NW = new TileConfiguration("room.wall.nw", false, true);
//	public final static TileConfiguration ROOM_WALL_NE = new TileConfiguration("room.wall.ne", false, true);
//	public final static TileConfiguration ROOM_WALL_SW = new TileConfiguration("room.wall.sw", false, true);
//	public final static TileConfiguration ROOM_WALL_SE = new TileConfiguration("room.wall.se", false, true);

    public final static TileConfig ROOM_GROUND = new TileConfig(ROOM_GROUND_ID, Ground$.MODULE$, Light$.MODULE$);
    public final static TileConfig ORB = new TileConfig(ROOM_GROUND_ID, Ground$.MODULE$, Void$.MODULE$);


    public static final TileConfig DOOR_OPENED = new TileConfig(DOOR_OPENED_ID, Ground$.MODULE$, Light$.MODULE$);
    public static final TileConfig DOOR_CLOSED = new TileConfig(DOOR_CLOSED_ID, Wall$.MODULE$, Void$.MODULE$);


}
