package br.guilherme.rmr.collision;

import br.guilherme.rmr.event.EventConst;
import br.guilherme.rmr.event.EventManager;

import com.threed.jpct.CollisionEvent;
import com.threed.jpct.CollisionListener;

public class CollisionSliderListener implements CollisionListener {
	public static final CollisionListener LEFT = new CollisionSliderListener(false);
	public static final CollisionListener RIGHT = new CollisionSliderListener(true);
	private static final long serialVersionUID = 1L;
	private boolean right;
	
	private CollisionSliderListener(boolean right) {
		this.right = right;
	}
	
	@Override
	public boolean requiresPolygonIDs() {
		return false;
	}
	@Override
	public void collision(CollisionEvent e) {
		if (right) EventManager.getInstance().registerEvent(EventConst.SLIDE_RIGHT);
		else EventManager.getInstance().registerEvent(EventConst.SLIDE_LEFT);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (right ? 1231 : 1237);
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
		CollisionSliderListener other = (CollisionSliderListener) obj;
		if (right != other.right)
			return false;
		return true;
	}
	
	
	
}
