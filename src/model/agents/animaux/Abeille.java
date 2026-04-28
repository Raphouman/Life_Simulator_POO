package model.agents.animaux;

import java.awt.Point;
import model.agents.Agent;
import model.agents.Animal;
import model.agents.Etat;
import model.agents.Sexe;
import model.agents.vegetaux.Vegetal;
import model.comportements.Hebergeur;
/**
 * Abeille est un hébergeur pour ses parasites (Varroa par exemple)
 * @author bruno
 *
 */
public abstract class Abeille extends Animal implements Hebergeur{
	/**
	 * parasite éventuel de l'abeille
	 * si l'abeille est parasitée, passe à true
	 */
	private boolean parasite=false;
	/**
	 * quantité de miel transporté par l'abeille
	 */
	private int qteMiel = 0;
	private int qteNectar = 0;
	/**
	 * constante donnant la quantité maximal de miel que l'abeille peut transporter
	 */
	private static final int qteMielMax = 10;
	private static final int qteNectarMax = 10;
	
	public Abeille(Sexe s, Point p) {
		super(s,p);
	}

	/**
	 * Renvoie la quantité de miel transportée par l'abeille.
	 * @return quantité de miel
	 */
	public int getQteMiel() {
		return qteMiel;
	}
	public int getQteNectar() {
		return qteNectar;
	}
	
	/**
	 * Vide complètement le miel transporté par l'abeille.
	 * Utilisée quand l'abeille rentre à la ruche.
	 * @return la quantité vidée
	 */
	public int viderMiel() {
		int qte = qteMiel;
		qteMiel = 0;
		return qte;
	}
	public int viderNectar() {
		int qte = qteNectar;
		qteNectar = 0;
		return qte;
	}

	@Override
	public void rencontrer(Agent a) {
		/* rencontres */
		/* Accueil: ne pas coder ici
		 * c'est à l'animal rencontré d'appeler cette méthode
		 * 
		 * if(a instanceof Animal) {accueillir((Animal)a);}
		 */
		if(a instanceof Vegetal && qteMiel<Abeille.qteMielMax) {
			Vegetal v = (Vegetal)a;
			int nectar = v.getPortionNectar();
			int miel = v.getPortionMiel();
			if ((nectar > 0 ||  miel>0 ) && (qteMiel <= qteMielMax || qteNectar <= qteNectarMax)) {
				manger();	// Réinitialiser le compteur de faim
			}
		}
		/* rencontre avec un prédateur */
		else if(a instanceof Frelon && getNiveauSante()!=Etat.Mourant) {
			setNiveauSante(Etat.EnDetresse);
			if (a.aFaim()) {setNiveauSante(Etat.Mourant);}
		}
	}

	@Override //de Hebergeur.java
	public boolean peutAccueillir(Animal a) {	//retourne vrai si l'animal a peut être accueilli
		/*
		 * l'abeille n'a pas de parasite et l'animal est un Varroa
		 */
		return a instanceof Varroa && !parasite;	//si l'abeille est déjà parasitée, elle ne peut pas en accueillir un autre
	}

	@Override //de Hebergeur.java
	public boolean accueillir(Animal a) {	//retourne vrai si l'accueil a réussi
		boolean ret = false;
		if(peutAccueillir(a)) {
			parasite = true;
			aggraverEtat();
			ret = true;
		}
		return ret;
	}
	
	@Override //de Hebergeur.java
	public void supprimer(Animal a) {
		/**
		 * Supprime un parasite de l'abeille.
		 * Responsabilité : nettoyer les références internes de l'abeille.
		 */
		if (a instanceof Varroa) {
			parasite = false;
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

}
