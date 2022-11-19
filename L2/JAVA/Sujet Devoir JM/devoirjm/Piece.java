package devoirjm;

import java.util.EnumMap;

/**
 * Une piece dans un jeu d'aventure.
 * <p>
 * Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 * texte.
 * </p>
 * <p>
 * Une "Piece" represente un des lieux dans lesquels se déroule l'action du jeu.
 * Elle est reliée à au plus quatre autres "Piece" par des sorties. Les sorties
 * sont étiquettées "nord", "est", "sud", "ouest". Pour chaque direction, la
 * "Piece" possède une référence sur la piece voisine ou null s'il n'y a pas de
 * sortie dans cette direction.
 * </p>
 * <p>
 *
 * Une piece peut aussi contenir des objets représentés par des instances de la
 * classe Aliment. Durant le déroulement du jeu des Aliments peuvent etre ajoutés
 * ou supprimés de la piece.
 * </p>
 *
 * @invariant getNbAliments() >= 0;
 * @invariant !contient(null);
 * @invariant (\forall Aliment obj; contient(obj); nbOccurences(obj) <= getNbAliments());
 *
 * @author Michael Kolling
 * @author Marc Champesme (pour la traduction francaise)
 * @version Septembre 2022
 * @since August 2000
 */
public class Piece {
	private String description;

	// mémorise les sorties de cette piece.
	private EnumMap<Direction, Piece> sorties;

	private Aliment[] mesObjets;

	private int nbObjets;

	/**
	 * Initialise une piece décrite par la chaine de caractères spécifiée.
	 * Initialement, cette piece ne possède aucune sortie. La description
	 * fournie est une courte phrase comme "la bibliothèque" ou
	 * "la salle de TP". La piece initialisée ne contient aucun Aliment.
	 *
	 * @requires description != null;
	 * @ensures descriptionCourte().equals(description);
	 * @ensures pieceSuivante(Direction.NORD) == null;
	 * @ensures pieceSuivante(Direction.SUD) == null;
	 * @ensures pieceSuivante(Direction.EST) == null;
	 * @ensures pieceSuivante(Direction.OUEST) == null;
	 * @ensures getNbAliments() == 0;
	 *
	 * @param description
	 *            Description de la piece.
	 */
	public Piece(String description) {
		this.description = description;
		sorties = new EnumMap<Direction, Piece>(Direction.class);
		mesObjets = new Aliment[5];
	}

	/**
	 * Initialise une piece décrite par la chaine de caractères spécifiée et
	 * contenant les nbObjets premiers Aliments du tableau d'Aliments spécifiés.
	 *
	 * @requires description != null;
	 * @requires lesObjets != null;
	 * @requires nbObjets >= 0;
	 * @requires nbObjets <= lesObjets.length;
	 * @requires (\forall int i; i >= 0 && i < nbObjets; lesObjets[i] != null);
	 * @ensures descriptionCourte().equals(description);
	 * @ensures pieceSuivante(Direction.NORD) == null;
	 * @ensures pieceSuivante(Direction.SUD) == null;
	 * @ensures pieceSuivante(Direction.EST) == null;
	 * @ensures pieceSuivante(Direction.OUEST) == null;
	 * @ensures getNbAliments() == nbObjets;
	 * @ensures (\forall int i; i >= 0 && i < nbObjets;
	 * 				nbOccurences(lesObjets[i])
	 *				== (\num_of int j; j >= 0 && j < nbObjets;
	 *						lesObjets[j].equals(lesObjets[i])));
	 *
	 * @param description
	 *            Description de la piece
	 * @param lesObjets
	 *            Aliments a placer dans la piece.
	 * @param nbObjets
	 *            Nb d'Aliments à placer dans la Piece
	 */
	public Piece(String description, Aliment[] lesObjets, int nbObjets) {
		this.description = description;
		sorties = new EnumMap<Direction, Piece>(Direction.class);
		mesObjets = (Aliment[]) lesObjets.clone();
		this.nbObjets = nbObjets;
	}

	/**
	 * Renvoie le nombre d'{@code Aliment} présents dans cette piece.
	 *
	 * @ensures \result >= 0;
	 *
	 * @return Le nombre d'{@code Aliment} présents dans cette piece.
	 *
	 * @pure
	 */
	public int getNbAliments() {
		return nbObjets;
	}

