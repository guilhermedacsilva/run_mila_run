package br.guilherme.rmr.stage;

import br.guilherme.rmr.cfg.Measure;
import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.obj3d.Fence;
import br.guilherme.rmr.obj3d.Object3dConfig;
import br.guilherme.rmr.obj3d.Slider;
import br.guilherme.rmr.obj3d.Spike;
import br.guilherme.rmr.obj3d.Wall;

import com.threed.jpct.World;

public class Stage2 extends Stage {
	private static final int Z_END = 212;
	private Spike[] spikeArray;

	public Stage2(World world, Joystick joystick) {
		super(world, joystick);
	}
	
	@Override
	public void createStageInternal() {

		StageUtil.createObject3dFromConfig(this, false, Slider.class, new Object3dConfig[] {
			new Object3dConfig(-9, 30),
			new Object3dConfig(-5, 185),
			new Object3dConfig(5, 185).others(new Object[] {Slider.SLIDE_LEFT}),
		});
		
		StageUtil.createObject3dFromConfig(this, false, Wall.class, new Object3dConfig[] {
			new Object3dConfig(-8, 40).rotation(-Measure.DEGREE_45),
			new Object3dConfig(8, 40).rotation(Measure.DEGREE_45),
			new Object3dConfig(8, 160),
			new Object3dConfig(0.6f, 160),
		});
		
		StageUtil.createObject3dFromConfig(this, false, Fence.class, new Object3dConfig[] {
			new Object3dConfig(-8, 15f),
			new Object3dConfig(8, 15f),
			new Object3dConfig(-4, 25),
			new Object3dConfig(4, 25),
			new Object3dConfig(0, 50f),
			new Object3dConfig(-5, 55f).rotation(Measure.DEGREE_90),
			new Object3dConfig(5, 55f).rotation(Measure.DEGREE_90),
			new Object3dConfig(0, 60f),
			new Object3dConfig(-5, 65f),
			new Object3dConfig(-10, 70f).rotation(Measure.DEGREE_90),
			new Object3dConfig(0, 70f).rotation(Measure.DEGREE_90),
			new Object3dConfig(-5, 75f),
			new Object3dConfig(5, 80f),
			new Object3dConfig(10, 85f).rotation(Measure.DEGREE_90),
			new Object3dConfig(0, 85f).rotation(Measure.DEGREE_90),
			new Object3dConfig(5, 90f),
			new Object3dConfig(10, 90f),
			new Object3dConfig(-6, 100f).rotation(Measure.DEGREE_90),
			new Object3dConfig(0, 100f).rotation(Measure.DEGREE_90),
			new Object3dConfig(6, 100f).rotation(Measure.DEGREE_90),
			new Object3dConfig(-6, 108f).rotation(Measure.DEGREE_90),
			new Object3dConfig(0, 108f).rotation(Measure.DEGREE_90),
			new Object3dConfig(6, 108f).rotation(Measure.DEGREE_90),
			new Object3dConfig(-5.5f, 116f).rotation(Measure.DEGREE_90+Measure.DEGREE_10),
			new Object3dConfig(0.5f, 116f).rotation(Measure.DEGREE_90+Measure.DEGREE_10),
			new Object3dConfig(6.5f, 116f).rotation(Measure.DEGREE_90+Measure.DEGREE_10),
			new Object3dConfig(-4.0f, 124f).rotation(Measure.DEGREE_90+2*Measure.DEGREE_10),
			new Object3dConfig(2.0f, 124f).rotation(Measure.DEGREE_90+2*Measure.DEGREE_10),
			new Object3dConfig(8.0f, 124f).rotation(Measure.DEGREE_90+2*Measure.DEGREE_10),
			new Object3dConfig(-2f, 132f).rotation(Measure.DEGREE_90+Measure.DEGREE_10),
			new Object3dConfig(4f, 132f).rotation(Measure.DEGREE_90+Measure.DEGREE_10),
			new Object3dConfig(10f, 132f).rotation(Measure.DEGREE_90+Measure.DEGREE_10),
			new Object3dConfig(-2f, 140f).rotation(Measure.DEGREE_90-2*Measure.DEGREE_10),
			new Object3dConfig(4f, 140f).rotation(Measure.DEGREE_90-2*Measure.DEGREE_10),
			new Object3dConfig(10f, 140f).rotation(Measure.DEGREE_90-2*Measure.DEGREE_10),
			new Object3dConfig(-6f, 148f).rotation(Measure.DEGREE_45),
			new Object3dConfig(0f, 148f).rotation(Measure.DEGREE_45),
			new Object3dConfig(6f, 148f).rotation(Measure.DEGREE_45),
			new Object3dConfig(-12f, 154f).rotation(Measure.DEGREE_45-Measure.DEGREE_10),
			new Object3dConfig(-6f, 154f).rotation(Measure.DEGREE_45-Measure.DEGREE_10),
			new Object3dConfig(0f, 154f).rotation(Measure.DEGREE_45-Measure.DEGREE_10),
			new Object3dConfig(-7f, 159f).rotation(Measure.DEGREE_45-Measure.DEGREE_10),
			new Object3dConfig(-11f, 160f).rotation(Measure.DEGREE_45+2*Measure.DEGREE_10),
			new Object3dConfig(-4f, 162f).rotation(Measure.DEGREE_45-Measure.DEGREE_10),
			new Object3dConfig(-7f, 165f).rotation(Measure.DEGREE_45+Measure.DEGREE_10),
			
			new Object3dConfig(-11f, 173f),
			new Object3dConfig(11f, 173f),
			new Object3dConfig(-5f, 175f).rotation(-Measure.DEGREE_30),
			new Object3dConfig(5f, 175f).rotation(Measure.DEGREE_30),
			new Object3dConfig(0f, 180f).rotation(-Measure.DEGREE_30*2),
			new Object3dConfig(0f, 180f).rotation(Measure.DEGREE_30*2),
			new Object3dConfig(2.5f, 189f).rotation(-Measure.DEGREE_90),
			new Object3dConfig(-2.5f, 189f).rotation(Measure.DEGREE_90),
			new Object3dConfig(0f, 198f).rotation(-Measure.DEGREE_30*2),
			new Object3dConfig(0f, 198f).rotation(Measure.DEGREE_30*2),
			new Object3dConfig(-5f, 203f).rotation(Measure.DEGREE_30),
			new Object3dConfig(5f, 203f).rotation(-Measure.DEGREE_30),
			new Object3dConfig(-11f, 205f),
			new Object3dConfig(11f, 205f),
		});

		spikeArray = (Spike[]) StageUtil.createObject3dFromConfig(this, false, Spike.class, new Object3dConfig[] {
			new Object3dConfig(0, 17.5f),
			new Object3dConfig(0, 25f),
			new Object3dConfig(-6f, 85f),
			new Object3dConfig(-9f, 90f),
			new Object3dConfig(-11f, 95f),
			new Object3dConfig(-10f, 112f),
			new Object3dConfig(-8f, 130f),
			new Object3dConfig(-8f, 140f),
			new Object3dConfig(-11, 180f),
			new Object3dConfig(11, 180f),
			new Object3dConfig(-11, 195f),
			new Object3dConfig(11, 195f),
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
