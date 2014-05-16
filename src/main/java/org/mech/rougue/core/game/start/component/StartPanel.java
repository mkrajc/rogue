package org.mech.rougue.core.game.start.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel {

	public interface Handler {
		void onGameStart();
	}

	private Handler handler;

	public StartPanel() {
		final JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				if (handler != null) {
					handler.onGameStart();
				}
			}
		});
		add(start);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(final Handler handler) {
		this.handler = handler;
	}
}
