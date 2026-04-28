public class Launcher {
    public static void main(String[] args) {
        Stylo s1;                 //je déclare
        s1 = new Stylo();         //je construit
        s1.ecrire();                    
        MoyenEcriture s2 = new Stylo(Couleur.Bleu);     //on peut faire tout en 1 ligne
        s2.ecrire();
    }
}
