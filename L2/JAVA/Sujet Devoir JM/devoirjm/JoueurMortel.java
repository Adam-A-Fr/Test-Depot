package devoirjm;

import java.util.ArrayList;

/**
 * <p>
 * Un joueur pour le jeu Zork, ayant la particularité de devoir s'alimenter pour
 * pouvoir survivre et de pouvoir ramasser et transporter des aliments dans un
 * garde-manger. Un {@code JoueurMortel} est entièrement caractérisé par:
 * <ul>
 * <li>son nom;</li>
 * <li>son capital de points de vie;</li>
 * <li>la pièce dans laquelle il se trouve;</li>
 * <li>les aliments transportés par ce joueur dans son garde-manger. Un
 * {@code JoueurMortel} peut transporter un nombre illimité d'aliments.</li>
 * </ul>
 * </p>
 *
 * <p>
 * A sa création un {@code JoueurMortel} possède un capital de points de vie
 * à sa valeur maximale (i.e. {@code Joueur.MAX_CAPITAL_VIE}), qui va diminuer ou
 * augmenter à chaque fois qu'il effectue une action, ce nombre pouvant varier
 * en fonction de l'action réalisée. Un {@code JoueurMortel} ne peut effectuer
 * une action que s'il est vivant. Dans cette version de {@code JoueurMortel}
 * les seules actions implémentées sont:
 * <dl>
 * <dt>se déplacer d'une pièce à l'autre:</dt>
 * <dd>chaque déplacement fait diminuer de 10 points le capital vie du joueur et
 * le nombre de points de vie du joueur diminue d'une unité supplémentaire par
 * aliment transporté;</dd>
 * <dt>ramasser un aliment présent dans la pièce courante:</dt>
 * <dd>l'aliment ramassé est retiré de la pièce courante et est ajouté au
 * garde-manger du joueur. Cette action coûte 2 points de vie au joueur;</dd>
 * <dt>manger:</dt>
 * <dd>si le {@code JoueurMortel} possède au moins un aliment, l'action manger
 * retire un aliment des aliments qu'il transporte dans son garde-manger et
 * augmente son capital vie de la valeur nutritive de cet aliment dans la limite
 * du capital vie maximal {@code JoueurMortel.MAX_CAPITAL_VIE}.</dd>
 * </dl>
 * Lorsque le capital vie du joueur atteint (ou passe en dessous de) la valeur
 * de 0 points de vie, le {@code JoueurMortel} meurt définitivement et ne peut
 * donc plus faire aucune action, ni même s'alimenter.
 * </p>
 *
 *
 * @invariant getCapitalVie() <= JoueurMortel.MAX_CAPITAL_VIE;
 * @invariant estVivant() <==> (getCapitalVie() > 0);
 * @invariant !contient(null);
 * @invariant getNom() != null;
 * @invariant getPieceCourante() != null;
 *
 * @author Marc Champesme (pour la documentation)
 * @author <ABADA 12000059> (pour l'implémentation)
 * @since 6 mars 2013
 * @version 28 septembre 2022
 */
public class JoueurMortel {
	// À compléter
	private String Nom;
	public static final int MAX_CAPITAL_VIE=100;
	private int CapitalVie;
	private Piece PieceCourante= new Piece("PieceCourante");
	private ArrayList<Aliment> gardeManger = new ArrayList<Aliment>();
	
	/**
	 * Initialise un nouveau {@code JoueurMortel} vivant situé dans la pièce
	 * spécifiée et portant le nom spécifié. Le garde-manger de ce nouveau
	 * {@code JoueurMortel} est vide.
	 *
	 * @requires nom != null;
	 * @requires pieceCourante != null;
	 * @ensures estVivant();
	 * @ensures getNom().equals(nom);
	 * @ensures getPieceCourante().equals(pieceCourante);
	 * @ensures getCapitalVie() == JoueurMortel.MAX_CAPITAL_VIE;
	 * @ensures gardeMangerEstVide();
	 *
	 * @param nom
	 *            le nom de ce {@code JoueurMortel}.
	 * @param pieceCourante
	 *            la pièce dans laquelle se trouve ce {@code JoueurMortel}.
	 */
	public JoueurMortel(String nom, Piece pieceCourante) {
		// À compléter
		this.Nom=nom;
		this.PieceCourante=pieceCourante;
		this.CapitalVie=MAX_CAPITAL_VIE;
		//this.gardeManger.clear();
	}

