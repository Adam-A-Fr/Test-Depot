package devoirjm.test;

import java.util.stream.Stream;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.jupiter.params.provider.Arguments;

import devoirjm.JoueurMortel;
import devoirjm.Piece;
import devoirjm.Aliment;
import devoirjm.Direction;

public class JoueurDataProvider {
    public static int LG_STREAM = 200;  // Nombre d'éléments par Stream
                                        // correspond au nombre de test par
                                        // méthode de test.
    public static int PIECE_NB = 30;
    public static List<String> alimentNames = Arrays.asList("carotte", "pomme de terre",
                                            "fromage", "jus de fruit", "haricots",
                                            "oranges", "pommes", "poires", "burger",
                                            "couscous", "adobo", "pho", "cassoulet",
                                            "confit de canard", "chou", "Pomme de terre",
                                            "frites", "poireau", "salade", "tomate",
                                            "burger", "sandwich", "entrecôte", "steak",
                                            "poisson", "pain", "brioche", "calamar",
                                            "biscotte", "tarte", "quiche", "abricot");
    public static List<String> pieceNames = Arrays.asList("cuisine", "salle TD", "Amphi A",
                                            "Amphi B", "Amphi Becquerel", "Amphi Ampère",
                                            "F 202", "Salle TP", "F 204", "C 315",
                                            "C 304", "C 302", "Amphi C", "Amphi D",
                                            "D 312", "B 303", "C 305", "C 307",
                                            "Taverne", "BDE");

    public static List<String> joueurNames = Arrays.asList("Marcel", "Adam", "Sonia",
                                            "Idir", "Mohamed", "Marc", "Ali", "Ziad",
                                            "Lyes", "Ayman", "Mounir", "Pierre", "Chanez",
                                            "Lamia", "Yanis", "Faycal", "Boris", "Imam",
                                            "Naila", "Zahra", "Rosa", "Lisa", "Sheraz",
                                            "Nima", "Aliou", "Issa", "Mamadou", "Ismael");

    public static List<Piece> pieceInstances = new ArrayList<Piece>(100);

    public static Stream<JoueurMortel> joueurProvider() {
        //System.out.println("joueurProvider");
        return Stream.generate(() -> newJoueurMortel()).limit(LG_STREAM);
    }

    /**
     * Arguments pour le 1er constructeur de JoueurMortel.
     */
    public static Stream<Arguments> nameAndPieceProvider() {
        //System.out.println("nameAndPieceProvider");
        return Stream.generate(() -> newPiece())
                .map(p -> Arguments.of(getRandomElt(joueurNames), p)).limit(LG_STREAM);
    }

    /**
     * Arguments pour le 2ème constructeur de JoueurMortel.
     */
    public static Stream<Arguments> nameAndPieceAndTabProvider() {
        //System.out.println("nameAndPieceAndTabProvider");
        return Stream.generate(() -> newTabAliment())
            .map(tab -> Arguments.of(getRandomElt(joueurNames), newPiece(), tab, tab.length))
            .limit(LG_STREAM);
    }

    /**
     * Data provider for method contient.
     */
    public static Stream<Arguments> joueurAndAlimentProvider() {
        //System.out.println("joueurAndAlimentProvider / contient");
        return Stream.generate(() -> newJoueurMortel())
            .map(j -> Arguments.of(j,
                    randBool() ? getRandomElt(j.getTabAliments())
                                : newAliment()))
            .limit(LG_STREAM);
    }

    /**
     * Data provider for method ramasser.
     */
    public static Stream<Arguments> joueurAndPAlimentProvider() {
        //System.out.println("joueurAndPAlimentProvider / ramasser");
        for (Piece p : pieceInstances) {
            p.ajouterNbOccurences(newAliment(), 10);
        }
        return Stream.generate(() -> newJoueurMortel())
            .filter(j -> j.getPieceCourante().getNbAliments() > 0)
            .map(j -> Arguments.of(j,
                    getRandomElt(j.getPieceCourante().getTabAliments())))
            .limit(LG_STREAM);
    }

    /**
     * Data provider for method deplacer.
     */
    public static Stream<Arguments> joueurAndDirectionProvider() {
        //System.out.println("joueurAndDirectionProvider / deplacer");
        return Stream.generate(() -> Arguments.of(newJoueurMortel(), getRandomElt(Direction.values())))
                .limit(LG_STREAM);

    }
    // FIN SECTION 2

    // ######################################
    // NE PAS MODIFIER LES LIGNES SUIVANTES !
    // ######################################
    private static Random randGen = new Random();


    public static Aliment newAliment() {
        return new Aliment(getRandomElt(alimentNames), randInt(1, 50));
    }

    public static Aliment[] newTabAliment() {
        int length = randInt(5, 10);
        Aliment[] tab = new Aliment[length];
        for (int i = 0; i < length; i++) {
            tab[i] = newAliment();
        }
        return tab;
    }

    public static Piece newIsolatedPiece() {
        Aliment[] tabAl = newTabAliment();
        return new Piece(getRandomElt(pieceNames), tabAl, tabAl.length);
    }

