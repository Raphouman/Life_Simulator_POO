package model.agents;

import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import model.comportements.Deplacable;
import model.comportements.Hebergeur;

/**
 * Cette classe modélise un Animal dans la simulation
 * @author bruno
 *
 */
/* 
 * abstract à partir du TP2 + déplacement des méthodes/attributs du TP1 concernant les agents dans la classe agent:
 * attributs de classe 
	private static int currentId = 0;
	
	attributs d'instance:
	private int id;
	protected int age;
	protected PointPositif coord; //question subsdiaire du tp2 + solution présentée au cours 4
	//protected Point coord;
	 
	méthodes:
	public Agent(Point coord)
	public Agent()
	
	equals, hascode,tostring (sans le sexe)
	getCoord, setAge, vieillir
	
	getUniqueId
	
	Attention: rencontrer(Agent a) devient abstrait 
 */
public abstract class Animal extends Agent implements Deplacable {
	/*
	 * SeDeplacer: soit abstract, soit encore mieux faire une interface Deplacable
	 */
	
	/** état de santé de l'animal */
	private Etat etat=Etat.Normal;
	/** sexe de l'animal */
	private Sexe sexe;
	/** hebergeur de l'animal */
	protected Hebergeur hebergeur;
	/** nombre de tours sans se nourrir */
	private int toursDepuisRepas = 0;
	/** seuil de tours avant que la santé diminue */
	private static final int SEUIL_FAIM = 5;	//statique car commun à tous les animaux
	
	/* 
	 * constructeurs 
	 */
	public Animal(Sexe sexe, Point p) {
		super(p);
		this.sexe=sexe;
	}
	
	public Animal(Sexe sexe) {
		this(sexe,new Point(0,0));
		//TODO
		/* crée un animal avec le sexe passé en paramètre, à la position (0;0), d'âge 0 et lui attribue un id unique
		 * une bonne manière de faire 
		 * en utilisant ce qui existe déjà, une moins bonne
		 */
	}
	
	public Animal() {
		this(Sexe.Femelle);
		//TODO
		/* crée un animal de sexe femelle, à la position (0;0), d'âge 0 et lui attribue un id unique
		 * une bonne manière de faire 
		 * en utilisant ce qui existe déjà, une moins bonne
		 */
	}
	
	/*
	 *  Accesseurs et mutateurs
	 */
	public Sexe getSexe() {
		return sexe;
	}
	
	public Etat getNiveauSante() {return etat;}
	/**
	 * protected, car seul l'animal doit pouvoir changer son niveau de santé
	 * @return
	 */
	protected void setNiveauSante(Etat e) {etat = e;}


	// pour qu'on puisse modifier l'état de santé de l'animal/la proie lorsqu'elle est attaqué
	/* //BON CAR C'EST à LA PROIE DE GÉRER SON PROPRE ÉTAT (dans la méthode rencontrer de la proie)
	public void subirAttaque(boolean mortel) {
		if (mortel) {
			this.setNiveauSante(Etat.Mourant);
		} else if (getNiveauSante() != Etat.Mourant) {
			this.setNiveauSante(Etat.EnDetresse);
		}
	}
	*/
	
	/**
	 * Gère la mort de l'animal en nettoyant les références.
	 * Notifie l'hébergeur (si applicable) pour qu'il se déréférence.
	 * Respecte le Single Responsibility Principle :
	 * - Animal gère son propre nettoyage
	 * - Hebergeur gère le nettoyage de ses références
	 */
	public void mourir() {
		setNiveauSante(Etat.Mourant);
		
		// Si l'animal a un hébergeur (ex: ruche), le notifier pour qu'il se déréférence
		if (hebergeur != null) {
			hebergeur.supprimer(this);
		}
	}



	
	/*
	 * (non-Javadoc)
	 * @see complet.model.agents.Agent#toString()
	 */
	
	public String toString() {
		//bien penser à réutiliser l'existant de Agent avec le super.toString()
		return super.toString()+", "+ getSexe();
	}
	
	/* 
	 * comportements d'instance
	 */
	/*
	 * (non-Javadoc)
	 * @see complet.model.comportements.Deplacable#seDeplacer()
	 */
	/**
	 * déplacement aléatoire avec -1<=dx<=1 et  -1<=dy<=1
	 * @see model.comportements.Deplacable#seDeplacer()
	 */
	
	public void seDeplacer() {
		int aleaX = (int)(Math.random()*3)-1;
		int aleaY = (int)(Math.random()*3)-1;
		this.setCoord((int)(coord.getX()+aleaX),(int)(coord.getY()+aleaY));
	}
	
