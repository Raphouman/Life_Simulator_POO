package model.world;

import java.awt.Point;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;


import model.agents.Agent;
//partie 2
//import model.agents.PointPositif;
import model.agents.Sexe;
import model.agents.Animal;
import model.agents.animaux.AbeilleDomestique;
import model.agents.animaux.AbeilleSolitaire;
import model.agents.animaux.FrelonAsiatique;
import model.agents.animaux.FrelonEuropeen;
import model.agents.animaux.Varroa;
import model.agents.vegetaux.Arbre;
import model.agents.vegetaux.Fleur;
import model.decor.Ruche;
import model.agents.Etat;


public class Monde {
	/**
	 * population d'agents dans le monde
	 */
	private Set<Agent>agents;		//set pour éviter les doublons mais pour ordre on utilise TreeSet (cf generateAgents)
	/**
	 * map de probabilité pour trouver un agent
	 */
	private static final Map<Integer,Agent> proba= Monde.probaAgent();
	/**
	 * constante: largeur du monde
	 */
	private static int LARGEUR = 30;
	/**
	 * constante: longueur du monde
	 */
	private static int LONGUEUR = 20;

	/** 
	 * constante : rayon de rencontre entre agents
	 */
	public static final int RAYON_RENCONTRE = 10;
	
	/**
	 * État jour/nuit du monde (STATIC pour être partagé par tous les agents)
	 */
	static private boolean estNuit = false;	//static car tout le monde doit pouvoir y accéder //private car on ne veut pas que les agents puissent la modifier eux meme
	
	/**
	 * 
	 * @param nbAgents
	 */
	
	public Monde(int nbAgents) {
		agents=generateAgents(nbAgents);
	}
	



	/**
	 * Pour chacun des agents, trouve et retourne une liste de ses voisins.
	 * Un voisin est un agent situé à une distance Manhattan <= RAYON_RENCONTRE.
	 * 
	 * @return Une Map où chaque agent est associé à l'ensemble de ses voisins
	 */
	private Map<Agent, Set<Agent>> gererRencontre() {
		Map<Agent, Set<Agent>> voisinsParAgent = new TreeMap<>(new CoordComparator()); // TreeMap pour stocker les voisins de chaque agent sous forme de clef-valeur, et que ce soi trié par coordonnées
		
		// Pour chaque agent
		for (Agent a : agents) {
			// Créer une liste des voisins de l'agent a
			Set<Agent> voisins = new TreeSet<>();
			
			// Parcourir tous les autres agents
			for (Agent b : agents) {
				if (a != b) {
					// Calculer la distance Manhattan
					int distance_X = Math.abs(b.getCoord().x - a.getCoord().x);
					int distance_Y = Math.abs(b.getCoord().y - a.getCoord().y);
					
					// OPTIMISATION : arrêter uniquement si distance_X > RAYON_RENCONTRE
					// (car les agents sont triés par x en premier)
					if (distance_X > RAYON_RENCONTRE) {
						break;
					}

					// Si le voisin est à proximité, l'ajouter à la liste des voisins ET LE FAIRE RENCONTRER AVEC A	
					if ((distance_X <= RAYON_RENCONTRE) && (distance_Y <= RAYON_RENCONTRE)) {
						voisins.add(b); //add car TreeSet
						a.rencontrer(b);
					}
				}
			}
			
			// Associer l'agent à la liste de ses voisins
			voisinsParAgent.put(a, voisins);	//clef = agent a, valeur = liste de ses voisins //put car HashMap 
		}
		
		return voisinsParAgent;
	}











	/**
	 * Méthode utilitaire statistique pour produire la table de proba
	 * d'apparition d'un agent
	 * @return
	 */
	private static Map<Integer, Agent> probaAgent() {
		Ruche r1 =new Ruche(new Point(10,50));
		Ruche r2 =new Ruche(new Point(100,20));
		Map<Integer,Agent> myMap = new LinkedHashMap<Integer,Agent>(); //ordre d'insertion conservé (LinkedHashMap)
	    myMap.put(20,new AbeilleDomestique(Sexe.Assexue,new Point(0,0),r1));
	    myMap.put(40,new AbeilleDomestique(Sexe.Assexue,new Point(0,0),r2));
	    myMap.put(50,new AbeilleSolitaire(Sexe.Assexue,new Point(0,0)));
	    myMap.put(55,new FrelonEuropeen(Sexe.Assexue,new Point(0,0)));
	    myMap.put(60,new FrelonAsiatique(Sexe.Assexue,new Point(0,0)));
	    myMap.put(70,new Varroa(Sexe.Assexue,new Point(0,0)));
	    myMap.put(80,new Arbre(new Point(0,0),1.0));
	    myMap.put(85,new Arbre(new Point(0,0),2.0));
	    myMap.put(100,new Fleur(new Point(0,0)));
	    return myMap;
	}
	
	/**
	 * fabrication aléatoire d'un Agent par tirage dans la Map
	 * et positionnement aléatoire
	 * @param alea
	 * @return
	 */
	private static Agent tirage(int alea) {	
		/*
		 * NE PAS TOUCHER!
		 */
		Agent agent=null;
		if(alea<100 && alea>=0) {	
			boolean trouve = false;
			Iterator<Integer> it = proba.keySet().iterator();
			while(!trouve && it.hasNext()) {
				Integer clef = it.next();
				if(clef>=alea) {
					agent = proba.get(clef);
					trouve=true;
				}
			}
		}
		else {
			agent = new Fleur(new Point(0,0));
		}
		//positionnement aléatoire entre Longueur et Largeur
		int aleaX = (int)(Math.random()*LONGUEUR);
		int aleaY = (int)(Math.random()*LONGUEUR);
		agent.setCoord(aleaX, aleaY);
		return agent;
	}

