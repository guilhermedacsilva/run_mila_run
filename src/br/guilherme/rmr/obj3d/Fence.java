package br.guilherme.rmr.obj3d;

import br.guilherme.rmr.collision.CollisionDieListener;

import com.threed.jpct.Object3D;


public class Fence extends BasicObject3d {
	
	public Fence() {
		loadObject("fence", 2, "wood.jpg");
		obj.translate(0,-1,0);
		obj.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
		obj.addCollisionListener(CollisionDieListener.INSTANCE);
	}

}
