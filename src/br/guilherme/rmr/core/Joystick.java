package br.guilherme.rmr.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.threed.jpct.util.KeyMapper;
import com.threed.jpct.util.KeyState;

public class Joystick {
	private static final int KEY_RELEASED = 0;
	private static final int KEY_PRESSED = 1;
	private static final int KEY_HOLD = 2;
	private KeyMapper keyMapper;
	private HashMap<Integer, Integer> activeKeyMap;
	private KeyState tempKey;
	private Iterator<Entry<Integer, Integer>> iterator;
	private Entry<Integer, Integer> entry;
	
	public Joystick() {
		keyMapper = new KeyMapper();
		activeKeyMap = new HashMap<Integer, Integer>(10);
	}
	
	public void update() {
		updateOldKeys();
		putNewKeys();
	}
	
	private void updateOldKeys() {
		for (iterator = activeKeyMap.entrySet().iterator();
				iterator.hasNext();) {
			
			entry = iterator.next();
			if (entry.getValue() == KEY_RELEASED) {
				iterator.remove();
			} else if (entry.getValue() == KEY_PRESSED) {
				entry.setValue(KEY_HOLD);
			}
		}
	}
	
	private void putNewKeys() {
		while ((tempKey = keyMapper.poll()) != KeyState.NONE) {
			if (tempKey.getState() == KeyState.PRESSED) {
				activeKeyMap.put(tempKey.getKeyCode(), KEY_PRESSED);
			} else {
				activeKeyMap.put(tempKey.getKeyCode(), KEY_RELEASED);
			}
		}
	}
	
	public boolean isPressed(int keyCode) {
		Integer state = activeKeyMap.get(keyCode);
		return state != null && state == KEY_PRESSED;
	}
	
	public boolean isHold(int keyCode) {
		Integer state = activeKeyMap.get(keyCode);
		return state != null && state != KEY_RELEASED;
	}
	
	public boolean isReleased(int keyCode) {
		Integer state = activeKeyMap.get(keyCode);
		return state != null && state == KEY_RELEASED;
	}
	

}
