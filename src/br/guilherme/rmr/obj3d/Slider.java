package br.guilherme.rmr.obj3d;

import br.guilherme.rmr.cfg.Measure;
import br.guilherme.rmr.collision.CollisionSliderListener;

import com.threed.jpct.Object3D;

public class Slider extends BasicObject3d {
	public static final int SLIDE_RIGHT = 0;
	public static final int SLIDE_LEFT = 1;

	public Slider() {
		loadObject("slider", 1.5f, "slider.png");
		obj.translate(0, -0.1f, 0);
		obj.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
		obj.addCollisionListener(CollisionSliderListener.RIGHT);
	}
	
	@Override
	protected void applyConfigInternal(Object3dConfig config) {
		if (config.others != null && config.others[0].equals(SLIDE_LEFT)) {
			obj.rotateY(Measure.DEGREE_180);
			obj.removeCollisionListener(CollisionSliderListener.RIGHT);
			obj.addCollisionListener(CollisionSliderListener.LEFT);
		}
	}
	
}
