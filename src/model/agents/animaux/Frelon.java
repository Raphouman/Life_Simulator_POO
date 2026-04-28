package model.agents.animaux;

import java.awt.Point;
import java.util.ArrayList;
import model.agents.Agent;
import model.agents.Animal;
import model.agents.Sexe;

public abstract class Frelon extends Animal {
	/**
	 * list d'objets de type "Class"
	 * Ces types Class sont paramétrés par <? extends Animal>
	 * Cela signifie que la classe représentée par Class doit hériter de la classe Animal
	 */
	protected ArrayList<Class<? extends Animal>> proies;
	
	public Frelon(Sexe s,Point p) {
		super(s,p);
		proies = new ArrayList<>();	// initialisation de la liste des proies //pas besoin de préciser le type entre <> car déjà défini dans la déclaration de proies
		/*
		 * TODO: ajouter l'objet Class qui représente la classe Abeille à la liste proie
		 */
		
		proies.add(Abeille.class);			//ajout dynamique de classe Abeille abstraite
		proies.add(AbeilleDomestique.class);	//ajout dynamique
		proies.add(AbeilleSolitaire.class);	//ajout dynamique
	}
	
	@Override
	public void rencontrer(Agent a) {
		try {
			gestionProie((Animal)a);
		}
		catch (ClassCastException cce) {
			System.err.println(a+" n'est pas un Animal");
		}
		
	}
	
	protected void gestionProie(Animal a) {
		//si le frelon a faim et 
		// TODO: qu'il a bien affaire à une proie, c'est à dire que la classe de a 
		// appartient bien à la liste proies
		// supprimer le instanceof et remplacer par cette vérification
		
		if (faim && proies.contains(a.getClass())) {	
		faim = false;}	//le frelon n'a plus faim après avoir mangé
		



		//attention getClass() vérifie la classe exacte alors que instanceof vérifie toute la hiérarchie 
			//donc instanceof est tellement plus utile dans la plupart des cas
	
		// Vérifie que c'est bien une proie valide
		/*if (proies.contains(a.getClass())) {

			//Pareil ici, ce serait au Frelon asiatique de gérer son état (proie)
			// Cas 1 : Combat entre frelons Asiatique et Europeen → Asiatique toujours EnDetresse (jamais Mourant)
			
			if (a instanceof FrelonAsiatique) {
				a.subirAttaque(false);  // Toujours false pour EnDetresse
			}
			

			// Cas 2 : Attaque d'une abeille → dépend de la faim
			//c'est à l'abeille de gérer son état dans rencontrer()
			else {
				a.subirAttaque(faim);   // true = Mourant, false = EnDetresse
				if (faim) faim = false;
			}
			
    	}	*/
	}
	
	@Override
	protected void maj() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public  void seNourrir() {
		// TODO Auto-generated method stub
		
	}
	
}


