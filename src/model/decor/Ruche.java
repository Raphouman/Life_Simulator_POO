package model.decor;

import java.awt.Point;
import java.util.ArrayDeque;
import model.agents.Animal;
import model.agents.animaux.AbeilleDomestique;
import model.comportements.Hebergeur;

public class Ruche extends Decor implements Hebergeur{
	
	/**
	 * Liste des abeilles de la ruche (LinkedList pour respecter l'ordre FIFO)
	 */
	/**
	 * constante taille maximale de la ruche
	 */
	private static int populationMax = 1000;
	private ArrayDeque<AbeilleDomestique> population;	//ArrayDeque = LinkedList pour respecter l'ordre FIFO : première arrivée = première sortie
	
	/**
	 * Stock de miel/nectar accumulé dans la ruche
	 */
	private int stockMiel = 0;
	private int stockNectar = 0;


	public Ruche(Point p) {
		super(p);
		// TreeSet avec Comparator pour trier par ID (ordre de création) ==> besoin car Ruche = Decor != Agent donc appel pas Comparable<Agent> = CompareTo
		//Integer est une classe 
		
		//AVANT LINKEDLIST : // population = new TreeSet<>((a1, a2) -> Integer.compare(a1.getId(), a2.getId())); //pas beson de réécrire <AbeilleDomestique> car le type est déjà défini dans private TreeSet<AbeilleDomestique> population;	
		
		// LinkedList pour respecter l'ordre FIFO : première arrivée = première sortie
		population = new ArrayDeque<>();	//LinkedList en FIFO
	}

	@Override //de Hebergeur.java
	public boolean peutAccueillir(Animal a) {
				 /*&& 
				&& //population ok
				//l'abeille n'appartient pas déjà à la ruche
				 */
		return ((a instanceof AbeilleDomestique) && population.size()<populationMax) && (!population.contains((AbeilleDomestique)a));
	}

	@Override //de Hebergeur.java
	public boolean accueillir(Animal a) {
		boolean ret = false;
		if(peutAccueillir(a)) {
			/* Ne pas faire ça ici: c'est à l'animal de mettre à jour ses attributs
			 * a.setHebergeur(this);
			 */
			population.add((AbeilleDomestique)a);
			ret=true;
		}
		return ret;
	}
	

	@Override	//de Hebergeur.java
	public void supprimer(Animal a) {
		/**
		 * Supprime une abeille de la ruche.
		 * Responsabilité : dérégérencer les animaux de la ruche.
		 */
		if (a instanceof AbeilleDomestique) {
			population.remove((AbeilleDomestique)a);
		}
	}
	



	public int getPopulationSize() {		//population private mais guetter public pour le main de test dans RucheLauncher (fichier à part)
		return population.size();
	}
	
	/**
	 * Ajoute du miel/nectar au stock de la ruche.
	 * Appelée quand une abeille domestique revient avec du nectar.
	 * @param qteMiel quantité de miel à ajouter
	 */
	public void deposerMiel(int qteMiel) {
		stockMiel += qteMiel;
	}
	public void deposerNectar(int qteNectar) {
		stockNectar += qteNectar;
	}
	
	/**
	 * Renvoie le stock de miel actuel de la ruche.
	 * @return quantité de miel stockée
	 */
	public int getStockMiel() {
		return stockMiel;
	}
	public int getStockNectar() {
		return stockNectar;
	}
	
	@Override
	public String toString() {
		String ret = getClass().getSimpleName() +" (" + getCoord().x + "," + getCoord().y + ") ; " + " population: " + population.size() + " abeilles ; stock miel: " + stockMiel + " ; stock nectar: " + stockNectar + "\n";
		for (AbeilleDomestique a : population) {
			ret += "\t"+a+"\n";			//appel automatique de a.toString() donc le toString de Agent.java si pas redéfini dans AbeilleDomestique.java ni Abeille.java ni Animal.java
		}
		/*
		 * "\t" code une tabulation dans une chaine de caractères
		 * "\n" un saut de ligne 
		 */
		return ret;
	}



	//main déplacé dans RucheLauncher.java
}

