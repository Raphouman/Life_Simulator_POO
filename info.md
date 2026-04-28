### RECAP COURS
- Un **attribut**, c’est une information que l’objet possède. Analogie = variable, constante.
`class Personne {
    String nom;   // attribut
    int age;      // attribut
}`
- Une **méthode**, c’est une action que l’objet peut faire. Analogie = fonction.
`void parler() {
    System.out.println("Bonjour !");
}`
- Un constructeur, c’est une **méthode spéciale qui fabrique l’objet**.
`Personne(String n, int a) {
    nom = n;
    age = a;
}`
- Une **instance, c’est un objet réel**, créé à partir de la classe.
`Personne p = new Personne("Raphaël", 20);`
Ici, p est une instance de Personne
`new`==> Pour créer un objet = instance



# INFOS GENERALES

* Pour éxécuter, on appuie sur le boutton **run** au dessus du **main**.
* le mot `this`, permet de faire référence à l'instance créée ! Ici animal. **ATTENTION =! DE LA CLASSE ANIMAL**
==> *<ins>Les types et ordre des paramètres vont décider de quelles méthodes on appel<ins>*

* **Transtiper = Caster une donnée** ==> la changer de type. Attention au **tronquage/perte d'info**

# MARKDOWN
* *italique*  // ou _italique_
* **gras** ou __test__
* <ins>Texte souligné</ins> ou <u>Texte souligné</u>


# 📘 Réponses aux questions
---

# 4.1.4. Attributs et 4.1.5. Constructeurs



## ❓ Question 1 :  
*1. Quels sont les différents types de commentaires utilisés ?*

### ✅ Réponse :  
*Les différents types de commentaire utilisés sont:*
- les commentaires pour la documentation utilisateur (javadoc).
    `/** */`
- les commentaires pour le lecteur/programmeur du code
    `//` ou `/* */`
---

## ❓ Question 2 :  
*Quels attributs sont des types construits et de  types primitifs ?*

### ✅ Réponse :  
1. **Types primitifs** sont :

| Type     | Taille             | Exemple          |
|----------|------------------|-----------------|
| byte     | 8 bits            | byte x = 5;     |
| short    | 16 bits           | short x = 10;   |
| int      | 32 bits           | int x = 42;     |
| long     | 64 bits           | long x = 9000L; |
| float    | 32 bits           | float x = 3.14f;|
| double   | 64 bits           | double x = 2.718;|
| char     | 16 bits (Unicode) | char c = 'A';   |
| boolean  | 1 bit logique     | boolean ok = true;|

Donc ID, age sont primitifs (type int...)

2. **Types Construits** (type pas au dessus): 
*Les types construits sont ceux qui viennent d'une classe par exemple:*
    - Point (coord)
    - Etat
    - Sexe


---

## ❓ Question 3 :  
*Lesquels sont des attributs d'instance ? de classe ? [cours : 65 et 67-70]*

### ✅ Réponse :  
* **Attribut de classe** (***static***) :
Mémoire et valeur communes à toutes les instances. **Modifications visibles et partagées par toutes les instances.**
Utilisation typique : compteurs partagés, constantes de classe (souvent **static** final), caches partagés.*
* **Attribut d'instance** :
définit l'état propre à un objet. Chaque instance a ses propres valeurs. 1 exemplaire par object

**Attribut de classe** : 
- CurrentId (déclaré en **STATIC**)

**Attribut d' instance** : 
- id
- age
- coord
- etat
- sexe



---

## ❓ Question 4 :  
*que sont les types Etat et Sexe en java ? Comment s'utilisent-t-ils ? Où sont-ils déclarés ?
Ajoutez une valeur ASSEXUE à SEXE* ✅

### ✅ Réponse :  
* Etat et Sexe sont des énumérations Java (enum).
Un enum est un type référence spécial qui **définit un ensemble fini de constantes nommées**.
* Elles sont déclarées dans `Etat.java` et `Sexe.java` (package tp.model.agents).
* On utilise les valeurs avec la syntaxe TypeValeur.CONSTANTE (ex. Sexe.Male). Les comparaisons se font avec ==.

---

## ❓ Question 5 :  
*etat est initialisé directement au moment de sa déclaration : est-ce une bonne idée ?
Comment pourriez-vous faire autrement ?*

