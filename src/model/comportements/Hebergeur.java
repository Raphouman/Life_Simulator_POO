package model.comportements;

import java.awt.Point;
import model.agents.Animal;

/**
 * un hébergeur peut accueillir un animal
 * @author bruno
 *
 */
public interface Hebergeur {
	/**
	 * Renvoie vrai si a peut être acceuilli
	 * Il s'agit d'un test de faisabilité
	 * (l'animal doit répondre aux conditions)
	 * @param a Animal candidat à l'accueil
	 * @return
	 */
	public boolean peutAccueillir(Animal a);	//c'est un test de faisabilité

	
	/**
	 * Animal a demande à être hébergé par l'herbergeur
	 * Si l'hébergeur accepte, renvoie true
	 * @param a
	 * @return
	 */
	public boolean accueillir(Animal a);	//c'est l' action d'accueillir
	
	/**
	 * Supprime un animal de l'hébergeur.
	 * Utile pour nettoyer les références lorsqu'un animal meurt.
	 * @param a Animal à supprimer
	 */
	public void supprimer(Animal a);
	
	/**
	 * Renvoie les coordonnées de l'hébergeur.
	 * Utilisé en mode nuit pour que les animaux retrouvent leur hébergeur.
	 * @return Clone des coordonnées de l'hébergeur
	 */
	public Point getCoord();
}
