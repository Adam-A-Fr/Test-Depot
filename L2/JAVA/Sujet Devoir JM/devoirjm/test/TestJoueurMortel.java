package devoirjm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import devoirjm.JoueurMortel;
import devoirjm.Piece;
import devoirjm.Aliment;
import devoirjm.Direction;

/**
 * Test class for JoueurMortel.
 *
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
 * A sa création un {@code JoueurMortel} possède un capital de 100 points de vie
 * (i.e. {@code Joueur.MAX_CAPITAL_VIE == 100}), qui va diminuer ou
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
 * du capital vie maximal {@code Joueur.MAX_CAPITAL_VIE}.</dd>
 * </dl>
 * Lorsque le capital vie du joueur atteint (ou passe en dessous de) la valeur
 * de 0 points de vie, le {@code JoueurMortel} meurt définitivement et ne peut
 * donc plus faire aucune action, ni même s'alimenter.
 * </p>
 */
public class TestJoueurMortel extends JoueurDataProvider {
        private String name;
        private int capitalVie;
        private Piece maPiece;
        private LinkedList<Aliment> gardeManger;

        private void saveState(JoueurMortel self) {
                // Put here the code to save the state of self:
                name = self.getNom();
                capitalVie = self.getCapitalVie();
                maPiece = self.getPieceCourante();
                gardeManger = new LinkedList<Aliment>(Arrays.asList(self.getTabAliments()));
        }

        private void assertPurity(JoueurMortel self) {
                // Put here the code to check purity for self:
                assertEquals(name, self.getNom());
                assertTrue(capitalVie == self.getCapitalVie());
                assertEquals(maPiece, self.getPieceCourante());
                LinkedList<Aliment> newGardeManger = new LinkedList<Aliment>(Arrays.asList(self.getTabAliments()));
                assertTrue(gardeManger.size() == newGardeManger.size());
                while(!gardeManger.isEmpty()) {
                    assertTrue(newGardeManger.remove(gardeManger.removeFirst()));
                }
                assertTrue(newGardeManger.isEmpty());
        }

        public void assertInvariant(JoueurMortel self) {
                // Put here the code to check the invariant:
                // @invariant getCapitalVie() <= JoueurMortel.MAX_CAPITAL_VIE;
                assertTrue(self.getCapitalVie() <= JoueurMortel.MAX_CAPITAL_VIE);
                // @invariant estVivant() <==> (getCapitalVie() > 0);
                assertEquals(self.estVivant(), (self.getCapitalVie() > 0));
                // @invariant !contient(null);
                assertFalse(self.contient(null));
                // @invariant getNom() != null;
                assertNotNull(self.getNom());
                // @invariant getPieceCourante() != null;
                assertNotNull(self.getPieceCourante());
        }


        /**
         * Test method for constructor JoueurMortel
         *
         * Initialise un nouveau {@code JoueurMortel} vivant situé dans la pièce
         * spécifiée et portant le nom spécifié. Le garde-manger de ce nouveau
         * {@code JoueurMortel} est vide.
         */
        @ParameterizedTest
        @MethodSource("nameAndPieceProvider")
        public void testJoueurMortel(String nom, Piece pieceCourante) {
                // Pré-conditions:
                // @requires nom != null;
                assumeTrue(nom != null);
                // @requires pieceCourante != null;
                assumeTrue(pieceCourante != null);

                // Oldies:

                // Exécution:
                JoueurMortel result = new JoueurMortel(nom, pieceCourante);

                // Post-conditions:
                // @ensures estVivant();
                assertTrue(result.estVivant());
                // @ensures getNom().equals(nom);
                assertEquals(nom, result.getNom());
                // @ensures getPieceCourante().equals(pieceCourante);
                assertEquals(pieceCourante, result.getPieceCourante());
                // @ensures getCapitalVie() == JoueurMortel.MAX_CAPITAL_VIE;
                assertTrue(result.getCapitalVie() == JoueurMortel.MAX_CAPITAL_VIE);
                // @ensures gardeMangerEstVide();
                assertTrue(result.gardeMangerEstVide());

                // Invariant:
                assertInvariant(result);
        }