    public static void initPieceList() {
        if (!pieceInstances.isEmpty()) {
            return;
        }
        for (int i = 0; i < PIECE_NB; i++) {
            pieceInstances.add(newIsolatedPiece());
        }
        for (Piece p :pieceInstances) {
            List<Piece> lp = new ArrayList<Piece>(random4SubList(pieceInstances));
            lp.remove(p);
            Piece piece1 = lp.get(0);
            Piece piece2 = lp.get(1);
            Piece piece3 = lp.get(2);
            Piece piece4 = lp.get(3);
            // while (piece1 == p || piece1 == piece2 || piece1 == piece3 || piece1 == piece4) {
            //     piece1 = getRandomElt(pieceInstances);
            // }
            // while (piece2 == p || piece2 == piece1 || piece2 == piece3 || piece2 == piece4) {
            //     piece2 = getRandomElt(pieceInstances);
            // }
            // while (piece3 == p || piece3 == piece1 || piece3 == piece2 || piece3 == piece4) {
            //     piece3 = getRandomElt(pieceInstances);
            // }
            // while (piece4 == p || piece4 == piece1 || piece4 == piece2 || piece4 == piece3) {
            //     piece2 = getRandomElt(pieceInstances);
            // }
            p.setSorties(piece1, piece2, piece3, piece4);
        }
        //System.out.println("PieceInstances initialized");
    }

    public static Piece newPiece() {
        initPieceList();
        return getRandomElt(pieceInstances);
    }

    public static JoueurMortel newJoueurMortel() {
        Aliment[] tabAl = newTabAliment();
        JoueurMortel j = new JoueurMortel(getRandomElt(joueurNames), newPiece(), tabAl, tabAl.length);
        j.deplacer(Direction.SUD);
        //System.out.println("New Joueur:" + j.getNom());
        //System.out.print(".");
        return j;
    }


    // General purpose utilities:


    /**
	 * Renvoie une sous-liste de la liste spécifiée de longueur aléatoire. La
     * liste retournée est une vue de la liste spécifiée, toute modification de
     * cette liste provoque donc une modification de la liste spécifiée.
	 *
	 * @param <T> le type des éléments de la liste
	 * @param l   la liste dont la sous-liste est extraite.
	 * @return une sous-liste de la liste spécifiée
	 *
	 * @requires l != null;
	 * @ensures \result != null;
	 * @ensures (\exists int lower, upper; lower >=0 && lower <= upper && upper <
	 *          l.size(); \result.equals(l.subList(lower, upper)));
	 * @ensures l.containsAll(\result);
	 * @ensures \result.size() <= l.size();
	 * @ensures l.isEmpty() => \result.isEmpty();
	 *
	 */
	public static <T> List<T> randomSubList(List<T> l) {
		if (l.isEmpty()) {
			return Collections.emptyList();
		}
		int upper = randGen.nextInt(l.size());
		int lower = randGen.nextInt(upper + 1);
		return l.subList(lower, upper);
	}
    public static <T> List<T> random4SubList(List<T> l) {
		int lower = randGen.nextInt(l.size() - 6);
		return l.subList(lower, l.size());
	}

    public static <T> Collection<T> removeNullFrom(Collection<T> c) {
        if (!c.contains(null)) {
            return c;
        }
        c = new LinkedList<T>(c);
        List<Object> nullList = new LinkedList<Object>();
        nullList.add(null);
        c.removeAll(nullList);
        return c;
    }
    /**
     * Renvoie un élément tiré aléatoirement parmi les éléments de la collection
     * spécifiée.
     *
     * @requires c != null;
     * @requires !c.isEmpty();
     * @ensures c.contains(\result);
     *
     * @param <T> Type des éléments de la collection spécifiée
     * @param c   collection dans laquelle est choisi l'élément retourné
     *
     * @return un élément tiré aléatoirement parmi les éléments de la collection
     *         spécifiée
     */
    public static <T> T getRandomElt(Collection<T> c) {
        int index = randInt(c.size());
        if (c instanceof List<?>) {
            return ((List<T>) c).get(index);
        }
        int i = 0;
        for (T elt : c) {
            if (i == index) {
                return elt;
            }
            i++;
        }
        throw new NoSuchElementException();
    }

    public static <T> T getRandomElt(T[] tab) {
        return getRandomElt(Arrays.asList(tab));
    }

    /**
	 * Renvoie un int obtenue par un générateur pseudo-aléatoire.
	 *
	 * @param max la valeur maximale du nombre aléatoire attendu
	 *
	 * @return un entier >= 0 et < max
	 *
	 * @throws IllegalArgumentException si max <= 0
	 *
	 * @requires max > 0;
	 * @ensures \result >= 0;
	 * @ensures \result < max;
	 */
	public static int randInt(int max) {
		return randGen.nextInt(max);
	}

	/**
	 * Renvoie un int obtenue par un générateur pseudo-aléatoire.
	 *
	 * @param min la valeur minimale du nombre aléatoire attendu
	 * @param max la valeur maximale du nombre aléatoire attendu
	 *
	 * @return un entier >= min et < max
	 *
	 * @throws IllegalArgumentException si max <= min
	 *
	 * @requires max > min;
	 * @ensures \result >= min;
	 * @ensures \result < max;
	 */
	public static int randInt(int min, int max) {
		return randInt(max - min) + min;
	}

    /**
     * Renvoie une valeur booléenne obtenue par un générateur pseudo-aléatoire.
     *
     * @return une valeur booléenne aléatoire
     */
    public static boolean randBool() {
        return randGen.nextBoolean();
    }

    /**
     * Renvoie une valeur booléenne obtenue par un générateur pseudo-aléatoire.
     * La valeur renvoyée a une probabilité d'être true similaire à la probabilité
     * que randInt(max) renvoie la valeur 0.
     *
     * @return une valeur booléenne aléatoire
     */
    public static boolean randBool(int max) {
        return randGen.nextInt(max) == 0;
    }


}
