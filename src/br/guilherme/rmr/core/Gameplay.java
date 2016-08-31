package br.guilherme.rmr.core;

import java.awt.Color;

import br.guilherme.rmr.cfg.GameFPS;
import br.guilherme.rmr.cfg.GlobalConfig;
import br.guilherme.rmr.debug.Debug;
import br.guilherme.rmr.event.EventManager;
import br.guilherme.rmr.gui2d.Gui2D;
import br.guilherme.rmr.stage.StageManager;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

public class Gameplay {
	private static Gameplay instance = new Gameplay();
	private World world;
	private FrameBuffer buffer;
	private Joystick joystick;
	private ObjectManager objectManager;
	private Gui2D gui2d;
	private Debug debug;
	
	public static void main(String[] args) {
		Gameplay.instance.play();
	}
	
	public static Gameplay getInstance() {
		return instance;
	}

	private Gameplay() {}
	
	private void play() {
		setupEngine();
		setupObjects();
		run();
		shutdown();	
	}

	private void setupEngine() {
		buffer = GlobalConfig.createFrameBuffer();
		world = GlobalConfig.createWorld();
		joystick = new Joystick();
		gui2d = new Gui2D(joystick, buffer);
		StageManager stageManager = new StageManager(world, joystick);
		objectManager = new ObjectManager();
		objectManager.addObjectGroup(stageManager);
		debug = new Debug(world, joystick);
	}

	private void setupObjects() {
		world.getCamera().setPosition(0, -20, -20);
		world.getCamera().lookAt(SimpleVector.ORIGIN);
		world.buildAllObjects();
	}

	private void run() {
		final GameFPS gameFPS = new GameFPS(30);
		while (!org.lwjgl.opengl.Display.isCloseRequested()
				&& !GlobalConfig.EXIT) {
			joystick.update();
			processInputs();
			updateObjects();
			showBuffer();
			EventManager.getInstance().fireEvents();
			gameFPS.sleep();
		}
	}
	
	private void processInputs() {
		debug.debugKeys();
		objectManager.processInputs();
		gui2d.processInputs();
	}
	
	private void updateObjects() {
		objectManager.updateObjects();
	}
	
	private void showBuffer() {
		buffer.clear(Color.CYAN);
		world.renderScene(buffer);
		world.draw(buffer);
		buffer.update();
		gui2d.draw();
		buffer.displayGLOnly();
	}

	private void shutdown() {
		GlobalConfig.shutdownEngine(buffer);
		if (debug != null) debug.shutdown();
	}
	
}

