package br.guilherme.rmr.obj3d;

import br.guilherme.rmr.collision.CollisionDieListener;
import br.guilherme.rmr.util.Object3dUtil;

import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

public abstract class BasicObject3d {
	protected Object3dConfig config;
	protected Object3D obj;
	
	protected void loadObject(String name, float scale) {
		loadObject(name, scale, null);
	}
	
	protected void loadObject(String name, float scale, String textureName) {
		obj = Object3dUtil.load3ds(name);
		obj.setScale(scale);
		if (textureName != null) Object3dUtil.applyTexture(obj, textureName);
	}
	
	public void addToWorld(World world) {
		world.addObject(obj);
	}
	
	public void removeFromWorld(World world) {
		world.removeObject(obj);
	}
	
	public Object3D get3D() {
		return obj;
	}
	
	public void destroy(World world) {
		obj.unbuild();
		obj.removeCollisionListener(CollisionDieListener.INSTANCE);
		obj = null;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasicObject3d> T applyConfig(Object3dConfig config) {
		obj.translate(config.x, 0, config.z);
		obj.rotateY(config.rotation);
		applyConfigInternal(config);
		this.config = config.clone();
		return (T) this;
	}
	
	protected void applyConfigInternal(Object3dConfig config) {
		
	}


	public void createConfigFromTranslation() {
		SimpleVector v = obj.getTranslation();
		config = new Object3dConfig(v.x, v.z);
	}

	public void resetTranslationFromConfig() {
		SimpleVector v = obj.getTranslation();
		v.x = config.x;
		v.z = config.z;
		obj.clearTranslation();
		obj.translate(v);
	}

}
