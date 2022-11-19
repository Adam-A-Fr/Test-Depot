import java.util.*;


public class Test{

    public static void main(String[] args) {

        Piece salle = new Piece("PSSSS Pssss Pssss");

        ObjetZork oz1 = new ObjetZork("Tp", 2, true);
        ObjetZork oz2 = new ObjetZork("Td", 9999999, false);
        ArrayList<ObjetZork> tmp = new ArrayList<ObjetZork>();
        tmp.add(oz1);
        ArrayList<ObjetZork> tmp2 = new ArrayList<ObjetZork>();
        tmp2.add(oz2);
        Joueur GOAT = new Joueur("Hamoud", salle, 90 );

        System.out.println("Listage des objets :");
        System.out.println(oz1);
        System.out.println(oz2);
        salle.addmobj(tmp);
        salle.addmobj(tmp2);
        System.out.println("Listage des objets de la piece");
        salle.afficherObjet();

        System.out.println("Listage des objets du joueurs");
        GOAT.addmobj(oz1);
        
        GOAT.addmobj(oz2);

        GOAT.afficherInventaire();
       
        System.out.println(salle.contient(oz2));

        System.out.println(salle.contientCombienDe(oz2));

    }






}