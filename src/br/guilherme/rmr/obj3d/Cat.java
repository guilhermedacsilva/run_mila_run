package br.guilherme.rmr.obj3d;

import java.awt.Color;

import br.guilherme.rmr.cfg.Measure;
import br.guilherme.rmr.core.Upgrades;
import br.guilherme.rmr.util.Object3dUtil;

import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

public class Cat extends BasicObject3d {
	private static final SimpleVector ELLIPSOID_COLLISION = new SimpleVector(0.45f,1,1);
	private static final float GRAVITY = 0.05f;
	private float moveSpeed;
	private float rotationSpeed;
	private float rotation;
	private float rotationSin;
	private float pushX;
	private float pushY;
	private int jumpCount;

	public Cat() {
		loadObject("mila", 1);
		Object3dUtil.applyTexture(obj, "mila", Color.GRAY);
		obj.rotateY(Measure.DEGREE_180);
		obj.rotateMesh();
		moveSpeed = 0.25f;
		setCollision(true);
		reset();
	}
	
	public void reset() {
		obj.clearTranslation();
		obj.clearRotation();
		obj.translate(new SimpleVector(0, 0, -8));
		rotation = 0;
		rotationSin = 0;
		rotationSpeed = (float) Math.PI/1000;
		rotationSpeed += Upgrades.get().getTurnSpeedLevel() * rotationSpeed / 3;
		pushX = 0;
		pushY = 0;
		jumpCount = 0;
	}
	
	/**
	 * @param direction -1 0 +1
	 */
	public void walk() {
		float translateX = calculateSlide(moveSpeed*rotationSin);
		float translateY = calculateJump();
		final SimpleVector v = new SimpleVector(translateX,translateY,moveSpeed);
		obj.checkForCollisionEllipsoid(v, ELLIPSOID_COLLISION, 1);
		obj.translate(v);
		if (obj.getTranslation().x < -11 || obj.getTranslation().x > 11) {
			obj.translate(-translateX,0,0);
		}
	}
	
	private float calculateSlide(float translateX) {
		if (pushX != 0) {
			translateX += pushX;
			pushX += -1*Math.signum(pushX)*0.1f;
			if (Math.abs(pushX) < 0.1f) pushX = 0;
		}
		return translateX;
	}
	
	private float calculateJump() {
		float translateY = 0;
		final float currentY = obj.getTranslation().y;
		if (pushY != 0) {
			translateY += pushY;
			pushY += GRAVITY;
			if (currentY > 0) {
				pushY = 0;
				translateY = -currentY;
			}
		} else if (currentY < 0) {
			pushY += GRAVITY;
		}
		return translateY;
	}
	
	public void turnRight() {
		if (rotation < Measure.DEGREE_45) {
			rotate(1);
		}
	}
	
	public void turnLeft() {
		if (rotation > -Measure.DEGREE_45) {
			rotate(-1);
		}
	}
	
	private void rotate(int dir) {
		obj.clearRotation();
		rotation += dir * rotationSpeed;
		rotationSin = (float)Math.sin(rotation);
		obj.rotateY(rotation);
	}
	
	public void setCollision(boolean on) {
		if (on) obj.setCollisionMode(Object3D.COLLISION_CHECK_SELF);
		else obj.setCollisionMode(Object3D.COLLISION_CHECK_NONE);
	}
	
	public void moveSpeedUp() {
		moveSpeed += 0.25f;
	}
	
	public void moveSpeedDown() {
		moveSpeed -= 0.25f;
	}

	public void pushX(float power) {
		pushX = power;
	}
	
	public boolean isOnGround() {
		return obj.getTranslation().y == 0;
	}

	public void jump() {
		if (jumpCount < Upgrades.get().getJumpLevel()
				&& pushY == 0) {
			pushY = -0.65f;
			jumpCount++;
		}
	}
}
