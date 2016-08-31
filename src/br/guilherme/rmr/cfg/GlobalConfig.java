package br.guilherme.rmr.cfg;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.IRenderer;
import com.threed.jpct.World;

public class GlobalConfig {
	public static boolean DEBUG = false;
	public static final byte STAGE_INITIAL = 2;
	public static final byte UPGRADE_TURN_SPEED = 5;
	public static final byte UPGRADE_MORE_POINTS = 5;
	public static final byte UPGRADE_JUMP = 1;
	
	public static boolean EXIT = false;
	public static final byte DIFFICULTY_VERY_EASY = 0;
	public static final byte DIFFICULTY_EASY = 1;
	public static final byte DIFFICULTY_NORMAL = 2;
	private static final String DIFFICULTY_TEXT_VERY_EASY = "Very easy (candy)";
	private static final String DIFFICULTY_TEXT_EASY = "Easy (milk)";
	private static final String DIFFICULTY_TEXT_NORMAL = "Normal (mouse)";
	private static final float DIFFICULTY_Z_VERY_EASY = 1.6f;
	private static final float DIFFICULTY_Z_EASY = 1.3f;
	private static final float DIFFICULTY_Z_NORMAL = 1;

	private static byte difficulty = DIFFICULTY_NORMAL;
	private static float difficultyZ = DIFFICULTY_Z_NORMAL;
	
	public static void changeDifficulty() {
		difficulty--;
		if (difficulty < DIFFICULTY_VERY_EASY) {
			difficulty = DIFFICULTY_NORMAL;
			difficultyZ = DIFFICULTY_Z_NORMAL;
		} else if (difficulty == DIFFICULTY_VERY_EASY) {
			difficultyZ = DIFFICULTY_Z_VERY_EASY;
		} else {
			difficultyZ = DIFFICULTY_Z_EASY;
		}
	}
	public static byte getDifficulty() {
		return difficulty;
	}
	public static String getDifficultyText() {
		if (difficulty == DIFFICULTY_VERY_EASY) return DIFFICULTY_TEXT_VERY_EASY;
		else if (difficulty == DIFFICULTY_EASY) return DIFFICULTY_TEXT_EASY;
		return DIFFICULTY_TEXT_NORMAL;
	}
	public static float getDifficultyZ() {
		return difficultyZ;
	}

	public static FrameBuffer createFrameBuffer() {
		FrameBuffer buffer = new FrameBuffer(800, 600, FrameBuffer.SAMPLINGMODE_NORMAL);
		buffer.disableRenderer(IRenderer.RENDERER_SOFTWARE);
		buffer.enableRenderer(IRenderer.RENDERER_OPENGL);
		return buffer;
	}
	
	public static World createWorld() {
		World world = new World();
		return world;
	}
	
	public static void shutdownEngine(FrameBuffer buffer) {
		buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
		buffer.dispose();
	}
	
}