	/**
	 * Initialise un nouveau {@code JoueurMortel} vivant portant le nom
	 * spécifié, situé dans la pièce spécifiée et dont le garde-manger contient
	 * les {@code Aliment} spécifiés. Le garde-manger de ce nouveau
	 * {@code JoueurMortel} contient les {@code nbAliments} premiers éléments du
	 * tableau spécifié.
	 *
	 * @requires nom != null;
	 * @requires pieceCourante != null;
	 * @requires tabAliments != null;
	 * @requires (nbAliments >= 0) && (nbAliments <= tabAliments.length);
	 * @requires (\forall int i; i >= 0 && i < nbAliments; tabAliments[i] !=
	 *           null);
	 * @ensures estVivant();
	 * @ensures getNom().equals(nom);
	 * @ensures getPieceCourante().equals(pieceCourante);
	 * @ensures getCapitalVie() == JoueurMortel.MAX_CAPITAL_VIE;
	 * @ensures getNbAliments() == nbAliments;
	 * @ensures (\forall int i; i >= 0 && i < nbAliments;
	 *          contient(tabAliments[i]));
	 *
	 * @param nom
	 *            le nom de ce {@code JoueurMortel}.
	 * @param pieceCourante
	 *            la pièce dans laquelle se trouve ce {@code JoueurMortel}.
	 * @param tabAliments
	 *            le tableau dont les {@code nbAliments} premiers éléments
	 *            doivent être ajoutés au garde-manger de ce
	 *            {@code JoueurMortel}.
	 * @param nbAliments
	 *            nombre d'{@code Aliment} du tableau à ajouter au garde-manger
	 *            de ce {@code JoueurMortel}.
	 */
	public JoueurMortel(String nom, Piece pieceCourante, Aliment[] tabAliments,
			int nbAliments) {
		// À compléter
		this.Nom=nom;
		this.PieceCourante=pieceCourante;
		this.CapitalVie=MAX_CAPITAL_VIE;
		for(int i=0;i<nbAliments;i++){
			this.gardeManger.add(i,tabAliments[i]);
		}
	}

	// Accesseurs:
	/**
	 * Renvoie le nom de ce {@code JoueurMortel}.addall arraylist java
	 *
	 * @return le nom de ce {@code JoueurMortel}.
	 *
	 * @pure
	 */
	public String getNom() {
		return this.Nom;
	}

	/**
	 * Renvoie le nombre de points de vie de ce {@code JoueurMortel}.
	 *
	 * @return le nombre de points de vie de ce {@code JoueurMortel}.
	 *
	 * @pure
	 */
	public int getCapitalVie() {
		return this.CapitalVie;
	}

	/**
	 * Renvoie {@code true} si ce {@code JoueurMortel} a un capital vie
	 * strictement supérieur à 0.
	 *
	 * @return {@code true} si ce {@code JoueurMortel} a un capital vie
	 *         strictement supérieur à 0; {@code false} sinon.
	 *
	 * @pure
	 */
	public boolean estVivant() {
		if(this.CapitalVie>0)
			return true;
		return false;
	}

	/**
	 * Renvoie la {@code Piece} dans la quelle se trouve ce
	 * {@code JoueurMortel.}
	 *
	 *
	 * @return la {@code Piece} dans la quelle se trouve ce
	 *         {@code JoueurMortel.}
	 *
	 * @pure
	 */
	public Piece getPieceCourante() {
		return this.PieceCourante;
	}

	// Accesseurs du garde-manger:

	/**
	 * Renvoie une nouvelle instance de la classe {@code Aliment[]} dont les
	 * éléments sont tous les {@code Aliment} présents dans le garde-manger de
	 * ce {@code JoueurMortel}.
	 *
	 * @ensures \result != null;
	 * @ensures \result.length == getNbAliments();
	 * @ensures (\forall int i; i >= 0 && i < getNbAliments();
	 *          contient(\result[i]));
	 * @ensures (\forall Aliment al; contient(al); (\exists int i; i >= 0 && i <
	 *          getNbAliments(); \result[i].equals(al)));
	 *
	 * @return Un tableau contenant tous les {@code Aliment} du garde-manger de
	 *         ce {@code JoueurMortel}.
	 * @pure
	 */
	public Aliment[] getTabAliments() {
		Aliment[] TabAliments = new Aliment[this.getNbAliments()];
		int j=0;
		for(int i=0;i<this.gardeManger.size();i++){
			if(null != this.gardeManger.get(i)){
				TabAliments[j++]=this.gardeManger.get(i);
			}	
		}
		return TabAliments;
	}

	/**
	 * Renvoie {@code true} si ce {@code JoueurMortel} ne possède aucun
	 * {@code Aliment}.
	 *
	 * @ensures \result == (getNbAliments() == 0);
	 *
	 * @return {@code true} si ce {@code JoueurMortel} ne possède aucun
	 *         {@code Aliment}; {@code false} sinon.
	 * @pure
	 */
	public boolean gardeMangerEstVide() {
		return this.gardeManger.isEmpty();
	}

	/**
	 * Renvoie le nombre d'{@code Aliment} présents dans le garde-manger de ce
	 * {@code JoueurMortel}.
	 *
	 * @ensures \result >= 0;
	 *
	 * @return le nombre d'{@code Aliment} transportés par ce
	 *         {@code JoueurMortel} dans son garde-manger.
	 * @pure
	 */
	public int getNbAliments() {
		int size=0;
		for(int i=0;i<this.gardeManger.size();i++){
			if(null != this.gardeManger.get(i))
				size ++;
		}
		return size;
	}

