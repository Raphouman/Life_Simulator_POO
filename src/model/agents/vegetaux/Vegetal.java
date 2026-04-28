package model.agents.vegetaux;

import java.awt.Point;
import model.agents.Agent;

public abstract class Vegetal extends Agent {
	
	protected int qteNectar = 0;
	protected int qteMiel = 0;

	public Vegetal(Point point) {
		super(point);
	}
	
	public abstract void produire();
	@Override
	public void rencontrer(Agent a) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void maj() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void seNourrir() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Si il reste du nectar, renvoie 1 et diminue la quantité disponible
	 * Renvoie 0 sinon
	 * @return
	 */
	public int getPortionNectar() {
		int ret = 0;
		if(qteNectar>0) {
			ret = 1;
			qteNectar--;	//on diminue la quantité de nectar disponible
		}
		return ret;
	}
	public int getPortionMiel() {
		int ret = 0;
		if(qteMiel>0) {
			ret = 1;
			qteMiel--;	//on diminue la quantité de miel disponible
		}
		return ret;
	}

}
