package br.guilherme.rmr.stage;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import br.guilherme.rmr.cfg.GlobalConfig;
import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.core.ObjectGroup;
import br.guilherme.rmr.core.Score;
import br.guilherme.rmr.event.EventConst;
import br.guilherme.rmr.event.EventManager;
import br.guilherme.rmr.obj3d.Cat;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;
import com.threed.jpct.util.Light;

public class StageCat extends ObjectGroup implements Observer {
	private Cat cat;
	private boolean hide;
	
	public StageCat(World world, Joystick joystick) {
		super(world, joystick);
		
		Light light = new Light(world);
		light.setIntensity(255, 255, 255);
		light.setPosition(new SimpleVector(100, -100, -100));
		light.setAttenuation(-1);
		
		cat = new Cat();
		cat.addToWorld(world);
		hide = false;
		
		EventManager.getInstance().addObserver(this);
	}
	
	public float getCatZ() {
		return cat.get3D().getTranslation().z;
	}
	
	@Override
	public void processInputs() {
		if (hide) return;
		
		if (joystick.isHold(KeyEvent.VK_LEFT)) {
			cat.turnLeft();				
		}
		if (joystick.isHold(KeyEvent.VK_RIGHT)) {
			cat.turnRight();
		}
		if (joystick.isPressed(KeyEvent.VK_UP)) {
			cat.jump();
		}
		debugKeys();
	}
	
	private void debugKeys() {
		if (GlobalConfig.DEBUG) {
			if (joystick.isPressed(KeyEvent.VK_I)) {
				cat.moveSpeedUp();
			}
			if (joystick.isPressed(KeyEvent.VK_K)) {
				cat.moveSpeedDown();
			}
			if (joystick.isPressed(KeyEvent.VK_O)) {
				SimpleVector v = cat.get3D().getTranslation();
				cat.get3D().translate(0, 0, -v.z);
			}
			if (joystick.isPressed(KeyEvent.VK_L)) {
				SimpleVector v = cat.get3D().getTranslation();
				System.out.println("x: " + v.x + " z: " + v.z);
			}
		}
	}
	
	@Override
	public void updateObjects() {
		if (hide) return;
		
		cat.walk();
		Score.get().setPointsZ((long)cat.get3D().getTranslation().z);
		
		updateCamera();
	}
	
	public void reset() {
		cat.reset();
		updateCamera();
	}
	
	public void hideObjects(boolean hideNow) {
		if (!hide && hideNow) {
			cat.removeFromWorld(world);
		} else if (this.hide && !hideNow) {
			cat.addToWorld(world);
		}
		hide = hideNow;
	}
	
	private void updateCamera() {
		SimpleVector v = world.getCamera().getPosition();
		world.getCamera().setPosition(0, v.y, cat.get3D().getTranslation().z - 12);
		debugCamera();
	}
	
	private void debugCamera() {
		if (GlobalConfig.DEBUG) {
			SimpleVector v = cat.get3D().getTranslation();
			v.x = 0;
			v.z += 8;
			world.getCamera().lookAt(v);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1.toString().equals(EventConst.DEBUG)) {
			cat.setCollision(!GlobalConfig.DEBUG);
		}
		if (arg1.toString().equals(EventConst.SLIDE_LEFT)) {
			cat.pushX(-0.75f);
		}
		if (arg1.toString().equals(EventConst.SLIDE_RIGHT)) {
			cat.pushX(0.75f);
		}
	}
	

}