        /**
         * Test method for constructor JoueurMortel
         *
         * Initialise un nouveau {@code JoueurMortel} vivant portant le nom
         * spécifié, situé dans la pièce spécifiée et dont le garde-manger contient
         * les {@code Aliment} spécifiés. Le garde-manger de ce nouveau
         * {@code JoueurMortel} contient les {@code nbAliments} premiers éléments du
         * tableau spécifié.
         */
        @ParameterizedTest
        @MethodSource("nameAndPieceAndTabProvider")
        public void testJoueurMortel(String nom, Piece pieceCourante, Aliment[] tabAliments, int nbAliments) {
                // Pré-conditions:
                // @requires nom != null;
                assumeTrue(nom != null);
                // @requires pieceCourante != null;
                assumeTrue(pieceCourante != null);
                // @requires tabAliments != null;
                assumeTrue(tabAliments != null);
                // @requires (nbAliments >= 0) && (nbAliments <= tabAliments.length);
                assumeTrue(nbAliments >= 0);
                assumeTrue(nbAliments <= tabAliments.length);
                // @requires (\forall int i; i >= 0 && i < nbAliments; tabAliments[i] != null);
                for (int i = 0; i < nbAliments; i = i + 1) {
                  assumeTrue(tabAliments[i] != null);
                }

                // Oldies:

                // Exécution:
                JoueurMortel result = new JoueurMortel(nom, pieceCourante, tabAliments, nbAliments);

                // Post-conditions:
                // @ensures estVivant();
                assertTrue(result.estVivant());
                // @ensures getNom().equals(nom);
                assertEquals(nom, result.getNom());
                // @ensures getPieceCourante().equals(pieceCourante);
                assertEquals(pieceCourante, result.getPieceCourante());
                // @ensures getCapitalVie() == JoueurMortel.MAX_CAPITAL_VIE;
                assertTrue(result.getCapitalVie() == JoueurMortel.MAX_CAPITAL_VIE);
                // @ensures getNbAliments() == nbAliments;
                assertTrue(nbAliments == result.getNbAliments());
                // @ensures (\forall int i; i >= 0 && i < nbAliments; contient(tabAliments[i]));
                for (int i = 0; i < nbAliments; i = i + 1) {
                  result.contient(tabAliments[i]);
                }

                // Invariant:
                assertInvariant(result);
        }

        /**
         * Test method for method getNom
         *
         * Renvoie le nom de ce {@code JoueurMortel}.
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testgetNom(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                String result = self.getNom();

                // Post-conditions:

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method getCapitalVie
         *
         * Renvoie le nombre de points de vie de ce {@code JoueurMortel}.
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testgetCapitalVie(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                int result = self.getCapitalVie();

                // Post-conditions:

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method estVivant
         *
         * Renvoie {@code true} si ce {@code JoueurMortel} a un capital vie
         * strictement supérieur à 0.
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testestVivant(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                boolean result = self.estVivant();

                // Post-conditions:

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method getPieceCourante
         *
         * Renvoie la {@code Piece} dans la quelle se trouve ce
         * {@code Joueur.}
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testgetPieceCourante(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                Piece result = self.getPieceCourante();

                // Post-conditions:

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method getTabAliments
         *
         * Renvoie une nouvelle instance de la classe {@code Aliment[]} dont les
         * éléments sont tous les {@code Aliment} présents dans le garde-manger de
         * ce {@code JoueurMortel}.
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testgetTabAliments(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                Aliment[] result = self.getTabAliments();

                // Post-conditions:
                // @ensures \result != null;
                assertNotNull(result);
                // @ensures \result.length == getNbAliments();
                assertTrue(self.getNbAliments() == result.length);
                // @ensures (\forall int i; i >= 0 && i < getNbAliments(); contient(\result[i]));
                for (int i = 0; i < self.getNbAliments(); i = i + 1) {
                  assertTrue(self.contient(result[i]));
                }
                // @ensures (\forall Aliment al; contient(al);
                //            (\exists int i; i >= 0 && i < getNbAliments(); \result[i].equals(al)));
                for (Aliment al : gardeManger) {
                  if (self.contient(al)) {
                    boolean trouve = false;
                    int i = 0;
                    while (i < self.getNbAliments() && !trouve) {
                      if (result[i].equals(al)) {
                        trouve = true;
                      }
                      i = i + 1;
                    }
                    assertTrue(trouve);
                  }
                }
                // Pour vérifier l'indépendance du tableau renvoyé
                // par rapport au stockage interne des aliments par l'instances
                // de JoueurMortel:
                for (int i = 0; i < result.length; i = i + 1) {
                    result[i] = null;
                }

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method gardeMangerEstVide
         *
         * Renvoie {@code true} si ce {@code JoueurMortel} ne possède aucun
         * {@code Aliment}.
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testgardeMangerEstVide(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                boolean result = self.gardeMangerEstVide();

                // Post-conditions:
                // @ensures \result == (getNbAliments() == 0);
                assertTrue(result == (self.getNbAliments() == 0));

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method getNbAliments
         *
         * Renvoie le nombre d'{@code Aliment} présents dans le garde-manger de ce
         * {@code JoueurMortel}.
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testgetNbAliments(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                int result = self.getNbAliments();

                // Post-conditions:
                // @ensures \result >= 0;
                assertTrue(result >= 0);

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method contient
         *
         * Renvoie {@code true} si ce {@code JoueurMortel} possède au moins un
         * {@code Aliment equals} à l'aliment spécifié dans son garde-manger.
         */
        @ParameterizedTest
        @MethodSource("joueurAndAlimentProvider")
        public void testcontient(JoueurMortel self, Aliment unAliment) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                boolean result = self.contient(unAliment);

