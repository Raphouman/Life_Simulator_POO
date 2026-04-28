package model.agents.animaux;

import java.awt.Point;
import model.agents.Sexe;
public class FrelonEuropeen extends Frelon {
	
	public FrelonEuropeen(Sexe s, Point p) {
		/*
		 * TODO
		 */
		super(s,p);
		/*
		 * TODO: ajouter l'objet Class qui représente la classe Abeille à la liste proie
		 */
		proies.add(FrelonAsiatique.class);	//oui car le frelon européen peut attaquer le frelon asiatique
		proies.add(AbeilleDomestique.class);			
		proies.add(AbeilleSolitaire.class);
		proies.add(Abeille.class);			//ajout dynamique de classe Abeille abstraite
	}
	

	/* inutile avec la liste de proies à partir des collections
	@Override
	public void rencontrer(Agent a) {
		//penser à réutiliser l'existant
		super.rencontrer(a);
		
		if(a instanceof FrelonAsiatique) {
			Animal b = (Animal)a;
			gestionProie(b);
		}

	}
	*/

}
