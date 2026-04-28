package model.world;

import java.util.Comparator;
import model.agents.Agent;

/**
 * Comparateur pour trier les agents par coordonnées.
 * Trie d'abord sur la coordonnée x, puis sur la coordonnée y.
 * En cas d'égalité de coordonnées, trie par id.
 * 
 * @author user
 */
public class CoordComparator implements Comparator<Agent> {

	@Override
	public int compare(Agent a1, Agent a2) {
		// Comparer d'abord sur x
		int compX = Integer.compare(a1.getCoord().x, a2.getCoord().x);
		if (compX != 0) {
			return compX;       //si les deux x sont différents, on retourne le résultat du comparateur (<0, >0)
		}
        //puis seulement si x égaux, comparer sur y
		
		// Si x égal, comparer sur y
		int compY = Integer.compare(a1.getCoord().y, a2.getCoord().y);
		if (compY != 0) {
			return compY;    //si les deux y sont différents, on retourne le résultat du comparateur (<0, >0)
		}
		
		// Si coordonnées égales, comparer par id (pour éviter l'égalité complète). Donc la 2eme viendra après la 1ere
		return Integer.compare(a1.getId(), a2.getId());
	}

}
