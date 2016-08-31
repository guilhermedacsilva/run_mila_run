package br.guilherme.rmr.core;

import com.threed.jpct.World;

public abstract class ObjectGroup {
	private static long ID_GENERATOR = 0;
	private final long id;
	protected World world;
	protected Joystick joystick;
	
	public ObjectGroup(World world, Joystick joystick) {
		this.world = world;
		this.joystick = joystick;
		id = ID_GENERATOR++;
	}
	
	public abstract void processInputs();
	public abstract void updateObjects();
	
	public void destroyGroup() {
		world = null;
		joystick = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectGroup other = (ObjectGroup) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}
