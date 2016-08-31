package br.guilherme.rmr.event;

import java.util.ArrayList;
import java.util.Observable;

public class EventManager extends Observable {
	private static EventManager instance = new EventManager();
	private ArrayList<String> eventList;
	
	private EventManager() {
		eventList = new ArrayList<String>(10);
	}
	
	public static EventManager getInstance() {
		return instance;
	}

	public void registerEvent(String e) {
		eventList.add(e);
	}

	public void fireEvents() {
		for (String e : eventList) {
			setChanged();
			notifyObservers(e);
		}
		eventList.clear();
	}
	
}
