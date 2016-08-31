package br.guilherme.rmr.gui2d;

import br.guilherme.rmr.cfg.GlobalConfig;
import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.core.Score;

import com.threed.jpct.FrameBuffer;

public class TopBar extends SimpleGUI {
	
	public TopBar(Joystick joystick, FrameBuffer buffer) {
		super(joystick, buffer);
	}
	
	@Override
	public void draw() {
		buffer.blit(Gui2D.TEXTURE_TOPBAR, 0, 0, 0, 0, 1024, 32, false);
		
		String text = "Stage: " + Score.get().getStageNumber();
		Gui2D.drawText(buffer, text, 0, 0);
		
		text = "Points: " + Score.get().getPoints();
		Gui2D.drawText(buffer, text, 150, 0);
		debug();
	}
	
	private void debug() {
		if (GlobalConfig.DEBUG) {
			Gui2D.drawText(buffer, "DEBUG", 700, 0);
		}
	}

}
