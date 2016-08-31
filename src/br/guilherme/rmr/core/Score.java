package br.guilherme.rmr.core;

import br.guilherme.rmr.cfg.GlobalConfig;

public class Score {
	private static Score instance = new Score();
	private long points;
	private long totalPoints;
	private byte stageNumber = GlobalConfig.STAGE_INITIAL;
	
	private Score() {}
	public static Score get() {
		return instance;
	}
	
	public void setPointsZ(long pointsZ) {
		if (pointsZ < 0) pointsZ = 0;
		this.points = (int)
				(pointsZ * (1 + stageNumber/10.0f) *
						(1 + Upgrades.get().getMorePointsLevel()*0.05) / GlobalConfig.getDifficultyZ()
				);
	}
	public long getPoints() {
		return points;
	}
	
	public void addToTotalPoints() {
		totalPoints += points;
		points = 0;
	}
	public void spendTotalPoints(long points) {
		totalPoints -= points;
	}
	public long getTotalPoints() {
		return totalPoints;
	}
	
	public int getStageNumber() {
		return stageNumber;
	}
	public void incrementStageNumber() {
		stageNumber++;
	}

}