	/**
	 * Renvoie unu nouvelle instance de tableau dont les éléments sont tous
	 * les {@code Aliment} présents dans cette piece.
	 *
	 * @ensures \fresh(\result);
	 * @ensures \result.length == getNbAliments();
	 * @ensures (\forall int i; i >= 0 && i < getNbAliments();
	 * 				contient(\result[i]));
	 * @ensures (\forall Aliment obj; contient(obj);
	 * 			(\num_of int i; i >= 0 && i < getNbAliments();
	 *					\result[i].equals(obj))
	 *			== nbOccurences(obj));
	 *
	 * @return Un tableau contenant les Aliments présents dans cette Piece.
	 *
	 * @pure
	 */
	public Aliment[] getTabAliments() {
		Aliment[] resultat = new Aliment[getNbAliments()];

		for (int i = 0; i < getNbAliments(); i++) {
			resultat[i] = mesObjets[i];
		}

		return resultat;
	}

	/**
	 * Ajoute l'Aliment spécifié aux Aliments présents dans cette piece.
	 *
	 * @requires al != null;
	 * @ensures contient(al);
	 * @ensures getNbAliments() == \old(getNbAliments()) + 1;
	 * @ensures nbOccurences(al) ==
	 *			\old(nbOccurences(al)) + 1;
	 *
	 * @param al
	 *            L'Aliment à ajouter.
	 */
	public void ajouter(Aliment al) {
		ajouterNbOccurences(al, 1);
	}

	/**
	 * Ajoute à cette piece le nombre d'occurences spécifié de l'Aliment spécifié.
	 *
	 * @requires al != null;
	 * @requires nbOccurences >= 1;
	 * @ensures contient(al);
	 * @ensures getNbAliments() == \old(getNbAliments()) + nbOccurences;
	 * @ensures nbOccurences(al) == \old(nbOccurences(al)) + nbOccurences;
	 *
	 * @param al
	 *            L'Aliment à ajouter
	 * @param nbOccurences
	 *            Le nombre d'occurences de l'Aliment à ajouter
	 */
	public void ajouterNbOccurences(Aliment al, int nbOccurences) {
		if (mesObjets.length <= (getNbAliments() + nbOccurences)) {
			Aliment[] copie;

			copie = new Aliment[getNbAliments() + nbOccurences + 5];
			for (int i = 0; i < getNbAliments(); i++) {
				copie[i] = mesObjets[i];
			}
			mesObjets = copie;
		}

		for (int i = getNbAliments(); i < getNbAliments() + nbOccurences; i++) {
			mesObjets[i] = al;
		}

		nbObjets += nbOccurences;
	}

	/**
	 * Renvoie true si l'{@code Aliment} spécifié est présent dans cette piece.
	 * Plus précisément, renvoie {@code true} si et seulement si cette piece
	 * contient au moins une instance {@code obj} de la classe {@code Aliment}
	 * telle que {@code obj.equals(al)}.
	 *
	 * @ensures (getNbAliments() == 0) ==> !\result;
	 * @ensures \result <==> (\exists Aliment obj; contient(obj);
	 *          obj.equals(al));
	 *
	 * @param al
	 *            L'{@code Aliment} cherché.
	 * @return {@code true} si l'{@code Aliment} spécifié est présent dans cette
	 *         piece ; {@code false} sinon.
	 *
	 * @pure
	 */
	public boolean contient(Aliment al) {
		boolean trouve = false;

		for (int i = 0; i < getNbAliments() && !trouve; i++) {
			trouve = mesObjets[i].equals(al);
		}

		return trouve;
	}

	/**
	 * Renvoie le nombre d'occurences de l'Aliment spécifié dans cette piece.
	 *
	 * @ensures \result == (\num_of Aliment obj; contient(obj); obj.equals(objet));
	 *
	 * @param al
	 *            L'Aliment cherché.
	 * @return Le nombre d'occurences de l'Aliment spécifié dans cette piece
	 *
	 * @pure
	 */
	public int nbOccurences(Aliment al) {
		int nbOccurences = 0;

		for (int i = 0; i < getNbAliments(); i++) {
			if (mesObjets[i].equals(al)) {
				nbOccurences++;
			}
		}

		return nbOccurences;
	}