### ✅ Réponse :  
* *Initié avec la valeur par défaut `Normal` favorise la lisibilité et la simplicité. 
* C'est pas mal mais elle devrait pouvoir être initalisé individuellement en fonction du constructeur (animal). C'est donc moins flexible (et pas accès aux paramètres)
* **Initié dans le constructeur** :
`public Animal(Sexe sexe, Point coord) {
    this.age = 0;
    this.id = Animal.getUniqueId();
    this.sexe = sexe;
    this.coord = new Point(coord);
    this.etat = Etat.Normal;}` // **initialisation ici**


`public Animal(Sexe sexe) {
    this(sexe, new Point(0, 0));}` // appelle le constructeur principal qui fixe etat


`public Animal() {
    this(Sexe.Femelle);}` // construit avec la valeur par défaut de sexe et etat via chaînes de constructeurs


* **OU AUTRE METHODE : Utiliser une méthode privée d'initialisation commune**
`private void initDefaults() {
    this.age = 0;
    this.id = Animal.getUniqueId();
    this.etat = Etat.Normal;
    this.coord = new Point(0,0);
}`

`public Animal() {
    initDefaults();
    this.sexe = Sexe.Femelle;
}`

---

## ❓ Question 6 :  
*quel pilier de la POO est mis en œuvre lors de la déclaration des attributs ? Comment ?*

### ✅ Réponse :  
*C'est l'**encapsulation**.

* Pourquoi : on isole l'état interne d'un objet pour contrôler qui peut le lire ou le modifier.

* Comment : en déclarant les champs avec des modificateurs d'accès (private, protected, public) et en fournissant des accès contrôlés via des méthodes (getters/setters) ou des API publiques.*

**Règles à respecter** :

* Private les attributs
* Public les méthodes/constructeurs


---

## ❓ Question 7 :  
*quel mot-clef permet de faire référence à l'instance créée ?*

### ✅ Réponse :  
*c'est **this**. Et ca va appeler les différentes méthodes, en les choississant en fonction du **type** et de l'**ordre** des **paramètres** passé au **this**.*


---

## ❓ Question 8 :  
*un constructeur peut-il se servir d'un autre constructeur ?*

### ✅ Réponse :  
***Evidemment !***

---
## ❓ Question 9 :  
*peut-on créer un Animal d'âge 25 ? Est-ce une bonne chose ?*

### ✅ Réponse :  
* Là tous les constructeurs fixent `age=0` au moment de la création.
* Il n'existe pas de constructeur avec le paramètre `age` mais on pourrait le créer. Ou modifier la valeur de l'age avec un setter (méthode) :
`public void setAge(int nouvelAge) {
    if (nouvelAge >= 0) {
        this.age = nouvelAge;
    } else {
        throw new IllegalArgumentException("L'âge ne peut pas être négatif");
    }
}`

`Animal a = new Animal();
a.setAge(25);`

* Mais **ATTENTION** ce n'est pas une bonne chose : un animal naît toujours avec 0 an (sinon manque de cohérence et de réalisme)



---
## ❓ Question 10 :  
*peut-on créer un animal sans lui indiquer de coordonnées ? Comment le programme se
comporte-t-il ?*

### ✅ Réponse :  
*Oui et le programme se comporte très bien. Il appel this(sexe.Femelle) et initalise les coordonnées par défault (0,0) ==> Pas de NULL, pas de crash.*


---
# 4.1.6. Création des accesseurs et des mutateurs [cours 64]
---
## ❓ Question 1 :  
*quelle différence fondamentale y a-t-il entre une énumération et un objet classique ?*

### ✅ Réponse :  
* **Énumération** : (Màj avec Sexe.Male par ex...)
ensemble fini et constant de valeurs contrôlées (ex. Sexe, Etat, les directions...) 
* **Objet classique** (Création dynamique avec **new**)
 : représenter des entités avec état variable (ex. Animal, Point) 

* **Remarque** : L'opérateur **==** ; marche avec les énumérations mais avec les objets ça compare les références et non les valeurs (opérateur équals())
---
## ❓ Question 2 :  
*un mutateur ou un accesseur peut-il être privé ? Pourquoi ?*

