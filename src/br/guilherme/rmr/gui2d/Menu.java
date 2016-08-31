package br.guilherme.rmr.gui2d;

import java.awt.event.KeyEvent;

import br.guilherme.rmr.cfg.GlobalConfig;
import br.guilherme.rmr.core.Joystick;
import br.guilherme.rmr.core.Score;
import br.guilherme.rmr.core.Upgrades;
import br.guilherme.rmr.event.EventConst;
import br.guilherme.rmr.event.EventManager;

import com.threed.jpct.FrameBuffer;

public class Menu extends SimpleGUI {
	private static final String UPGRADE_FORMAT = "[%s] %s. Cost: %d. Level: %d.";
	
	public Menu(Joystick joystick, FrameBuffer buffer) {
		super(joystick, buffer);
	}
	
	private String createUpgradeText(String key, String text, long cost, int level) {
		return String.format(UPGRADE_FORMAT, key, text, cost, level);
	}
	
	private void blitUpgrade(String key, String text, long cost, int level, int x, int y) {
		final String textFormatted = createUpgradeText(key, text, cost, level);
		Gui2D.drawText(buffer, textFormatted, x, y);
	}
	
	@Override
	public void draw() {
		buffer.blit(Gui2D.TEXTURE_WHITE, 0, 0, 0, 0, 800, 600, false);
		Gui2D.drawText(buffer, "Total points: " + Score.get().getTotalPoints(), 50, 50);
		Gui2D.drawText(buffer, "[1] Play", 50, 100);
		Gui2D.drawText(buffer, "[2] Difficulty: " + GlobalConfig.getDifficultyText(), 50, 150);
		Gui2D.drawText(buffer, "Upgrades", 50, 250);

		blitUpgrade("Q", "Turn speed",
				Upgrades.getUpgradeCost(Upgrades.get().getTurnSpeedLevel()), 
				Upgrades.get().getTurnSpeedLevel(),
				50, 300);
		blitUpgrade("W", "More points",
				Upgrades.getUpgradeCost(Upgrades.get().getMorePointsLevel()), 
				Upgrades.get().getMorePointsLevel(),
				50, 350);
		if (Score.get().getStageNumber() > 1) {
			blitUpgrade("E", "Jump",
					Upgrades.getUpgradeCost2(Upgrades.get().getJumpLevel()), 
					Upgrades.get().getJumpLevel(),
					50, 400);
		}
	}
	
	@Override
	public void processInputs() {
		if (joystick.isPressed(KeyEvent.VK_1)) {
			EventManager.getInstance().registerEvent(EventConst.PLAY);
		}
		if (joystick.isPressed(KeyEvent.VK_2)) {
			GlobalConfig.changeDifficulty();
		}
		if (joystick.isPressed(KeyEvent.VK_Q)
				&& tryBuy(1, Upgrades.get().getTurnSpeedLevel())) 
				{
			Upgrades.get().incrementTurnSpeed();
		}
		if (joystick.isPressed(KeyEvent.VK_W)
				&& tryBuy(1, Upgrades.get().getMorePointsLevel())) 
				{
			Upgrades.get().incrementMorePoints();
		}
		if (joystick.isPressed(KeyEvent.VK_E)
				&& tryBuy(2, Upgrades.get().getJumpLevel())) 
				{
			Upgrades.get().incrementJump();
		}
		debugKeys();
	}
	
	private boolean tryBuy(int type, int level) {
		long cost;
		if (type == 1) cost = Upgrades.getUpgradeCost(level);
		else cost = Upgrades.getUpgradeCost2(level);
		
		if (Score.get().getTotalPoints() >= cost) {
			Score.get().spendTotalPoints(cost);
			return true;
		}
		return false;
	}
	
	private void debugKeys() {
		if (GlobalConfig.DEBUG) {
			if (joystick.isPressed(KeyEvent.VK_Q)) {
				Upgrades.get().incrementTurnSpeed();
			}
			if (joystick.isPressed(KeyEvent.VK_W)) {
				Upgrades.get().incrementMorePoints();
			}
			if (joystick.isPressed(KeyEvent.VK_E)) {
				Upgrades.get().incrementJump();
			}
		}
	}

}
