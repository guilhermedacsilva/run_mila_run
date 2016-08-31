package br.guilherme.rmr.collision;

import br.guilherme.rmr.event.EventConst;
import br.guilherme.rmr.event.EventManager;

import com.threed.jpct.CollisionEvent;
import com.threed.jpct.CollisionListener;

public class CollisionDieListener implements CollisionListener {
	public static final CollisionListener INSTANCE = new CollisionDieListener();
	private static final long serialVersionUID = 1L;
	
	private CollisionDieListener() {}
	
	@Override
	public boolean requiresPolygonIDs() {
		return false;
	}
	@Override
	public void collision(CollisionEvent e) {
		EventManager.getInstance().registerEvent(EventConst.MILA_DEAD);
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj == INSTANCE;
	}
	
}
