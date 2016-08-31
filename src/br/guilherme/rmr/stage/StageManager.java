package br.guilherme.rmr.stage;

import java.util.Observable;
import java.util.Observer;

import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.core.ObjectGroup;
import br.guilherme.rmr.core.Score;
import br.guilherme.rmr.event.EventConst;
import br.guilherme.rmr.event.EventManager;

import com.threed.jpct.World;

public class StageManager extends ObjectGroup implements Observer {
	private StageCat catStage;
	private Stage currentStage;
	
	public StageManager(World world, Joystick joystick) {
		super(world, joystick);
		catStage = new StageCat(world, joystick);
		EventManager.getInstance().addObserver(this);
		eventNextStage();
	}
	
	public void processInputs() {
		catStage.processInputs();
	}

	public void updateObjects() {
		catStage.updateObjects();
		currentStage.updateObjects();
		
		if (currentStage.isFinished(catStage.getCatZ())) {
			EventManager.getInstance().registerEvent(EventConst.NEXT_STAGE);
		}
	}
	
	private void eventMilaDead() {
		catStage.hideObjects(true);
		currentStage.show(false);
	}
	
	private void eventPlay() {
		currentStage.adjustObjectsFromDifficulty();
		currentStage.show(true);
		currentStage.reset();
		catStage.hideObjects(false);
		catStage.reset();
	}
	
	private void eventNextStage() {
		catStage.hideObjects(true);
		if (currentStage != null) currentStage.destroyStage();
		createNextStage();
	}

	private void createNextStage() {
		catStage.reset();
		Score.get().incrementStageNumber();
		currentStage = Stage.loadStageClass("Stage" + Score.get().getStageNumber(), world, joystick);
		currentStage.createStage();
		currentStage.adjustObjectsFromDifficulty();
		world.buildAllObjects();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg.toString().equals(EventConst.MILA_DEAD)) {
			eventMilaDead();
		} else if (arg.toString().equals(EventConst.PLAY)) {
			eventPlay();
		} else if (arg.toString().equals(EventConst.NEXT_STAGE)) {
			eventNextStage();
		}
	}

}
