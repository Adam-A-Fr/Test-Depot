package devoirjm;
/**
 * Un Aliment est entièrement caractérisé par son nom (une chaîne de caractères
 * non null de longueur strictement supérieure à 0) et sa valeur nutritive
 * (un entier strictement positif).
 * La classe Aliment doit être implémentée de façon à ce que, une fois créées,
 * les instances de cette classe ne puissent pas être modifiées (i.e. classe
 * non modifiable ou immutable) : les caractéristiques nom et valeur nutritive
 * sont fixées lors de la création de l’instance et ne peuvent pas être modifiées
 * par la suite.
 * Deux instances de la classe Aliment seront considérées comme equals si et s
 * eulement si elles possèdent le même nom et la même valeur nutritive.
 *
 * @author <ABADA 12000059>
 */
public class Aliment {
    private String nom;
    private int val_nutr;

    /**
     * 
     * 
     * 
     * 
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * 
     * 
     * 
     * 
     */
    public int getValeurNutritive() {
        return this.val_nutr;
    }

    /**
     * Constructeur qui initialise un aliment
     * @requires nom != null;   
     * @requires nom.lenght>0
     * @requires val_nutr>0
     * @param nom
     * @param val_nutr
     */
    public Aliment(String nom, int val_nutr) {
        this.nom = nom;
        this.val_nutr = val_nutr;
    }

    @Override
    public int hashCode() {
        return this.nom.hashCode()
               +val_nutr;
    }

    @Override
    public boolean equals(Object obj) { 
        if ( ! (obj instanceof Aliment) )
            return false;
        Aliment o = (Aliment) obj;
        if (!this.nom.equals(o.nom))
            return false;
        if (this.val_nutr != o.val_nutr)
            return false;
        return true;
    }

    

    @Override
    public String toString() {
        return "Aliment [nom=" + nom + ", val_nutr=" + val_nutr + "]";
    }

    public static void main(String[] args) {
        Aliment al1= new Aliment("Pizza",10);
        Aliment al2= new Aliment("Pizza",10);
        System.out.println(al1.hashCode()==al2.hashCode());
    }    


    

}