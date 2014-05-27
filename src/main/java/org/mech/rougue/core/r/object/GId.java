package org.mech.rougue.core.r.object;

import java.io.Serializable;

/**
 * It is common game id. Contains hex representation of id.
 * 
 * @author martin.krajc
 *
 */
public class GId implements Serializable{
	private static final long serialVersionUID = 8962058452076499360L;
	
	private int id;
	
	public GId(final int id) {
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
