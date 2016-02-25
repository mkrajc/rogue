package org.mech.rougue.ui;

import java.awt.Dimension;
import javax.annotation.PostConstruct;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;
import org.mech.rougue.core.game.start.action.StartGameAction;
import org.mech.rougue.core.game.start.component.StartPanel;
import org.mech.rougue.core.game.start.component.StartPanel.Handler;
import org.mech.rougue.factory.Inject;
import org.mech.terminator.swing.TerminalFrame;

public class GameFrame extends TerminalFrame implements Handler {

	private static final long serialVersionUID = 1L;

	@Inject
	private GameTerminalPanel gameTerminal;

	private StartPanel startPanel;

	private JPanel gamePanel;
	private JPanel leftPanel;
	private JPanel rightPanel;

	@Inject
	private StartGameAction startGameAction;

	public void showWindow() {
		pack();
		setVisible(true);
	}

	@PostConstruct
	public void setup() {
		startPanel = new StartPanel();
		gamePanel = new JPanel(new MigLayout("ins 0, fill"));

		leftPanel = new JPanel(new MigLayout("fill"));
		rightPanel = new JPanel(new MigLayout());
		leftPanel.setVisible(false);
		rightPanel.setVisible(false);

		gamePanel.add(leftPanel, "hidemode 3, aligny top");
		gamePanel.add(gameTerminal, "grow, push");
		gamePanel.add(rightPanel, "hidemode 3");
		
//		final JTextArea textArea = new JTextArea ();
//		final JScrollPane scroll = new JScrollPane (textArea, 
//		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		gamePanel.add(scroll, "height :50:");
		
		startPanel.setHandler(this);
		setContentPane(startPanel);

	}

	public GameFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
	}

	@Override
	public void onGameStart() {
		setContentPane(gamePanel);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				validate();
				repaint();
				gameTerminal.autoRender();
				gameTerminal.requestFocus();

				startGameAction.onAction();
			}
		});

	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

}