	private TreeSet<Agent> generateAgents(int nbAgents) {
			/*
			 * NE PAS TOUCHER!
			 */
		//TreeSet trie soit par ID (ordre de tri naturel dans Agent.java) si rien spécifié soit par CoordComparator (ordre de tri spécifiques : par coordonnées spatiales)
		TreeSet<Agent> ts = new TreeSet<Agent>(new CoordComparator());// Trier par coordonnées spatiales (x, y) pour améliorer les performances
																		// de gererRencontre() et avoir les agents proches les uns à côté des autres
		for(int i=0;i<nbAgents;i++) {
			int alea = (int)(Math.random()*100);	
			//partie 2
			//ts.add((Agent)tirage(alea).clone());
			//partie 1
			ts.add(copieAgent(tirage(alea)));	//ca copie et ca ajoute l'agent 
		}
		return ts;
	}
	/**
	 * à supprimer dès la partie deux lorsqu'il devient possible de
	 * cloner les agents
	 * place toutes les abeilles dans la même ruche
	 * les arbres ont tous la même taille
	 * ne respecte pas les bonnes pratiques (switch case sur le type) 
	 * @param tirage
	 * @return
	 */
	private Agent copieAgent(Agent tirage) {
		// TODO Auto-generated method stub
		Ruche ruche =new Ruche(new Point(10,50));
		Agent ret = null;
		String classeAgentName = tirage.getClass().getSimpleName();
		switch(classeAgentName) {
			case "AbeilleDomestique":
				AbeilleDomestique ad = (AbeilleDomestique) tirage;
				ret = new AbeilleDomestique(ad.getSexe(),ad.getCoord(),ruche);
				break;
			case "AbeilleSolitaire":
				AbeilleSolitaire as = (AbeilleSolitaire) tirage;
				ret = new AbeilleSolitaire(as.getSexe(),as.getCoord());
				break;
			case "FrelonEuropeen":
				FrelonEuropeen fe = (FrelonEuropeen) tirage;
				ret = new FrelonEuropeen(fe.getSexe(),fe.getCoord());
				break;
			case "FrelonAsiatique":
				FrelonAsiatique fa = (FrelonAsiatique) tirage;
				ret = new FrelonAsiatique(fa.getSexe(),fa.getCoord());
				break;
			case "Varroa":
				Varroa v = (Varroa) tirage;
				ret = new Varroa(v.getSexe(),v.getCoord());
				break;
			case "Arbre":
				Arbre a = (Arbre) tirage;
				ret = new Arbre(a.getCoord(),1.0);
				break;
			default:
				Fleur f = (Fleur) tirage;
				ret = new Fleur(f.getCoord());
				break;
		}
		return ret;
	}

	@Override
	public String toString() {
		String ret="";
		ret+="******************************\n";
		ret+="Le monde contient "+agents.size()+" agents:\n";
		
		//trier les agents par coordonnées (x,y) ==> partie 10.3 @TODO dans CoordComparator.java
		Set<Agent> coordComp = new TreeSet<Agent>(new CoordComparator());//on instantie un objet de type TreeSet trié avec la classe CoordComparator
		coordComp.addAll(agents);
		
		for(Agent a:coordComp) {	//parcours la liste TRIée PAR COORDS	//avant le tri par coords: for(Agent a:agents) ==> ordre selon compareTo dans Agent.java (id)
			ret+="\t"+a+"\n";		//a.toString() est appelé automatiquement donc le ToString de Agent.java
		}
		return ret;
	}

	/**
	 * génère un cycle de vie dans le monde
	 */
	public void cycle() {
		// Gérer les rencontres (qui appelle déjà rencontrer() sur chaque agent)
		gererRencontre();
		
		// Appeler le cycle individuel de chaque agent
		List<Agent> agentsASupprimer = new ArrayList<>();	//liste vide pour stocker les agents à supprimer
		for (Agent a : agents) {
			a.cycle();	//on fait vieillir, déplacer EN FONCTION DE L'HEURE, nourrir, maj, etc.
			
			//PUIS :
			// Vérifier si c'est un Animal mourant
			if (a instanceof Animal) {
				Animal animal = (Animal) a;	//on caste en Animal pour accéder à getNiveauSante()
				if (animal.getNiveauSante() == Etat.Mourant) {
					agentsASupprimer.add(a);
				}
			}
		}
		
		// Retirer les agents mourants du monde
		for (Agent aASupprimer : agentsASupprimer) {
			agents.remove(aASupprimer);
		}
	}
	
	/**
	 * Bascule le mode jour/nuit du monde.
	 * Appelée pour alterner entre jour et nuit.
	 */
	public void basculerModeNuit() {
		estNuit = !estNuit;
	}
	
	/**
	 * Vérifie si c'est la nuit.
	 * @return true si c'est la nuit, false si c'est le jour
	 */
	public static boolean getNuit() {
		return estNuit;
	}
	
	/**
	 * Force le mode nuit.
	 * @param nuit true pour la nuit, false pour le jour
	 */
	private void setNuit(boolean nuit) {
		estNuit = nuit;
	}
}