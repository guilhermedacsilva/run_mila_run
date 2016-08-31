package br.guilherme.rmr.stage;

import br.guilherme.rmr.cfg.Measure;
import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.obj3d.Object3dConfig;
import br.guilherme.rmr.obj3d.Wall;

import com.threed.jpct.World;

public class Stage1 extends Stage {
	private static final int Z_END = 170;

	public Stage1(World world, Joystick joystick) {
		super(world, joystick);
	}
	
	@Override
	public void createStageInternal() {

		StageUtil.createObject3dFromConfig(this, false, Wall.class, new Object3dConfig[] {
				new Object3dConfig(2, 13),
				new Object3dConfig(-8, 23),
				new Object3dConfig(-6, 33),
				new Object3dConfig(6, 33),
				new Object3dConfig(0, 43),
				new Object3dConfig(-8, 53),
				new Object3dConfig(-4, 63),
				new Object3dConfig(0, 73),
				new Object3dConfig(5, 83),
				new Object3dConfig(8, 93),
				new Object3dConfig(0, 93),
				new Object3dConfig(0, 110).rotation(Measure.DEGREE_90),
				new Object3dConfig(-6, 110).rotation(Measure.DEGREE_90),
				new Object3dConfig(6, 110).rotation(Measure.DEGREE_90),
				new Object3dConfig(-9, 120).rotation(Measure.DEGREE_90),
				new Object3dConfig(-3, 120).rotation(Measure.DEGREE_90),
				new Object3dConfig(3, 120).rotation(Measure.DEGREE_90),
				new Object3dConfig(9, 120).rotation(Measure.DEGREE_90),
				new Object3dConfig(-8, 130),
				new Object3dConfig(8, 130),
				new Object3dConfig(0, 140).rotation(Measure.DEGREE_90),
				new Object3dConfig(-7, 140).rotation(Measure.DEGREE_45),
				new Object3dConfig(7, 140).rotation(-Measure.DEGREE_45),
				new Object3dConfig(-7, 150).rotation(-Measure.DEGREE_45),
				new Object3dConfig(7, 150).rotation(Measure.DEGREE_45),
				new Object3dConfig(-4.5f, 160),
				new Object3dConfig(4.5f, 160),
		});
	}
	
	@Override
	protected float getFinalZofStage() {
		return Z_END;
	}
	
}
