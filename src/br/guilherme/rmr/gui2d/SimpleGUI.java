package br.guilherme.rmr.gui2d;

import br.guilherme.rmr.core.Joystick;

import com.threed.jpct.FrameBuffer;

public abstract class SimpleGUI {
	protected Joystick joystick;
	protected FrameBuffer buffer;
	
	public SimpleGUI(Joystick joystick, FrameBuffer buffer) {
		this.joystick = joystick;
		this.buffer = buffer;
	}

	public abstract void draw();

	public void processInputs() {
	}
	
}
