package org.mech.rougue.core.game.model.map.tile;

public class Tiles {
	
	public static final String VOID_ID = "void";
	public static final TileConfiguration VOID = new TileConfiguration(VOID_ID, false, false);
	
	public static final String ROOM_GROUND_ID = "room.ground";
	
	public static final String DOOR_OPENED_ID = "door.opened";
	public static final String DOOR_CLOSED_ID = "door.closed";
	
	public static final String ROOM_WALL_ID = "room.wall";
	public static final String SECRET_ID = "secret";
	
	
	
	public final static TileConfiguration ROOM_WALL = new TileConfiguration(ROOM_WALL_ID, false, true);
//	public final static TileConfiguration ROOM_WALL_NORTH = new TileConfiguration("room.wall.north", false, true);
//	public final static TileConfiguration ROOM_WALL_SOUTH = new TileConfiguration("room.wall.south", false, true);
//	public final static TileConfiguration ROOM_WALL_WEST = new TileConfiguration("room.wall.west", false, true);
//	public final static TileConfiguration ROOM_WALL_EAST = new TileConfiguration("room.wall.east", false, true);
//	public final static TileConfiguration ROOM_WALL_NW = new TileConfiguration("room.wall.nw", false, true);
//	public final static TileConfiguration ROOM_WALL_NE = new TileConfiguration("room.wall.ne", false, true);
//	public final static TileConfiguration ROOM_WALL_SW = new TileConfiguration("room.wall.sw", false, true);
//	public final static TileConfiguration ROOM_WALL_SE = new TileConfiguration("room.wall.se", false, true);

	public final static TileConfiguration ROOM_GROUND = new TileConfiguration(ROOM_GROUND_ID, true);

	public static final TileConfiguration DOOR_OPENED = new TileConfiguration(DOOR_OPENED_ID, true, false);
	public static final TileConfiguration DOOR_CLOSED = new TileConfiguration(DOOR_CLOSED_ID, false, true);

}
