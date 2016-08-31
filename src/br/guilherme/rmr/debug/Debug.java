package br.guilherme.rmr.debug;

import java.awt.event.KeyEvent;

import br.guilherme.rmr.cfg.GlobalConfig;
import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.event.EventConst;
import br.guilherme.rmr.event.EventManager;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

public class Debug {
	protected World world;
	protected Joystick joystick;
	protected StageEditor editor;
	
	public Debug(World world, Joystick joystick) {
		this.world = world;
		this.joystick = joystick;
		editor = new StageEditor(world);
		EventManager.getInstance().addObserver(editor);
	}
	
	public void debugKeys() {
		if (joystick.isPressed(KeyEvent.VK_ESCAPE)) {
			GlobalConfig.DEBUG = !GlobalConfig.DEBUG;
			EventManager.getInstance().registerEvent(EventConst.DEBUG);
		}
		if (GlobalConfig.DEBUG) {
			if (joystick.isHold(KeyEvent.VK_U)) {
				SimpleVector v = world.getCamera().getPosition();
				world.getCamera().setPosition(v.x, v.y-1, v.z);
			}
			if (joystick.isHold(KeyEvent.VK_J)) {
				SimpleVector v = world.getCamera().getPosition();
				world.getCamera().setPosition(v.x, v.y+1, v.z);
			}
			if (joystick.isPressed(KeyEvent.VK_N)) {
				EventManager.getInstance().registerEvent(EventConst.NEXT_STAGE);
			}
		}
	}

	public void shutdown() {
		editor.shutdown();
	}
	
}
