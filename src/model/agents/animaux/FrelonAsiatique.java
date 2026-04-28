package model.agents.animaux;

import java.awt.Point;
import model.agents.Agent;
import model.agents.Etat;
import model.agents.Sexe;

public class FrelonAsiatique extends Frelon{

	public FrelonAsiatique(Sexe s, Point p) {
		/*
		 * TODO
		 */
		super(s,p);
		proies.add(AbeilleDomestique.class);
		proies.add(AbeilleSolitaire.class);
	}

	public void rencontrer(Agent a) {
		/*
		 * TODO
		 */
		super.rencontrer(a);
		if(a instanceof FrelonEuropeen && getNiveauSante()!=Etat.Mourant) {
			setNiveauSante(Etat.EnDetresse);
			//if (a.aFaim()) {setNiveauSante(Etat.Mourant);}	//les frelons asiatiques ne tuent jamais les frelons européens ?
		}
		
	}
	

}
