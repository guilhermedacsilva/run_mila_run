package br.guilherme.rmr.stage;

import br.guilherme.rmr.cfg.Measure;
import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.obj3d.Fence;
import br.guilherme.rmr.obj3d.Object3dConfig;
import br.guilherme.rmr.obj3d.Slider;
import br.guilherme.rmr.obj3d.Spike;
import br.guilherme.rmr.obj3d.Wall;

import com.threed.jpct.World;

public class Stage3 extends Stage {
	private static final int Z_END = 212;
	private Spike[] spikeArray;

	public Stage3(World world, Joystick joystick) {
		super(world, joystick);
	}
	
	@Override
	public void createStageInternal() {

		StageUtil.createObject3dFromConfig(this, false, Slider.class, new Object3dConfig[] {
			new Object3dConfig(-9, 30),
		});
		
		StageUtil.createObject3dFromConfig(this, false, Wall.class, new Object3dConfig[] {
			new Object3dConfig(-8, 40).rotation(-Measure.DEGREE_45),
			new Object3dConfig(8, 40).rotation(Measure.DEGREE_45),
		});
		
		StageUtil.createObject3dFromConfig(this, false, Fence.class, new Object3dConfig[] {
			new Object3dConfig(-8, 15f),
			new Object3dConfig(8, 15f),
			new Object3dConfig(-4, 25),
		});

		spikeArray = (Spike[]) StageUtil.createObject3dFromConfig(this, false, Spike.class, new Object3dConfig[] {
			new Object3dConfig(0, 17.5f),
		});
		
	}
	
	@Override
	public void updateObjects() {
		for (Spike s : spikeArray) {
			s.updateObject();
		}
	}
	
	@Override
	public void reset() {
		for (Spike s : spikeArray) {
			s.reset();
		}
	}
	
	@Override
	protected float getFinalZofStage() {
		return Z_END;
	}
	
}
