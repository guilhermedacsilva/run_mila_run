package br.guilherme.rmr.core;

import java.util.ArrayList;


public class ObjectManager {
	private ArrayList<ObjectGroup> groupList;
	
	public ObjectManager(){
		groupList = new ArrayList<ObjectGroup>(10);
	}

	public void addObjectGroup(ObjectGroup objectGroup) {
		groupList.add(objectGroup);
	}
	
	public void removeObjectGroup(ObjectGroup objectGroup) {
		groupList.remove(objectGroup);
	}
	
	public void processInputs() {
		for (ObjectGroup group : groupList) {
			group.processInputs();
		}
	}

	public void updateObjects() {
		for (ObjectGroup group : groupList) {
			group.updateObjects();
		}
	}

}