### ✅ Réponse :  
*Oui, un accesseur ou un mutateur peut être privé*

**Quand un accesseur/mutateur est public**

* Accessible de partout (classe, sous-classes, autres classes).
* Utilisé pour exposer une partie de l'API : le code client peut consulter/modifier l'attribut.
* Exemple : public int getAge(), public void setAge(int newAge).

**Quand un accesseur/mutateur est privé**

* Accessible uniquement à l'intérieur de la classe.
* Utilisé pour l'usage interne — helpers, logique cachée, étapes intermédiaires.
* Exemple : private void setCoord(Point newCoord) chez toi.

**POURQUOI rendre un mutateur PRIVE ?**
* Immuabilité controlée : Le sexe d'un animal ne peut pas changer après création
* Logique réservée à la classe ==> ID d'un animal
* Eviter les erreurs : setCoord modifiables que via des méthodes contrôlées (seDeplacer()...). en effet seDeplacer PUBLIC mais setCoord PRIVATE

**POURQUOI rendre un accesseur privé ?**
* cacher une implémentation interne
---
## ❓ Question 3 :  
Comment qualifier la méthode Math.random() ? Que renvoie t elle comme type ?

### ✅ Réponse :  
**Math.random() est une méthode de classe (static).**
* On l'appelle directement sur la classe : Math.random() (pas besoin de créer une instance Math).
* Elle n'accède pas à l'état d'une instance (this).
* Elle est déclarée avec le modificateur static dans la classe Math.

**Retour : double entre 0.0 (inclus) et 1.0 (exclus).**
* Utilité : générer des nombres aléatoires pour simuler des comportements imprévisibles.
---
## ❓ Question 4 :  
*Qu'est que le transtypage (**cast**) de type primitif ? Comment l'utiliser ici ?*

### ✅ Réponse :  
**convertir une valeur d'un type primitif à un autre type primitif.**
* Ex : 
`double d = 3.14;
int i = (int) d;  // i=3 (perte de la partie décimale)`
*on peut l'utiliser dans la méthode se déplacer* :
`int dx = (int)(Math.random() * 3) - 1;`
Math.random() * 3 est un double alors que les coordonnées Point.x et Point.y sont des int ==> Java ne fait pas la conversion tout seul il faut **CAST** !
---
## ❓ Question 5 :  
* La méthode getUniqueId est déclarée statique, pourquoi ?
* pour fonctionner, vous aller avoir besoin d'utiliser quelquechose de la classe Animal. De quoi
s'agit-il ?
*Pourquoi la méthode getUniqueId() est elle private ?

### ✅ Réponse :  
* Parce qu'elle doit être **appelée depuis le constructeur** pour attribuer un ID à chaque nouvelle instance — et à ce moment, **l'instance n'existe pas encore** (on est en plein constructeur).
* Elle a besoin de l'attribut d'instance currentId (doit aussi être déclaré en static car partagé par toutes les instances et il faut donc maintenir un **compteur global**)
* Elle. est **private** car elle est destinée à un usage interne uniquement. **Une autre classe ne doit pas pouvoir incrémenter la variable** /attribut currentId qui est modifié dans le guetter getUniqueId() car sinon des Id serait créés sans être utilisé. #Les autres guetters ne modifient rien c'est pour cela qu'ils sont publics.



---
## ❓ Question 6 :  
**

### ✅ Réponse :  
**

# ✏️ Notes supplémentaires
* **Accesseurs (getters)** : méthodes qui lisent la valeur d'un attribut privé et la retournent.
Ex : `public TypeAttribut getNomAttribut() {
    return this.nomAttribut;
}`
* **Mutateurs (setters)** : méthodes qui modifient la valeur d'un attribut privé de manière contrôlée.
Ex : `public void setNomAttribut(TypeAttribut valeur) {
    this.nomAttribut = valeur;
}`

* Ensemble, ils implémentent l'**encapsulation**, tu ne manipules pas directement les champs, tu passes par des méthodes.*

* **Message d'erreur** : `System.err.println("");`








# Note pour plus tard :

