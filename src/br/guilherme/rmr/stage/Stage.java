package br.guilherme.rmr.stage;

import java.util.ArrayList;

import br.guilherme.rmr.cfg.GlobalConfig;
import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.core.ObjectGroup;
import br.guilherme.rmr.obj3d.BasicObject3d;
import br.guilherme.rmr.obj3d.Bowl;
import br.guilherme.rmr.obj3d.Ground;

import com.threed.jpct.World;

public abstract class Stage extends ObjectGroup {
	private static final String STAGE_PACKAGE = "br.guilherme.rmr.stage.";
	protected ArrayList<BasicObject3d> fixedObjectList;
	protected ArrayList<BasicObject3d> relativeObjectList;
	private boolean showing;
	private byte currentDifficulty;
	
	public Stage(World world, Joystick joystick) {
		super(world, joystick);
		fixedObjectList = new ArrayList<BasicObject3d>(500);
		relativeObjectList = new ArrayList<BasicObject3d>(500);
		showing = true;
		currentDifficulty = GlobalConfig.DIFFICULTY_NORMAL;
	}
	
	protected void addToStageAndWorld(BasicObject3d obj) {
		addToStageAndWorld(obj, true);
	}
	
	protected void addToStageAndWorld(BasicObject3d obj, boolean fixed) {
		obj.addToWorld(world);
		if (fixed) fixedObjectList.add(obj);
		else relativeObjectList.add(obj);
	}

	public void createStage() {
		addToStageAndWorld(new Ground());
		
		StageUtil.createTrees(this);
		
		Bowl bowl = new Bowl();
		bowl.get3D().translate(0, 0, getFinalZofStage());
		bowl.createConfigFromTranslation();
		addToStageAndWorld(bowl, false);

		createStageInternal();
	}

	protected abstract void createStageInternal();

	public void reset() {
		
	}
	
	public void show(boolean show) {
		if (showing && !show) {
			for (BasicObject3d obj3d : fixedObjectList) {
				obj3d.removeFromWorld(world);
			}
			for (BasicObject3d obj3d : relativeObjectList) {
				obj3d.removeFromWorld(world);
			}
		} else if (!showing && show) {
			for (BasicObject3d obj3d : fixedObjectList) {
				obj3d.addToWorld(world);
			}
			for (BasicObject3d obj3d : relativeObjectList) {
				obj3d.addToWorld(world);
			}
		}
		showing = show;
	}
	
	public void destroyStage() {
		show(false);
		
		for (BasicObject3d obj3d : fixedObjectList) {
			obj3d.destroy(world);
		}
		fixedObjectList.clear();
		fixedObjectList = null;
		
		for (BasicObject3d obj3d : relativeObjectList) {
			obj3d.destroy(world);
		}
		relativeObjectList.clear();
		relativeObjectList = null;
		
		destroyGroup();
	}
	
	protected abstract float getFinalZofStage();
	
	public boolean isFinished(float catZ) {
		return catZ > getFinalZofStage() * GlobalConfig.getDifficultyZ();
	}
	
	@SuppressWarnings("unchecked")
	public static Stage loadStageClass(String className, World world, Joystick joystick) {
		try {
			Class<Stage> stageClass = (Class<Stage>) Class.forName(STAGE_PACKAGE + className);
			Stage stage = stageClass.getConstructor(World.class, Joystick.class)
					.newInstance(world, joystick);
			return stage;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}

	
	@Override
	public void processInputs() {
	}
	
	@Override
	public void updateObjects() {
	}

	public void adjustObjectsFromDifficulty() {
		if (currentDifficulty != GlobalConfig.getDifficulty()) {
			for (BasicObject3d obj3d : relativeObjectList) {
				obj3d.resetTranslationFromConfig();
			}
			
			currentDifficulty = GlobalConfig.getDifficulty();
			if (currentDifficulty != GlobalConfig.DIFFICULTY_NORMAL) {
				recalculateZ();
			}
		}
	}
	
	private void recalculateZ() {
		final float translationZ = GlobalConfig.getDifficultyZ() - 1;
		for (BasicObject3d obj3d : relativeObjectList) {
			obj3d.get3D().translate(0, 0, obj3d.get3D().getTranslation().z * translationZ);
		}
	}
	
}
