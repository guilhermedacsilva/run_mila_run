package br.guilherme.rmr.util;

import java.awt.Color;
import java.util.HashMap;

import com.threed.jpct.Loader;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

public class Object3dUtil {
	private static final String FOLDER_MEDIA = "media/";
	private static final HashMap<String, Object3D> OBJECT3D_MAP = new HashMap<String, Object3D>();

	/**
	 * @param name DO NOT use extension
	 */
	public static Object3D load3ds(String name) {
		if (!OBJECT3D_MAP.containsKey(name)) {
			try {
				doLoad3ds(name);
			} catch (Exception e) {
				System.out.println("ERROR: Could not load " + name);
				System.exit(1);
			}
		}
		return OBJECT3D_MAP.get(name).cloneObject();
	}

	private static void doLoad3ds(String name) {
		Object3D[] model = Loader.load3DS(FOLDER_MEDIA + name + ".3ds", 1);
		Object3D o3d = new Object3D(0);
		Object3D temp = null;
		for (int i = 0; i < model.length; i++) {
			temp = model[i];
			temp.setCenter(SimpleVector.ORIGIN);
			temp.rotateX((float) (-.5 * Math.PI));
			temp.rotateMesh();
			temp.clearRotation();
			o3d = Object3D.mergeObjects(o3d, temp);
			o3d.build();
		}
		OBJECT3D_MAP.put(name, o3d);
	}

	/**
	 * @param name use extension
	 */
	public static void applyTexture(Object3D obj, String textureName) {
		if (!TextureManager.getInstance().containsTexture(textureName)) {
			Texture texture = new Texture(FOLDER_MEDIA + textureName);
			TextureManager.getInstance().addTexture(textureName, texture);
		}
		obj.setTexture(textureName);
	}
	public static void applyTexture(Object3D obj, String textureName, Color color) {
		if (!TextureManager.getInstance().containsTexture(textureName)) {
			Texture texture = new Texture(2,2,color);
			TextureManager.getInstance().addTexture(textureName, texture);
		}
		obj.setTexture(textureName);
	}
	public static Texture loadTexture(String name) {
		return new Texture(FOLDER_MEDIA + name);
	}

}