- **interface** = c'est un **contract** que plusieurs classes différentes pourraient signer. Tous les classes qui ont besoin de travailler avec cette interface peuvent savoir quelles sont les autres classes qui travaillent avec cette interface.
- Une **interface** c'est un **comportement**, c'est un **type** ==> Deplacable ou Hebergeur
C'est un **outil** qui force les classes à utiliser des méthodes.

Pour une abeille domestique ces types sont : Object, Agent, Animal, Abeille, Abeille domestiques

- Pour la **classe animal**, une interface serait : (CmpAgent &) Hebergeur & Deplacable


- Une classe abeille hérite de la classe Animal ==> **Héritage**
- Une classe qui hérite d'aucune autre classe, hérite quand même de la **classe Object** (cf ToString dans le println du main) ==> Aller voir le code source de la classe Object !



---
## 4.2. Spécialisation d'Animal par rapport à sa classe mère Object
## 4.2.1. Redéfinition de comportements par défaut hérités d'Object
---

## ❓ Question 1 :  
*Quelle méthode permet de renvoyer une représentation sous forme de chaîne de caractères d'un
objet ? D'où vient-elle ? Quel est son comportement par défaut ?*

### ✅ Réponse 1:  
*Méthode : public String toString()
Rôle : retourner une représentation textuelle (chaîne de caractères) d'un objet., elle vient de **la classe Object (classe mère)**

---

## ❓ Question 2 :  
*Pourquoi n'y a-t-il pas de différence entre les deux affichages suivants :
`Animal a = new Animal();
System.out.println(a);
System.out.println(a.toString());`*

### ✅ Réponse 2:  
* Parce que System.out.println() appelle automatiquement toString() quand on lui passe un objet. *

---

## ❓ Question 2 :  
* 6. Quel(s) pilier(s) est(sont) mis en œuvre ici ?
* 7. Comment afficheriez-vous un message d'erreur dans la console ?*

### ✅ Réponse 2:  
* Dans la fonction toString() on utilise des guetters (dynamique) donc ==> **ENCAPSULATION** et je redéfinis toString( hérité de Object = classe Mère) ==> **HERITAGE ET POLYMORPHISME**

* Erreur dans la console : `System.out.println("Erreur");` **OU* `System.err.println("Erreur");`

---
## ❓ Question 3 :  
*COMPARAISON : DIFFFERENCE ENTRE == ET EQUALS*



### ✅ Réponse 3:  

* **Pour les types primitifs** (int, boolean, double, etc.) :

**Utilise TOUJOURS ==**
3 == 3 → true
a.getAge() == 2 → compare les valeurs ✅

- **Pour les énumérations (enum)** :
**Utilise ==** (pas .equals())
a.getSexe() == Sexe.Assexue → true (même instance enum)
Les enums sont des singletons — une seule instance par valeur ✅

- **Pour les objets classiques** (Point, Animal, String) :
 IL FAUT UTILISER **.equals()**
== compare les références (adresses mémoire)
.equals() compare le contenu*

- **REMARQUE : POUR LES STRINGS TOUJOURS UTILISER .equals** (car elles peuvent être les mêmes mais pourraient être stockées à des @mémoires diff et donc == pourrait renvoyer true comme false)

---
---
# 6.3. Conception d'une hiérarchie d'agents

## ❓ Question 1 :  
*Au premier TP, la méthode main était programmée dans la classe Animal.
Aujourd'hui, nous allons utilisez une classe spécifique dont le rôle est de lancer le programme.*

*1. Quel principe est mis en œuvre ?*
*2.pourquoi y-a-t-il une erreur ? Corrigez le programme.*

### ✅ Réponse 1:  
* La séparation des responsabilités (**Single Responsibility Principle — SRP = DELEGATION**)
* Car Animal pas reconnue comme héritage de Agent donc écrire 
public class Animal ==> **public class Animal extends Agent**

## ❓ Question 2 :  
*1. Quel avantage en terme de taille de code la hiérarchie entre Agent et Animal offre-t-elle ?*
*2. Quel principe est ainsi mis en œuvre ?*

