package model.agents.animaux;

import java.awt.Point;
import model.agents.Sexe;
import model.decor.Ruche;

public class AbeilleDomestique extends Abeille {

	public AbeilleDomestique(Sexe s, Point p, Ruche r) {
		/*
		 * TODO
		 */
		super(s, p);
		//implémenter par défaut l'hebergeur = la ruche passée en paramètre
		sInstaller(r);	//l'abeille s'installe dans la ruche
	}
	
	/**
	 * Méthode générique pour retourner à la ruche.
	 * Utilisée à la fois pour la nuit et pour ramener le nectar et le miel.
	 * Se déplace graduellement vers la ruche jusqu'à atteindre ses coordonnées.
	 */
	protected void retournerALaRuche() {
		if (hebergeur != null) {
			Point ruche_coord = hebergeur.getCoord();
			
			// Si l'abeille n'est pas déjà à la ruche
			if (!coord.equals(ruche_coord)) {
				// Calcule le déplacement d'une unité vers la ruche (en X et Y)
				int dx = Integer.compare(ruche_coord.x, coord.x);
				int dy = Integer.compare(ruche_coord.y, coord.y);
				setCoord((int)(coord.getX()+dx), (int)(coord.getY()+dy));
			}
			// Si l'abeille vient d'arriver à la ruche
			else {
				deposerNectarALaRuche();
				deposerMielALaRuche();
			}
		}
	}
	
	/**
	 * Dépose le miel/nectar à la ruche.
	 * Appelée quand l'abeille arrive à la ruche (même position).
	 */
	private void deposerNectarALaRuche() {
		if (hebergeur != null && getQteNectar() > 0) {
			int nectarADeposer = viderNectar();
			((Ruche) hebergeur).deposerNectar(nectarADeposer);
		}
	}
	private void deposerMielALaRuche() {
		if (hebergeur != null && getQteMiel() > 0) {
			int mielADeposer = viderMiel();
			((Ruche) hebergeur).deposerMiel(mielADeposer);
		}
	}
	
	/**
	 * Override de seDeplacerLaNuit pour utiliser la méthode générique.
	 * La nuit, l'abeille retourne à la ruche pour se reposer.
	 */
	@Override
	public void seDeplacerLaNuit() {
		retournerALaRuche();
	}
}