	/**
	 * En mode nuit, l'animal se déplace vers son hébergeur.
	 * Se rapproche d'une unité par cycle jusqu'à atteindre l'hébergeur.
	 * Une fois aux mêmes coordonnées, l'animal ne bouge plus.
	 */
	public void seDeplacerLaNuit() {
		if (hebergeur != null) {
			Point hebergeurCoord = hebergeur.getCoord();
			int dx = Integer.compare(hebergeurCoord.x, coord.x);
			int dy = Integer.compare(hebergeurCoord.y, coord.y);
			this.setCoord((int)(coord.getX()+dx), (int)(coord.getY()+dy));
		}
	}
	
	/**
	 * condition d'installation d'un animal dans un hébergeur
	 * @param h
	 * @return
	 */
	//elle est final car on ne veut pas que les classes filles la modifient car elle est "logiquement" correcte pour toutes les classes filles
	protected final boolean sInstaller(Hebergeur h) {	//retourne vrai si l'installation a réussi mais INSTALLE l'animal dans l'hébergeur AUSSI
		boolean ret=false;
		if(h!= null && h.accueillir(this)) {	// si l'hébergeur n'est pas null et accepte l'animal
			hebergeur = h;
			ret=true;
		}
		return ret;
	}
	
	protected final void aggraverEtat() {		//fait empirer l'état de santé de l'animal d'un cran
		/* détail sur plusieurs lignes de:
		 * 	LinkedList<Etat> liste = new LinkedList<Etat>(Arrays.asList(Etat.values()));

		Etat[] tableauEtat = Etat.values();
		List<Etat> listeEtat = Arrays.asList(tableauEtat);
		LinkedList<Etat> liste = new LinkedList<Etat>(listeEtat);
		*/
		LinkedList<Etat> liste = new LinkedList<Etat>(Arrays.asList(Etat.values()));		// création d'une LinkedList (nommée liste) à partir de la liste des états (transofrmation en tableau puis en liste puis en LinkedList)
		// ArrayList<Etat> liste = new ArrayList<Etat>(Arrays.asList(Etat.values()));
		/* détail de
		 * Iterator<Etat> it = liste.listIterator(liste.indexOf(etat));
		 
		int indexEtatActuel = liste.indexOf(this.etat); (inutile avec l'algo suivant)
		Iterator<Etat> it = liste.listIterator();
		boolean trouve = false;
		while(it.hasNext() && !trouve) {
			if(it.next().equals(this.etat)) {trouve=true;}
		}
		*/
		Iterator<Etat> it = liste.listIterator(liste.indexOf(etat)); 	//positionne l'itérateur it sur l'état actuel etat //Itrerator n'a accès qu'à has.next() et next() pas previous ni has.previous()
		if(it.hasNext()) {etat = it.next();}		//passe à l'état suivant si possible
	}
	
	protected final void ameliorerEtat() {
		/*
		 * TODO 3 lignes pas plus!
		 */
		LinkedList<Etat> liste = new LinkedList<Etat>(Arrays.asList(Etat.values()));
		ListIterator<Etat> it = liste.listIterator(liste.indexOf(etat)); 	//ListIterator étend Iterator et permet de reculer dans la liste (previous)
		if(it.hasPrevious()) {etat = it.previous();}
	}
	
	/**
	 * Réinitialise le compteur de faim de l'animal.
	 * Appelée lors d'une rencontre avec un végétal (prise de nourriture).
	 */
	public void seNourrir() {
		// Si l'animal n'a pas mangé pendant longtemps
		if (toursDepuisRepas >= SEUIL_FAIM) {
			// Aggraver l'état (santé diminue)
			aggraverEtat();
			this.faim = true;	// L'animal a faim
		} 
		// Sinon, s'il vient de manger
		else if (toursDepuisRepas == 0) {
			// Améliorer légèrement l'état
			ameliorerEtat();
			this.faim = false;	// L'animal n'a pas faim
		}
		
		// dans tous les cas, incrémenter le compteur de tours sans manger
		toursDepuisRepas++;
	}
	
	/**
	 * Réinitialise le compteur de faim après que l'animal ait mangé.
	 * Appelée lors d'une rencontre avec un végétal.
	 */
	public void manger() {
		toursDepuisRepas = 0;
		//on gère l'état et la faim dans seNourrir()
		//ameliorerEtat();	// Améliorer la santé immédiatement après avoir mangé
		//this.faim = false;	// L'animal n'a plus faim
	}

}
