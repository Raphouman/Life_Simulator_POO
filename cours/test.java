class Stylo implements MoyenEcriture{
    private Couleur couleur; //= Couleur.Bleu;

    public Stylo() {
        this(Couleur.Bleu); //this = appel au constructeur si il existe
    }

    public Stylo(Couleur c) {
        couleur = c;
    }

    public Couleur getCouleur() {
        //return Couleur.Bleu;
        return couleur;
    }

    public void ecrire() {
        System.out.println("Écriture au stylo en " + couleur);  // Implémentation de la méthode d'écriture
        System.out.println("Le stylo écrit en " + getCouleur());
        // Ajoutez ici le code pour gérer l'écriture avec le stylo
        
    }
}								     





//POUR COMPILER tous les fichiers java : javac *.java
//POUR EXECUTER : java Launcher
//error = no pointer constructor Stylo() = on a pas definit le constructeur
