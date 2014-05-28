package org.mech.rougue.core.r.model.combat.dmg;

import java.io.Serializable;
import java.util.List;

public class MetaDamage implements Serializable{
	private static final long serialVersionUID = -9150292408182421051L;
	protected List<Damage> damages;

	public List<Damage> getDamages() {
		return damages;
	}

	public Damage getDamage() {
		if (damages.size() == 1) {
			return damages.get(0);
		}
		return null;
	}
}
