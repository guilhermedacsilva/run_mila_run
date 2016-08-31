package br.guilherme.rmr.cfg;

public class GameFPS {
	private int fps;
	private long oldTime;
	private long elapsedTime;
	private long frameTime;
	private int totalFrames;
	private long totalTime;
	
	public GameFPS(int fps) {
		this.fps = fps;
		frameTime = 1000/fps;
		oldTime = System.currentTimeMillis();
	}
	
	public void sleep() {
		elapsedTime = System.currentTimeMillis() - oldTime;
		if (elapsedTime < frameTime) {
			try {
				Thread.sleep(frameTime - elapsedTime);
			} catch (Exception e) {}
		}
		debug();
		oldTime = System.currentTimeMillis();
	}
	
	private void debug() {
		if (GlobalConfig.DEBUG && false) {
			elapsedTime = System.currentTimeMillis() - oldTime;
			totalTime += elapsedTime;
			totalFrames++;
			if (totalFrames == fps*5) {
				System.out.println("FPS: " + (totalFrames/(totalTime/1000.0)));
				totalFrames = 0;
				totalTime = 0;
			}
		}
	}
	
}
