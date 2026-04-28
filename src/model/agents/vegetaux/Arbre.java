package model.agents.vegetaux;

import java.awt.Point;
import model.agents.Animal;
import model.agents.animaux.AbeilleSolitaire;
import model.agents.animaux.Frelon;
import model.comportements.Hebergeur;

public class Arbre extends Vegetal implements Hebergeur{

	public Arbre(Point point, double taille) {
		super(point);
		this.taille=taille;
	}

	private double taille = 1.0;	//taille de l'arbre
	private int nbHeberges = 0;		//nombre d'animaux hébergés actuellement
	
	@Override
	public boolean peutAccueillir(Animal a) {
		return (a instanceof AbeilleSolitaire || a instanceof Frelon)&&nbHeberges<getMaxHeberges();
	}

	private int getMaxHeberges() {
		return (int)(Math.pow(taille,2));	//la surface de l'arbre détermine le nombre maximal d'animaux hébergés // max = taille^2
	}

	@Override
	public boolean accueillir(Animal a) {	//retourne vrai si l'accueil a réussi
		boolean ret = false;
		if(peutAccueillir(a)) {	//on peut accueillir l'animal
			nbHeberges++;		//on augmente le nombre d'animaux hébergés
			ret=true;
		}
		return ret;
	}

	@Override
	public void produire() {	//l'arbre produit du nectar en fonction de sa taille
		qteNectar += Math.pow(2, taille);	//qte = 2^taille		
	}

	@Override
	public void supprimer(Animal a) {
		/**
		 * Supprime un animal de l'arbre.
		 * Responsabilité : décompter l'animal lorsqu'il quitte l'arbre.
		 */
		if (nbHeberges > 0) {
			nbHeberges--;
		}
	}

}
