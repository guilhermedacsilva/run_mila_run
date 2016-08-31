package br.guilherme.rmr.obj3d;

public class Object3dConfig implements Cloneable {
	private static final Object3dConfig OBJ_REUSE = new Object3dConfig(0,0);
	float x;
	float z;
	float rotation;
	Object[] others;

	public Object3dConfig(float x, float z) {
		this.x = x;
		this.z = z;
	}
	
	public Object3dConfig rotation(float rotation) {
		this.rotation = rotation;
		return this;
	}
	
	public Object3dConfig others(Object[] others) {
		this.others = others;
		return this;
	}
	
	public static Object3dConfig reuse(float x, float z) {
		OBJ_REUSE.x = x;
		OBJ_REUSE.z = z;
		OBJ_REUSE.rotation = 0;
		return OBJ_REUSE;
	}
	
	public Object3dConfig clone() {
		Object3dConfig obj = new Object3dConfig(x,z);
		obj.rotation = rotation;
		obj.others = others;
		return obj;
	}

}
