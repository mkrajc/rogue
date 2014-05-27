package org.mech.rougue.factory;

import java.util.Comparator;

public class OrderComparator<T extends Ordered> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return Integer.valueOf(o1.getOrder()).compareTo(Integer.valueOf(o2.getOrder()));
	}

}
