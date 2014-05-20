package org.mech.rougue.core.r.model.stat;


public class IntegerStat extends StatImpl<Integer> implements Stat<Integer> {
	
	public IntegerStat() {
		this.stat = Integer.valueOf(0);
	}

	public void increase(final int increment) {
		setValue(getValue() + increment);
	}

	public void decrease(final int decrement) {
		setValue(getValue() - decrement);
	}
}