                // Post-conditions:
                // @ensures \result ==> !gardeMangerEstVide();
                if (result) {
                  assertFalse(self.gardeMangerEstVide());
                }

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method getAliment
         *
         * Renvoie un {@code Aliment} présent dans le garde-manger de ce
         * {@code JoueurMortel}. Si le garde-manger de ce {@code JoueurMortel} n'est
         * pas vide, renvoie l'{@code Aliment} transporté qui sera consommé par la
         * prochaine action {@code manger} si aucun {@code Aliment} n'est ramassé
         * par ce {@code JoueurMortel} dans l'interval.
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testgetAliment(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:
                // @requires !gardeMangerEstVide();
                assumeTrue(!self.gardeMangerEstVide());


                // Save state for purity check:
                saveState(self);

                // Oldies:

                // Exécution:
                Aliment result = self.getAliment();

                // Post-conditions:
                // @ensures \result != null;
                assertNotNull(result);
                // @ensures contient(\result);
                assertTrue(self.contient(result));

                // Assert purity:
                assertPurity(self);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method deplacer
         *
         * Déplace ce {@code JoueurMortel} dans la direction spécifié. La pièce
         * courante doit posséder une sortie dans la direction spécifiée. Remplace
         * la pièce courante de ce {@code JoueurMortel} par la sortie de cette pièce
         * située dans la direction spécifiée. Le déplacement fait diminuer le
         * capital vie de ce {@code JoueurMortel} d'un nombre de points de vie égal
         * à 10 + le nombre d'{@code Aliment} transportés par ce
         * {@code JoueurMortel} dans son garde-manger.
         */
        @ParameterizedTest
        @MethodSource("joueurAndDirectionProvider")
        public void testdeplacer(JoueurMortel self, Direction direction) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:
                // @requires estVivant();
                assumeTrue(self.estVivant());
                // @requires direction != null;
                assumeTrue(direction != null);
                // @requires getPieceCourante().pieceSuivante(direction) != null;
                assumeTrue(self.getPieceCourante().pieceSuivante(direction) != null);


                // Oldies:
                // old in:@ensures getPieceCourante() == \old(getPieceCourante().pieceSuivante(direction));
                Piece oldSortie = self.getPieceCourante().pieceSuivante(direction);
                // old in:@ensures getCapitalVie() == \old(getCapitalVie() - (10 + getNbAliments());
                int oldValue = self.getCapitalVie() - (10 + self.getNbAliments());

                // Exécution:
                self.deplacer(direction);

                // Post-conditions:
                // @ensures getPieceCourante() == \old(getPieceCourante().pieceSuivante(direction));
                assertTrue(self.getPieceCourante() == oldSortie);
                // @ensures getCapitalVie() == \old(getCapitalVie()) - (10 + getNbAliments());
                assertTrue(self.getCapitalVie() == oldValue);

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method ramasser
         *
         * Retire l'aliment spécifié de la pièce courante de ce {@code JoueurMortel}
         * et l'ajoute au garde-manger de ce {@code JoueurMortel}. Diminue le
         * capital vie de ce {@code JoueurMortel} de 2 points de vie.
         */
        @ParameterizedTest
        @MethodSource("joueurAndPAlimentProvider")
        public void testramasser(JoueurMortel self, Aliment unAliment) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:
                // @requires estVivant();
                assumeTrue(self.estVivant());
                // @requires getPieceCourante().contient(unAliment);
                assumeTrue(self.getPieceCourante().contient(unAliment));


                // Oldies:
                // old in:@ensures getPieceCourante().getNbAliments() == (\old(getPieceCourante().getNbAliments()) - 1);
                int oldPieceNbAliments = self.getPieceCourante().getNbAliments();
                // old in:@ensures getNbAliments() == (\old(getNbAliments()) + 1);
                int oldNbAliments = self.getNbAliments();
                // old in:@ensures getCapitalVie() == (\old(getCapitalVie()) - 2);
                int oldCapitalVie = self.getCapitalVie();

                // Exécution:
                self.ramasser(unAliment);

                // Post-conditions:
                // @ensures getPieceCourante().getNbAliments() == (\old(getPieceCourante().getNbAliments()) - 1);
                assertTrue(self.getPieceCourante().getNbAliments() == (oldPieceNbAliments - 1));
                // @ensures getNbAliments() == (\old(getNbAliments()) + 1);
                assertTrue(self.getNbAliments() == (oldNbAliments +1));
                // @ensures contient(unAliment);
                assertTrue(self.contient(unAliment));
                // @ensures getCapitalVie() == (\old(getCapitalVie()) - 2);
                assertTrue(self.getCapitalVie() == (oldCapitalVie - 2));

                // Invariant:
                assertInvariant(self);
        }

