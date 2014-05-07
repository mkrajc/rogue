package org.mech.rougue.core.game.play.component.dialog;

import java.awt.Color;
import javax.annotation.PostConstruct;
import org.mech.rougue.factory.Inject;
import org.mech.rougue.lang.LocalizedResourceBundle;
import org.mech.terminator.ITerminal;
import org.mech.terminator.component.Alignment;
import org.mech.terminator.component.Border;
import org.mech.terminator.component.Dialog;
import org.mech.terminator.component.Label;
import org.mech.terminator.component.VerticleAlignment;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.ui.Text;

public class PauseDialog extends Dialog {

	private static final String PAUSE_FORMAT = "@g%s";

	@Inject
	private LocalizedResourceBundle bundle;

	private final Label pause;

	public PauseDialog() {
		setSize(Dimension.of(20, 5));

		pause = new Label();
		pause.setAlignment(Alignment.CENTER);
		pause.setValignment(VerticleAlignment.CENTER);

		addComponent(new Border(pause));
	}

	@PostConstruct
	public void init() {
		final String pauseString = bundle.getMessage("pausedialog.paused");
		Text text = new Text(String.format(PAUSE_FORMAT, pauseString));
		pause.setText(text);
		
		layout();
	}

	@Override
	public void onRender(ITerminal terminal) {
		terminal.put(' ');
		terminal.bg(Color.BLACK);
		super.onRender(terminal);
	}

}