	/**
	 * Renvoie {@code true} si ce {@code JoueurMortel} possède au moins un
	 * {@code Aliment equals} à l'aliment spécifié dans son garde-manger.
	 *
	 * @ensures \result ==> !gardeMangerEstVide();
	 * @ensure gardeMangerEstVide() ==> !\result;
	 *
	 * @param unAliment
	 *            l'{@code Aliment} dont on cherche à savoir si ce
	 *            {@code JoueurMortel} en possède un exemplaire.
	 * @return {@code true} si ce {@code Joueur} possède l'{@code Aliment}
	 *         spécifié dans son garde-manger; {@code false} sinon.
	 * @pure
	 */
	public boolean contient(Aliment unAliment) {
		if(this.gardeMangerEstVide())
			return false;
		return this.gardeManger.contains(unAliment);
	}

	/**
	 * Renvoie un {@code Aliment} présent dans le garde-manger de ce
	 * {@code JoueurMortel}. Si le garde-manger de ce {@code JoueurMortel} n'est
	 * pas vide, renvoie l'{@code Aliment} transporté qui sera consommé par la
	 * prochaine action {@code manger} si aucun {@code Aliment} n'est ramassé
	 * par ce {@code JoueurMortel} dans l'interval.
	 *
	 * @requires !gardeMangerEstVide();
	 * @ensures \result != null;
	 * @ensures contient(\result);
	 *
	 * @return l'{@code Aliment} du garde-manger de ce {@code JoueurMortel} qui
	 *         sera consommé par la prochaine action {@code manger}.
	 * @pure
	 */
	public Aliment getAliment() {
		return this.gardeManger.get(this.gardeManger.size()-1);
	}

	// Actions:
	/**
	 * Déplace ce {@code JoueurMortel} dans la direction spécifié. La pièce
	 * courante doit posséder une sortie dans la direction spécifiée. Remplace
	 * la pièce courante de ce {@code JoueurMortel} par la sortie de cette pièce
	 * située dans la direction spécifiée. Le déplacement fait diminuer le
	 * capital vie de ce {@code JoueurMortel} d'un nombre de points de vie égal
	 * à 10 + le nombre d'{@code Aliment} transportés par ce
	 * {@code JoueurMortel} dans son garde-manger.
	 *
	 * @requires estVivant();
	 * @requires direction != null;
	 * @requires getPieceCourante().pieceSuivante(direction) != null;
	 * @ensures getPieceCourante() ==
	 *          \old(getPieceCourante().pieceSuivante(direction));
	 * @ensures getCapitalVie() == \old(getCapitalVie()) - (10 +
	 *          getNbAliments());
	 *
	 * @param direction
	 *            la direction dans laquelle ce {@code JoueurMortel} doit se
	 *            déplacer.
	 */
	public void deplacer(Direction direction) {
		this.PieceCourante=this.PieceCourante.pieceSuivante(direction);
		this.CapitalVie=this.CapitalVie - (10 + this.getNbAliments());
	}

	/**
	 * Retire l'aliment spécifié de la pièce courante de ce {@code JoueurMortel}
	 * et l'ajoute au garde-manger de ce {@code JoueurMortel}. Diminue le
	 * capital vie de ce {@code JoueurMortel} de 2 points de vie.
	 *
	 * @requires estVivant();
	 * @requires getPieceCourante().contient(unAliment);
	 * @ensures getPieceCourante().getNbAliments() ==
	 *          (\old(getPieceCourante().getNbAliments()) - 1);
	 * @ensures getNbAliments() == (\old(getNbAliments()) + 1);
	 * @ensures contient(unAliment);
	 * @ensures getCapitalVie() == (\old(getCapitalVie()) - 2);
	 *
	 * @param unAliment
	 *            l'{@code Aliment} à ramasser par ce {@code JoueurMortel}.
	 */
	public void ramasser(Aliment unAliment) {
		this.gardeManger.add(unAliment);
		this.PieceCourante.retirer(unAliment);
		this.CapitalVie=this.CapitalVie - 2;
	}

	/**
	 * Ajoute la valeur nutritive d'un {@code Aliment} du garde-manger au
	 * capital vie de ce {@code JoueurMortel}. Retire du garde-manger de ce
	 * {@code JoueurMortel} un exemplaire de l'{@code Aliment getAliment()} et
	 * ajoute la valeur nutritive de cet {@code Aliment} au capital vie de ce
	 * {@code JoueurMortel} dans la limite du
	 * {@code JoueurMortel.MAX_CAPITAL_VIE}.
	 *
	 * @requires estVivant();
	 * @requires !gardeMangerEstVide();
	 * @ensures getCapitalVie() == Math.min(\old(getCapitalVie() +
	 *          getAliment().getValeurNutritive())),
	 *          JoueurMortel.MAX_CAPITAL_VIE);
	 * @ensures getNbAliments() == \old(getNbAliments()) - 1;
	 */
	public void manger() {
		Aliment tmp=this.getAliment();
		if((this.CapitalVie + tmp.getValeurNutritive()) < MAX_CAPITAL_VIE)
			this.CapitalVie= this.CapitalVie + tmp.getValeurNutritive();
		else
			this.CapitalVie=MAX_CAPITAL_VIE;				
		this.gardeManger.remove(this.getAliment());
	}
}
