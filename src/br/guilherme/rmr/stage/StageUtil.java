package br.guilherme.rmr.stage;

import java.lang.reflect.Array;

import br.guilherme.rmr.obj3d.BasicObject3d;
import br.guilherme.rmr.obj3d.Object3dConfig;
import br.guilherme.rmr.obj3d.Tree;

public class StageUtil {
	
	public static void createTrees(Stage stage) {
		for (int sideX = 0; sideX < 2; sideX++) {
			for (int column = 0; column < 3; column++) {
				for (int translateZ = 0; translateZ < 37; translateZ++) {
					stage.addToStageAndWorld(
							new Tree()
								.applyConfig(Object3dConfig.reuse(column*6-25+sideX*38, translateZ*10+column*5))
							);
				}
			}
		}
	}
	
	public static BasicObject3d[] createObject3dFromConfig(
			Stage stage, 
			boolean fixed, 
			Class<? extends BasicObject3d> clazz, 
			Object3dConfig[] configArray) {
		
		BasicObject3d[] objArray = (BasicObject3d[]) Array.newInstance(clazz, configArray.length);
		BasicObject3d obj;
		try {
			for (int i = 0; i < configArray.length; i++) {
				obj = clazz.newInstance().applyConfig(configArray[i]);
				stage.addToStageAndWorld(obj, fixed);
				objArray[i] = obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return objArray;
	}
	
}
