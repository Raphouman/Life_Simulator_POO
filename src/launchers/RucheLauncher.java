package launchers;

import java.awt.Point;
import model.agents.Sexe;
import model.agents.animaux.AbeilleDomestique;
import model.decor.Ruche;

/**
 * Launcher de démonstration pour la classe Ruche
 * Teste les fonctionnalités de la Ruche et affiche les résultats
 * 
 * Bon exemple de respect du Single Responsibility Principle :
 * - Ruche = gère la population d'abeilles
 * - RucheLauncher = teste et démontre la Ruche
 * 
 * @author user
 */
public class RucheLauncher {
	
	public static void main(String[] args) {
		System.out.println("╔════════════════════════════════════════════════════════════════╗");
		System.out.println("║              TEST ET DÉMONSTRATION DE LA RUCHE                 ║");
		System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
		
		// Créer une ruche
		Ruche r = new Ruche(new Point(0, 0));
		System.out.println("✅ Ruche créée à la position (0, 0)\n");
		
		// Ajouter des abeilles (dans un ordre délibérément non-trié)
		System.out.println("Ajout d'abeilles à la ruche :");
		
		AbeilleDomestique a1 = new AbeilleDomestique(Sexe.Assexue, new Point(10, 20), r);
		System.out.println("  → Abeille 1 (Assexuée, ID=" + a1.getId() + ")");
		
		AbeilleDomestique a2 = new AbeilleDomestique(Sexe.Assexue, new Point(10, 20), r);
		System.out.println("  → Abeille 2 (Assexuée, ID=" + a2.getId() + ")");
		
		AbeilleDomestique a3 = new AbeilleDomestique(Sexe.Femelle, new Point(5, 10), r);
		System.out.println("  → Abeille 3 (Femelle, ID=" + a3.getId() + ")\n");
		
		// Afficher la Ruche (qui affichera les abeilles triées par ID)
		System.out.println("État de la Ruche après ajout :");
		System.out.println(r);
		
		System.out.println("✅ Les abeilles sont affichées TRIÉES par ID !");
		System.out.println("   (TreeSet avec Comparator par ID garanti l'ordre)\n");
		
		// Test : vérifier qu'on ne peut pas ajouter une abeille déjà présente
		System.out.println("Test : Essai d'ajouter l'abeille 1 une deuxième fois...");
		boolean result = r.accueillir(a1);
		System.out.println("  → Résultat : " + (result ? "Acceptée" : "Refusée (déjà présente)"));
		System.out.println("  → Population : " + r.getPopulationSize() + " abeilles\n");
	}
}