        /**
         * Test method for method manger
         *
         * Ajoute la valeur nutritive d'un {@code Aliment} du garde-manger au
         * capital vie de ce {@code JoueurMortel}. Retire du garde-manger de ce
         * {@code JoueurMortel} un exemplaire de l'{@code Aliment getAliment()} et
         * ajoute la valeur nutritive de cet {@code Aliment} au capital vie de ce
         * {@code JoueurMortel} dans la limite du
         * {@code JoueurMortel.MAX_CAPITAL_VIE}.
         */
        @ParameterizedTest
        @MethodSource("joueurProvider")
        public void testmanger(JoueurMortel self) {
                assumeTrue(self != null);

                // Invariant:
                assertInvariant(self);

                // Pré-conditions:
                // @requires estVivant();
                assumeTrue(self.estVivant());
                // @requires !gardeMangerEstVide();
                assumeFalse(self.gardeMangerEstVide());

                // Oldies:
                // old in:@ensures getCapitalVie()
                //            == Math.min(\old(getCapitalVie() + getAliment().getValeurNutritive())),
                //                        JoueurMortel.MAX_CAPITAL_VIE);
                int oldCapitalVie = self.getCapitalVie();
                int oldAlimentValue = self.getAliment().getValeurNutritive();
                // old in:@ensures getNbAliments() == \old(getNbAliments()) - 1;
                int oldNbAliments = self.getNbAliments();

                // Exécution:
                self.manger();

                // Post-conditions:
                // @ensures getCapitalVie()
                //            == Math.min(\old(getCapitalVie() + getAliment().getValeurNutritive())),
                //                        JoueurMortel.MAX_CAPITAL_VIE);
                assertTrue(self.getCapitalVie() == Math.min(oldCapitalVie + oldAlimentValue, JoueurMortel.MAX_CAPITAL_VIE));
                // @ensures getNbAliments() == \old(getNbAliments()) - 1;
                assertTrue(self.getNbAliments() == (oldNbAliments - 1));

                // Invariant:
                assertInvariant(self);
        }
} // End of the test class for JoueurMortel
