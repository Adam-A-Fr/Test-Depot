import java.util.Map;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      August 2000
 */

public class Piece {
	private ArrayList<ObjetZork> Objs = new ArrayList<ObjetZork>();
	public ArrayList<ObjetZork> getObjs() {
		return Objs;
	}




	private Piece[] Sorties;
	// Les objets des piéces
 	private String description;
	// mémorise les sorties de cette piece.
	private EnumMap<Direction, Piece> sorties;

	
	
	public Boolean elimobj(ObjetZork obj){
		return this.Objs.remove(obj);
	
		/*ObjetZork tmp= new ObjetZork("tmp");
		for(int i=0;i<cpt;i++)if (Objs[i] == obj) {found=true;
		tmp = Objs[i];
		Objs[i]=null ;break;};
		if(found)return tmp ;
		else return null;
		*/
	}
 
	public boolean contient(ObjetZork o){
		return this.Objs.contains(o);
	}

	public boolean estvide(){
		return this.Objs.isEmpty();
	}

	/*
	 * desp
	 * 
	 * @param 
	 */
	public void addmobj(ArrayList<ObjetZork> obj){
		
		Objs.addAll(obj);
		
	}

	public int contientCombienDe(ObjetZork o){
		int cpt=0;
		for(ObjetZork e:this.Objs){
			if(e.equals(o)){
				cpt++;
			}
		}
		return cpt;
	}



	/**
	 *  Initialise une piece décrite par la chaine de caractères spécifiée.
	 *  Initialement, cette piece ne possède aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 */
	public Piece(String description) {
		this.description = description;
		sorties = new EnumMap<Direction, Piece>(Direction.class);
		
	}


	/**
	 *  Définie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est  
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
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
	 *  Renvoie la description de cette piece (i.e. la description spécifiée lors
	 *  de la création de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatée pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
	 *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisée dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pièce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		for (Direction sortie :  sorties.keySet()) {
			resulString += " " + sortie;
		}
		return resulString;
	}


	/**
	 *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se déplacer
	 * @return            Piece atteinte lorsque l'on se déplace dans la direction
	 *      spécifiée ou null.
	 */
	public Piece pieceSuivante(Direction d) {
		return sorties.get(d);
	}
	
	public void afficherObjet(){
		
		for(ObjetZork e:this.Objs){
			
			System.out.println(e.toString());


		}
	}
		
			
        
    

}
