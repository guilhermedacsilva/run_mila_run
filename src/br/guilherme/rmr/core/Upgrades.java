package br.guilherme.rmr.core;

import br.guilherme.rmr.cfg.GlobalConfig;


public class Upgrades {
	private static Upgrades instance = new Upgrades(); 
	private int turnSpeed = GlobalConfig.UPGRADE_TURN_SPEED;
	private int morePoints = GlobalConfig.UPGRADE_MORE_POINTS;
	private int jump = GlobalConfig.UPGRADE_JUMP;
	
	private Upgrades() {}
	public static Upgrades get() {
		return instance;
	}

	public static long getUpgradeCost(int level) {
		return 30+level*level*50;
	}

	public static long getUpgradeCost2(int level) {
		level++;
		return level*level*250;
	}
	
	public void incrementTurnSpeed() {
		turnSpeed++;
	}
	public void incrementMorePoints() {
		morePoints++;
	}
	public void incrementJump() {
		jump++;
	}
	
	public int getTurnSpeedLevel() {
		return turnSpeed;
	}
	public int getMorePointsLevel() {
		return morePoints;
	}
	public int getJumpLevel() {
		return jump;
	}
}
