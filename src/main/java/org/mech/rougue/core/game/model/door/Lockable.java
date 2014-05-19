package org.mech.rougue.core.game.model.door;

public interface Lockable {
	boolean isLocked();
	boolean isUnlocked();

	void lock(Key key);
	void unlock(Key key);

}
