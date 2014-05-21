package org.mech.rougue.core.r.model.inv;

import org.mech.rougue.core.r.model.common.GObject;

public interface Item extends GObject {
	String getName();
	ItemType getType();
}
