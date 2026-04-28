package model.agents.animaux;

import java.awt.Point;
import model.agents.Agent;
import model.agents.Animal;
import model.agents.Sexe;
/**
 * 
 * @author bruno
 *
 */
public class Varroa extends Animal {
	
	public Varroa(Sexe s, Point p) {
		/*
		 * Crée un Varroa avec le sexe passé en paramètre et la position p
		 */
		super(s, p);
	}
		
	@Override
	public void rencontrer(Agent a) {
		//code à trouver en section 6
		if(a instanceof Abeille) {
			Abeille candidateAuParasitage = (Abeille) a;
			this.sInstaller(candidateAuParasitage);
		}
	}

	@Override
	protected void maj() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seNourrir() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Si le Varroa a un hébergeur (une abeille), il se déplace avec elle.
	 * Sinon, il se déplace aléatoirement.
	 */
	@Override
	public void seDeplacer() {
		// Si le Varroa est parasité une abeille
		if (hebergeur != null) {
			// Se déplacer aux coordonnées de l'abeille
			Point abeilleCoord = hebergeur.getCoord();
			this.setCoord(abeilleCoord.x, abeilleCoord.y);
		} 
		// Sinon, déplacement aléatoire (cherche une abeille)
		else {
			super.seDeplacer();  // Appel du déplacement aléatoire d'Animal
		}
	}
}
