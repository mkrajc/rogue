package org.mech.rougue.core.game.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class ShowPanel extends JPanel {

	private static final long serialVersionUID = 5171436284425805268L;

	private JPanel currentParent;

	public ShowPanel() {
		super(new MigLayout());
		
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (currentParent != null) {
						currentParent.setVisible(false);
						currentParent.remove(ShowPanel.this);
					}
				}
			}

		});
	}
	
	public void showInPanel(final JPanel panelToSettle) {
		
		panelToSettle.add(this);
		panelToSettle.setVisible(true);
		panelToSettle.validate();
		panelToSettle.repaint();
		
		this.requestFocus();
		this.currentParent = panelToSettle;
	}
}
