import java.util.ArrayList;

public class Joueur{

    private String nom;
    private Piece piece_joueur;
    private int capacite;
    private int poids_inventaire=0;
    private ArrayList<ObjetZork> inventaire = new ArrayList<ObjetZork>();

    
    
    public ArrayList<ObjetZork> getInventaire() {
        return inventaire;
    }
    public String getNom() {
        return nom;
    }
    public Piece getPiece_joueur() {
        return piece_joueur;
    }
    public Joueur(String nom, Piece piece_joueur, int capacite) {
        this.nom = nom;
        this.piece_joueur = piece_joueur;
        this.capacite = capacite;
    }

    public Joueur(){}

    public void addmobj(ObjetZork obj){
        if (this.piece_joueur.getObjs().contains(obj)&&obj.estTransportablePar(capacite-poids_inventaire)){
            this.inventaire.add(obj);
            this.piece_joueur.elimobj(obj);
            this.poids_inventaire+=obj.getPoids();

        }
    }

    public void elimobj(ObjetZork obj){
        if (this.inventaire.contains(obj)){

            ArrayList<ObjetZork> tmp = new ArrayList<ObjetZork>();
            tmp.add(obj);

            this.inventaire.remove(obj);
            this.piece_joueur.addmobj(tmp);
            this.poids_inventaire-=obj.getPoids();

        }

    }

    public void afficherInventaire(){
        for(ObjetZork e :inventaire){
            System.out.println(e.toString());
        }
    }


    



    /* 
    private ObjetZork[] inventaire = new ObjetZork[10];

    public ObjetZork[] getInventaire() {
        return inventaire;
    }

    public Joueur(String nom){
        this.nom = nom;
        
    }    

    public Joueur(String nom, Piece piece, int capacite){
        this.nom = nom;
        this.piece_joueur = piece;
        this.capacite=capacite;
        ObjetZork.iniatilize(this.inventaire);
    }  
    
    public void addmobj(ObjetZork obj){
        ObjetZork tmp= this.piece_joueur.elimobj(obj);

        if((this.poids_inventaire + tmp.getPoids())<=this.capacite){

            for(int i=0;i<this.inventaire.length;i++)
            if (inventaire[i]==null){
                inventaire[i]=tmp ;
                break;}
            else System.out.println("Erreur, aucune place disponible");}
        else System.out.println("Vous n'avez pas la capacité de prendre cet objet");  
	}

    public void poserobj(String Nomobj){
		boolean found=false;
		ObjetZork tmp= new ObjetZork("tmp");
		for(int i=0;i<this.inventaire.length;i++)if (inventaire[i].getNom()== Nomobj) {found=true;
		tmp = inventaire[i];
		inventaire[i]=null ;break;};
		if(found){

            this.poids_inventaire =this.poids_inventaire - tmp.getPoids();
            this.piece_joueur.addmobj(tmp);
        } 
		else ;
		
	}

    public void afficherInventaire(){
        for(int i=0;i<this.inventaire.length;i++){
            if(this.inventaire[i] == null) break;
            else System.out.println(this.inventaire[i].toString());
        }
    } 

    */




    }

