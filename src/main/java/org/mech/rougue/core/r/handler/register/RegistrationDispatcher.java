package org.mech.rougue.core.r.handler.register;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDispatcher implements Registration {

	private final List<Registration> regs = new ArrayList<Registration>();

	@Override
	public void register() {
		for (final Registration r : regs) {
			r.register();
		}
	}

	@Override
	public void unregister() {
		for (final Registration r : regs) {
			r.unregister();
		}
	}

	@Override
	public void destroy() {
		for (final Registration r : regs) {
			r.destroy();
		}

		regs.clear();
	}

	@Override
	public boolean isRegistered() {
		boolean allReg = true;
		for (final Registration r : regs) {
			allReg = allReg && r.isRegistered();
			if (!allReg) {
				break;
			}
		}
		return allReg;
	}

	public void add(final Registration current) {
		regs.add(current);
	}

}