	/**
	 * Retire de cette pièce une occurence de l'{@code Aliment} spécifié.
	 * Renvoie {@code true} si la liste des {@code Aliment} présents dans cette
	 * pièce a effectivement été modifiée, c'est-a-dire, si l'{@code Aliment}
	 * était présent dans cette pièce.
	 *
	 * @ensures \result <==> \old(contient(al));
	 * @ensures \result ==> (getNbAliments() == \old(getNbAliments()) - 1);
	 * @ensures !\result ==> (getNbAliments() == \old(getNbAliments()));
	 *
	 * @param al
	 *            L'{@code Aliment} à retirer de cette {@code Piece}.
	 * @return {@code true} si cette piece contenait cet {@code Aliment};
	 *         {@code false} sinon.
	 */
	public boolean retirer(Aliment al) {
		for (int i = 0; i < getNbAliments(); i++) {
			if (mesObjets[i].equals(al)) {
				mesObjets[i] = mesObjets[getNbAliments() - 1];
				nbObjets--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Retire de cette piece toutes les occurences de l'Aliment spécifié. Renvoie
	 * true si et seulement si la liste des Aliments présents dans cette piece a
	 * effectivement été modifiée, c'est-a-dire, si l'objet était présent dans cette piece.
	 *
	 * @requires contient(al);
	 * @ensures \result;
	 * @ensures !contient(al);
	 * @ensures getNbAliments() == \old(getNbAliments() - nbOccurences(al));
	 * @ensures nbOccurences(al) == 0;
	 * @also
	 * @requires !contient(al);
	 * @ensures !\result;
	 * @ensures !contient(al);
	 * @ensures getNbAliments() == \old(getNbAliments());
	 * @ensures (\forall Aliment obj; \old(contient(obj));
	 * 			nbOccurences(obj) == \old(nbOccurences(obj)));
	 *
	 * @param al
	 *            L'Aliment à retirer.
	 * @return true si cette piece contenait cet Aliment.
	 */
	public boolean retirerToutesOccurences(Aliment al) {
		boolean trouve = true;
		boolean modifie = false;

		do {
			trouve = retirer(al);
			modifie = modifie || trouve;
		} while (trouve);

		return modifie;
	}

	/**
	 * Définie les sorties de cette piece. A chaque direction correspond ou bien
	 * une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie
	 * dans cette direction.
	 *
	 * @ensures pieceSuivante(Direction.NORD) == nord;
	 * @ensures pieceSuivante(Direction.SUD) == sud;
	 * @ensures pieceSuivante(Direction.EST) == est;
	 * @ensures pieceSuivante(Direction.OUEST) == ouest;
	 *
	 * @param nord
	 *            La sortie nord
	 * @param est
	 *            La sortie est
	 * @param sud
	 *            La sortie sud
	 * @param ouest
	 *            La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put(Direction.NORD, nord);
		}
		if (est != null) {
			sorties.put(Direction.EST, est);
		}
		if (sud != null) {
			sorties.put(Direction.SUD, sud);
		}
		if (ouest != null) {
			sorties.put(Direction.OUEST, ouest);
		}
	}

	/**
	 * Renvoie la description de cette piece (i.e. la description spécifiée lors
	 * de la création de cette instance).
	 *
	 * @ensures \result != null;
	 *
	 * @return Description de cette piece
	 *
	 * @pure
	 */
	public String descriptionCourte() {
		return description;
	}

	/**
	 * Renvoie une description de cette piece mentionant ses sorties et
	 * directement formatée pour affichage, de la forme:
	 *
	 * <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest
	 * </pre>
	 *
	 * Cette description utilise la chaine de caractères renvoyée par la méthode
	 * descriptionSorties pour décrire les sorties de cette piece.
	 *
	 * @ensures \result != null;
	 * @ensures \result.indexOf(descriptionCourte()) >= 0;
	 * @ensures \result.indexOf(descriptionSorties()) >= 0;
	 *
	 * @return Description affichable de cette piece
	 *
	 * @pure
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}

	/**
	 * Renvoie une description des sorties de cette piece, de la forme:
	 *
	 * <pre>
	 *  Sorties: nord ouest
	 * </pre>
	 *
	 * Cette description est utilisée dans la description longue d'une piece.
	 *
	 * @ensures \result != null;
	 * @ensures (pieceSuivante(Direction.NORD) != null) ==> \result.indexOf("NORD") >= 0;
	 * @ensures (pieceSuivante(Direction.SUD) != null) ==> \result.indexOf("SUD") >= 0;
	 * @ensures (pieceSuivante(Direction..EST) != null) ==> \result.indexOf("EST") >= 0;
	 * @ensures (pieceSuivante(Direction.OUEST) != null) ==> \result.indexOf("OUEST") >= 0;
	 *
	 * @return Une description des sorties de cette pièce.
	 *
	 * @pure
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		for (Direction d : Direction.values()) {
			if (sorties.get(d) != null) {
				resulString += " " + d;
			}
		}
		return resulString;
	}

	/**
	 * Renvoie la piece atteinte lorsque l'on se déplace à partir de cette piece
	 * dans la direction spécifiée. Si cette piece ne possède aucune sortie dans
	 * cette direction, renvoie {@code null}.
	 *
	 * @requires direction != null;
	 *
	 * @param direction
	 *            La direction dans laquelle on souhaite se déplacer.
	 * @return La piece atteinte lorsque l'on se déplace dans la direction
	 *         spécifiée ou {@code null}.
	 * @pure
	 */
	public Piece pieceSuivante(Direction d) {
		return sorties.get(d);
	}
}
