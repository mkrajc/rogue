package org.mech.rougue.core.config.ui.provider;

import org.mech.rougue.core.config.ui.TerminalCharConfig;

public interface TerminalCharConfigProvider {
	TerminalCharConfig provide(String id);
}
