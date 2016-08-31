package br.guilherme.rmr.gui2d;

import java.util.Observable;
import java.util.Observer;

import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.core.Score;
import br.guilherme.rmr.event.EventConst;
import br.guilherme.rmr.event.EventManager;
import br.guilherme.rmr.util.Object3dUtil;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Texture;

public class Gui2D implements Observer {
	protected static final Texture TEXTURE_CHARS;
	protected static final Texture TEXTURE_TOPBAR;
	protected static final Texture TEXTURE_BLACK;
	protected static final Texture TEXTURE_WHITE;
	private SimpleGUI currentGui;
	private TopBar topBar;
	private Menu menu;
	
	static {
		TEXTURE_TOPBAR = Object3dUtil.loadTexture("gui_top.png");
		TEXTURE_CHARS = Object3dUtil.loadTexture("chars.png");
		TEXTURE_BLACK = Object3dUtil.loadTexture("black.png");
		TEXTURE_WHITE = Object3dUtil.loadTexture("white.png");
	}

	public Gui2D(Joystick joystick, FrameBuffer buffer) {
		topBar = new TopBar(joystick, buffer);
		menu = new Menu(joystick, buffer);
		currentGui = menu;
		EventManager.getInstance().addObserver(this);
	}

	public void processInputs() {
		currentGui.processInputs();
	}

	public void draw() {
		currentGui.draw();
	}
	
	public static void drawText(FrameBuffer buffer, String text, int x, int y) {
		int currentX = x;
		for (char c : text.toCharArray()) {
			if ((int)c >= 32 && (int)c <= 126) {
				buffer.blit(TEXTURE_CHARS, ((int)c-32)*15, 0, currentX, y, 15, 32, true);
				currentX += 15;
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg.toString().equals(EventConst.MILA_DEAD)
				|| arg.toString().equals(EventConst.NEXT_STAGE)) {
			Score.get().addToTotalPoints();
			currentGui = menu;
		} else if (arg.toString().equals(EventConst.PLAY)) {
			currentGui = topBar;
		}
	}
	
	
}
