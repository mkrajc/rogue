package org.mech.rougue.core.r.object;

/**
 * It is common game id. Contains hex representation of id.
 * 
 * @author martin.krajc
 *
 */
public class GId {
	private int id;
	
	public GId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return Integer.toHexString(id);
	}

	public int toId() {
		return id;
	}

	@Override
	public boolean equals(final Object obj) {
		return ((GId) obj).id == id;
	}
	
}
