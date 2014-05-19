package org.mech.rougue.core.game.model.door;

import org.mech.rougue.core.r.model.door.Door;
import org.mech.terminator.geometry.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockableDoor extends Door implements Lockable {

	private final static Logger LOG = LoggerFactory.getLogger(LockableDoor.class);
	private boolean locked;
	private String keyId;

	public LockableDoor(final String keyId, final Position at) {
		super(at);
		this.keyId = keyId;
	}

	public LockableDoor() {}

	@Override
	public boolean isLocked() {
		return locked;
	}

	@Override
	public boolean isUnlocked() {
		return !locked;
	}

	@Override
	public void lock(final Key key) {
		if (pass(key)) {
			LOG.warn("Key not from this door");
		}

		if (isClosed() && isUnlocked()) {
			locked = true;
			LOG.debug("Door [" + this + "] locked");
		}
	}

	@Override
	public void unlock(final Key key) {
		if (pass(key)) {
			LOG.warn("Key not from this door");
		}

		if (isClosed() && isLocked()) {
			locked = false;
			LOG.debug("Door [" + this + "] unlocked");
		}
	}

	protected boolean pass(final Key key) {
		return key.pass(keyId);
	}
	
	@Override
	public void setOpen(final boolean open) {
		if(open && locked){
			throw new IllegalStateException("doors cannot be locked when opened");
		}
		super.setOpen(open);
	}

	@Override
	public String toString() {
		return "lockable (" + keyId + ") " + super.toString();
	}

	public void setLocked(final boolean locked) {
		this.locked = locked;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(final String keyId) {
		this.keyId = keyId;
	}
	

}
