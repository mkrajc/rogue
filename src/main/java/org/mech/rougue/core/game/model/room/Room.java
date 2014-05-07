package org.mech.rougue.core.game.model.room;

import org.mech.rougue.core.game.model.Positionable;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class Room implements Positionable {

	private Dimension size;
	private Position position;
	private String name;

	public Room(Dimension dimension, Position position) {
		this.size = dimension;
		this.position = position;
	}

	public boolean inRoom(Position position) {
		Position relative = position.sub(this.position);
		return size.isIn(relative);
	}

	public Room() {}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