### ✅ Réponse 2:  
* Réduction de la duplication de code (DRY — Don't Repeat Yourself)
* L'héritage (Inheritance) — l'un des trois piliers de la POO

## ❓ Question 3 :  
*La simulation va réaliser des cycles (ou tour) régulièrement. Chaque agent exécutera alors le même
algorithme à chaque tour :*

### ✅ Réponse 3:  
*`public final void cycle(){...}`*
* Final = les sous classes vont en hériter mais ne pourront pas l'@override !!!!!!!!

---
# Note pour plus tard :
- private (les classes extés ne peuvent pas modif) ==> protected (idem mais les sous classes peuvent modif)
- this. ==> attribut et méthode de cette classe ; this() ==> constructeur de cette classe
- super. ==> attribut et méthode de la classe mère; super() ==> constructeur de la classe mère

---
---
# 8. Les collections (1ère partie)

## ❓ Question 1 :  
* **`if(this instanceof Deplacable)` {	//est ce que this implémete déplacable
			`((Deplacable)this).seDeplacer();}`**	//le cast transforme this en deplacable car un agent de base ne sait pas se déplacer
		 ==> **Cast (transcryptage)**

### ✅ Réponse 1:  
* Agent est la classe de base :
Pas tous les agents se déplacent (une Ruche ne se déplace pas, une Abeille oui)
- L'interface Deplacable définit le contrat : "Je sais me déplacer"
Seules les classes qui implémentent Deplacable ont la méthode seDeplacer()
- Le compilateur refuse d'appeler this.seDeplacer() directement car Agent ne connaît pas cette méthode.*

## ❓ Question 2 :  
* protected abstract void seNourrir();
* public abstract void rencontrer(Agent a); 
* protected abstract void maj();

### ✅ Réponse 2:  
* Quand on abstract une classe on ne def pas son "code" mais ca veut dire que **toutes ses classes filles en auront besoin** et on definira son "code" dans les classes filles

* **ABSTRACTON** = (1) Interface/comportement (Hebergeur), pas obligé de les definir abstract car déjà par défaut (2) Classe Abstraite (3) Classe

## ❓ Question 3 :  
*@Override
	public int hashCode() {
		final int prime = 31; //nombre premier pour le calcul du hashcode (évite les collisions = les memes hashcode pour 2 objects différents)
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
		result = prime * result + id;
		return result;
	}*

### ✅ Réponse 3:  
* HASHCODE ==> 1. Trouver rapidement un objet dans une collection
* // ❌ Sans hashCode : (très lent)
for (Agent a : agents) {
    if (a.equals(recherche)) return a;  // Parcourt tout !
}

* // ✅ Avec hashCode : (très rapide)
agents.contains(recherche);  // Va directement !

## ❓ Question 4 :  
* pourquoi la méthode sInstaller d'Animal est-elle final ?
* pourquoi la méthode AggraverEtat et AmeliorerEtat d'Animal sont-elles finales ?

### ✅ Réponse 4:  
*	elle est final car **on ne veut pas que les classes filles la modifient / l'@Override** car elle est "logiquement" correcte pour toutes les classes filles*

## ❓ Question 5 : 
*quel est l'intérêt d'utiliser une interface Deplacable plutôt que de donner une méthode abstraite ?*

### ✅ Réponse 5: 
* Flexibilité hiérarchique (Une plante pouvant bouger ne pourra jamais se déplacer si on met une méthode abstraite)
* Partage de comportement sans parenté (fleur et abeille peuvent tous 2 se déplacer `if (fleur instanceof Deplacable) fleur.seDeplacer()`;
* Si certains animaux veulent ne pas pouvoir se déplacer alors c'est possible


## ❓ Question 6 : 
* `LinkedList<Etat> liste = new LinkedList<Etat>(Arrays.asList(Etat.values()));`		// création de la liste des états (transofrmation en tableau puis en liste)
* Obligation de définir lee type de classe de la LinkedList, ici <Etat> !!!!!

### ✅ Réponse 6: 
*new LinkedList ==> LinkedList est une liste CHAINEE donc chaque élément connait ses 2 voisins et compléxité de parcours de liste plus petite*

## ATTENTION : 
* **Il faut mettre `Iterator<Etat> it = liste.listIterator(liste.indexOf(etat));` pour accéder à has.next() et next**

* **Il faut mettre `ListIterator<Etat> it = liste.listIterator(liste.indexOf(etat));` pour accéder à has.previous() et previous car ListIterator EXTENDS Iterator**


## ❓ Question 7 : 
* Quel est l'inconvénient de cette solution en terme de factorisation de code ?
Au lieu de cela, on se propose d'utiliser une collection contenant des objets de type Class
représentant les proies potentielles d'un Frelon.
* En quoi cette solution est-elle préférable ? Quel(s) principe(s) de la POO sont ainsi pris en
compte ?*

### ✅ Réponse 7: 
* ❌ Le problème : Mauvaise factorisation de code ==> SI 10 proies alors 10 if instanceof...
* Utiliser une Collection de Class<?> ==> Principe de l'OPEN/CLOSED (OCP) et polymorphisme ?

## ❓ Question 8 : 
* Comment interpréteriez-vous la déclaration ArrayList<? extends Agent> liste;
* Que peut contenir cette liste ?
* POurquoi c'est mieux qu'une LinkedList (list chainée)

#### Remarque : (même pour les interfaces) ArrayList<? extends Hebergeur> liste; et non pas implements

### ✅ Réponse 8: 
* "Une ArrayList contenant des éléments dont le type est Agent **ou INCONNU = n'importe quelle sous-classe de Agent**"
* Peut contenir des animaux (liste.add(new Animal(Sexe.Femelle));) mais aussi des animaux concrets (abeilledomestique...,) mais aussi des végétaux... Mais ne peut pas contenir ce qui n'est pas Agent.

* Mieux car si on veut aller au n ième élément pas besoin de parcourir les n-1 éléments 
* Moins bien pour supprimer un élément en plein milieu de la liste

## ❓ Question 9 : 
* **Quelle méthode de la classe OBJECT renvoie un objet de type Class ?**
* Comment recup le nom de la classe

### ✅ Réponse 9: 
* public final Class<?> **getClass()**

Animal a = new Abeille(Sexe.Femelle)

* METHODE 1 : utiliser a.getClass()
* METHODE 2 : Abeille.class

## ❓ Question 10 : 
*Ajouter une collection population en attribut de la classe Ruche.
Vous choisirez la collection la plus pertinente pour la Ruche (une ArrayList n'est pas la solution
attendue...) parmi **LinkedList, HashMap et HashSet** *

### ✅ Réponse 10: 
Population = 
- pas de doublons
- Verif si une abeille existe déjà (avec LinkedList plus long)
- Ordre d'arrivée n'a pas d'importance
- Pas d'associations clé-valeur (sinon on aurait pris Hashmap)

## ❓ Question 11 : 
5. Pourquoi les abeilles ne sont-elles pas nécessairement classées par ordre d'insertion dans la
collection ?
6. Quelle(s) modification(s) faudrait-il apporter, et dans quelle(s) classe(s), pour que les abeilles
soient classées par ordre d'identifiant (donc par ordre de création dans le programme) ?

Regardez les différents comportements des HashSet, LinkedHashSet, TreeSet, etc...

### ✅ Réponse 11: 
5. Ordre d'arrivée n'a pas d'importance ==> HashSet utile le **HASHCODE** (pour les classer par ordre de hashcode, mais ultra rapide)
- LinkedList ==> Tri par ordre d'insertion ==> rapide aussi en compléxité
6. Remplacer HashSet par TreeSet (trié par ID) ==> Plus complexe mais ordre garanti
`private TreeSet<AbeilleDomestique> population;`
`population = new TreeSet<>((a1, a2) -> Integer.compare(a1.getId(), a2.getId()));`
Cette ligne au dessus renvoie <0 si IDa1<IDa2 ; =0 si égal et >0 si IDa1>IDa2


##### Methode de tri avec Integer car Ruche= decor != Agent (appel pas compareTo)
###### Remarque : HashSet, LinkedHashSet, TreeSet ==> Les 3 n'ont pas de doublons


`for (AbeilleDomestique a : population) {ret += "\t"+a+"\n";}		//appel automatique de a.toString() donc le toString de Agent.java si pas redéfini dans AbeilleDomestique.java ni Abeille.java ni Animal.java`

---
---
# Note pour plus tard :
* Remarque : pas obligé de mettre des accolades après les if si ils tiennent en 1 ligne
* public boolean equals(Object obj) {	//a.equals(b)
		if (this == obj)		//si a et b meme adresse memoire
			return true;

---
---
# Partie 10 : Le monde des abeilles


## ❓ Question 1 : 
1. quelle collection est utilisée pour contenir les Agents ? est-ce une bonne idée ?
2. grâce à quelle méthode cette collection est-elle initialisée ? A quel endroit ?

### ✅ Réponse 1: 
1. **Set<Agent>, TreeSet** ==> Avantage (pas de doublons, et TreeSet = trié par ID avec comparable) MAIS **INCONVENIENTS** (Ordre de tri non maitrisé et accès aléatoire impossible (genre liste[3]))

2. Dans le constructeur : `public Monde(int nbAgents) {agents=generateAgents(nbAgents);}` or dans `generateAgents` on a : **`TreeSet<Agent> ts = new TreeSet<Agent>();`**
Ici : 
- //TreeSet pour trier les agents par ordre NATUREL (id) ==> partie 10.2, a besoin de Comparable dans Agent.java
- //OU PAR METHODE DE TRI défini avec CoordComparator.java ==> partie 10.3

## ❓ Question 2 : erreur d'éxécution luncher monde
1. Implémentez l'interface Comparable qui permet de lever cette erreur.
**La comparaison se fera sur l'id de l'Agent.**
2. Était-ce une bonne idée de choisir un TreeSet plutôt qu'un HashSet pour agents ?

### ✅ Réponse 2: 
- **TreeSet a besoin que les objets soient comparables pour pouvoir les trier**
1. `public abstract class Agent` **`implements Comparable<Agent> {`**
`@Override public int compareTo(Agent other) {return Integer.compare(this.id, other.id);}`
- Comparable est destiné au tri naturel (en gros : le tri par défaut quand on range les Agents dans un TreeSet, par exemple). Comme c'est le tri naturel, il est "logique" d'implémenter ça directement dans la classe concernée (Agent)


2. Oui car ordre trié par ID garanti, pas de comportemebt aléatoire. (Hashset ==> ordre imprévisible et arbitraire avec le hashcode...)

## ❓ Question 3 : 
*créez un comparateur CoordComparator qui implémente Comparator<Agent> dans une classe
indépendante (ne faites pas de classe interne dans monde)*

### ✅ Réponse 3: 
- Comparator est plutôt pour des usages "spécifiques" (ex : dans le contexte X, j'aurai besoin de trier mes Agents différemment, par exemple sur leurs coordonnées).
1. on créée une nouvelle classe qui implémente Comparator, et qui est paramétrée avec Agent (class CoordComparator implements Comparator<Agent>)
2. on instancie un objet de cette classe (coordComp = new CoordComparator)
3. on fourni cet objet à la collection (new TreeSet<Agent>(coordComp) )

`Set<Agent> coordComp = new TreeSet<Agent>(new CoordComparator());`//on instantie un objet de type TreeSet trié avec la classe CoordComparator
`coordComp.addAll(agents);`

#### Remaque : CoordComp est un TreeSet car on veut trier via ce dernier (pour l'affichage seulement !!!) et PAS DIRECTEMENT agents (qui peut être un HashSet... dont on ne veut pas modifier l'ordre)
En gros :
- Les agents sont triés par ID (ordre de tri naturel) dans agents
- mais AFFICHéS par coords.x puis .y dans CoordComp

## ❓ Question 4 : 
* Créez une méthode gererRencontre() dans le monde qui, pour chacun des agents, renvoie une
liste des agents qu'il peut rencontrer (on les appellera les voisins de l'agent).
 Appeler la méthode rencontrer de l'agent avec chacun de ses voisins.
**Quels sont les problèmes que posent cette approche ?**

### ✅ Réponse 4: 
- ACCES MEMOIRE ENORME 
- A rencontre B et B rencontre aussi A ==> ca double pour rien
- Compléxité n^2
- A voit B avant que B voit C ==> peut causer des problèmes
- Distance Manhattan plutot que euclidienne ?

#### On triera alors les agents du Monde par coords au lieu de par ID pour que les agents proches dans l'espace soient parcourus successivement. ==> accès mémoire inefficaces supprimés
`if (distance_X > RAYON_RENCONTRE) {break;}`    car tous les agents suivants seront plus loin

REMARQUE : Voir 12.2.2.4 ==> cycle du monde pour remove les morts
## ❓ Question 5 : 
*Est-ce un problème d'ajouter une méthode getCoord() à Hebergeur alors que certains
Herbergeurs (Agents) ont déjà cette méthode héritée d'Agent ? Pourquoi ?*

### ✅ Réponse 5: 
*Non car en effet Ruche l'a déjà car hérite de Décor qui possède cette méthode, elle l'implémentera alors par héritage. MAIS on s'assure que **TOUS les hebergeurs** (même ceux qui n'héritent pas de DECOR) hérite de la méthdoe getCord()*
C'est du **POLYMORPHISME** ==> Hebergeur = Interface spécifiant le contrat et permet à tous les animaux d'appeler getCoord() sur **n'importe quel hebergeur sans connaitre son type exact**

## ❓ Question 6 : 
*Qui est responsable du changement de mode ?*

### ✅ Réponse 6: 
*Le Monde est responsable : il gere le cycle(), c'est lui qui gérera le mode nuit/jour*
## ❓ Question 7 : 
*La première abeille qui rentre dans la Ruche (pour apporter du miel ou à la nuit) sera également
celle qui en ressort en premier.*

### ✅ Réponse 7: 
*on remplace dans Ruche.java les TreeSet(ordre de création par ID) par des **LinkedList (FIFO ==> ordre d'insertion)**
La première abeille ajoutée avec add() sera la première accessible avec getFirst() ou removeFirst().*

---
---
PARTIE 2
---
# 14. Héritage et Abstraction
## ❓ Question 1 : 
*nous avons vu que seuls les Animaux se déplacent en section 6.3.5. Comment leur imposer de
disposer d'une méthode seDeplacer sans pour autant expliquer comment un animal se déplace
dans la classe Animal ?*

### ✅ Réponse 1: 
*Interface Deplacable,
public **abstract** class Animal extends Agent **implements Deplacable**
**abstract** public void seDeplacer(void)       //Comme ca on implemente pas de code mais on le def quand meme*

# Clonage : comment améliorer l'encapsulation
## ❓ Question 1 : 
### **CLONEABLE**

### ✅ Réponse 1: 
- x.clone() != x ==> True
- x.clone().getClass() == x.getClass() ==> True
- x.clone().equals(x) ==> True

public class CloneMain {

    public static void main(String []args) {
        Personne personne1 = new Personne(new Patronyme("Jean", "Dupond"), 30);
        Personne personne2 = (Personne) personne1.clone();

==> Attention quand on les **println** les **references/adresses sont différentes**

- Avec la class personne :

public class Personne implements Cloneable {

    private Patronyme patronyme;
    private int age;

    public Personne(Patronyme patronyme, int age) {
        this.patronyme = patronyme;
        this.age = age;
    }
      
    public Object clone() {
        Personne personne = null;
        try {
            // On récupère l'instance à renvoyer par l'appel de la 
              // méthode super.clone()
              personne = (Personne) super.clone();
        } catch(CloneNotSupportedException cnse) {
              // Ne devrait jamais arriver, car nous implémentons 
              // l'interface Cloneable
              cnse.printStackTrace(System.err);
        }
        
        // On clone l'attribut de type Patronyme qui n'est pas immuable.
        personne.patronyme = (Patronyme) patronyme.clone();
        
        // on renvoie le clone
        return personne;
    }
}


- return (Point) coord.clone()

## ❓ Question 2 : 
*public abstract Object clone();
2. quel est l'intérêt de cette manipulation ? Regardez les méthodes héritées d'Object : que veut-on
éviter en utilisant le mot-clef abstract ?*

### ✅ Réponse 2: 
Force toutes les sous-classes concrètes à implémenter leur propre méthode clone()
- Chaque type d'agent peut être correctement cloné selon ses spécificités
- La classe Agent devient elle meme clonable

- **Object.clone() renvoie un objet donc ABSTRACT évite que les classes filles oublie de caster**
## ❓ Question 3 : 
**

### ✅ Réponse 3: 
**
## ❓ Question 1 : 
**

### ✅ Réponse 1: 
**
## ❓ Question 1 : 
**

### ✅ Réponse 1: 
**