package br.guilherme.rmr.obj3d;

import java.awt.Color;

import com.threed.jpct.Object3D;

import br.guilherme.rmr.collision.CollisionDieListener;
import br.guilherme.rmr.util.Object3dUtil;

public class Spike extends BasicObject3d {
	private static final Color SPIKE_COLOR = new Color(50,50,50);
	private static final float MAX_Y = 1.7f;
	private static final float MIN_Y = -0.1f;
	private static final float UP_SPEED = -0.2f;
	private static final float DOWN_SPEED = 0.03f;
	private float direction;

	public Spike() {
		loadObject("spike", 2);
		Object3dUtil.applyTexture(obj, "spike", SPIKE_COLOR);
		obj.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
		obj.addCollisionListener(CollisionDieListener.INSTANCE);
		reset();
	}
	
	public void reset() {
		direction = DOWN_SPEED;
		obj.translate(0, -obj.getTranslation().y + 1, 0);
	}
	
	public void updateObject() {
		obj.translate(0, direction, 0);
		float currentY = obj.getTranslation().y;
		if (currentY > MAX_Y) {
			direction = UP_SPEED;
		} else if (currentY < MIN_Y) {
			direction = DOWN_SPEED;			
		}
	}
	
}
