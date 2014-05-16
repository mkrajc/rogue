package org.mech.rougue.core.game.ui;

import java.awt.Font;
import javax.swing.JLabel;
import org.mech.rougue.core.game.GameContext;
import org.mech.rougue.core.r.model.player.stat.PlayerStats;
import org.mech.rougue.core.r.model.stat.Stat;
import org.mech.rougue.core.r.model.stat.StatGroup;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;

public class PlayerStatsPanel extends ShowPanel {
	
	@Inject
	private LocalizedResourceBundle bundle;

	public void setPlayerStats(final GameContext context) {
		removeAll();
		final PlayerStats stats = context.getData().getPlayer().getStats();

		for (final StatGroup group : stats.getGroups()) {
			final JLabel groupLabel = new JLabel(bundle.getMessage(group.getKey()));
			final Font font = groupLabel.getFont();
		
			final Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
			groupLabel.setFont(boldFont);
			
			add(groupLabel, "wrap");
			for (final Stat<?> stat : group.getStats()) {
				add(new JLabel(bundle.getMessage(stat.getKey())), "width 50px");
				add(new JLabel(stat.getValue() == null ? "" : stat.getValue().toString()), "wrap");
			}
		}
	}

}
