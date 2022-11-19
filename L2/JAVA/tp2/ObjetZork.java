public class ObjetZork{
    private String nom;

    /*
     * 
     @param 
     @requires
     @return
     @ensures

     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + poids;
        result = prime * result + (transportable ? 1231 : 1237);
        return result;
    }

  
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ObjetZork other = (ObjetZork) obj;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (poids != other.poids)
            return false;
        if (transportable != other.transportable)
            return false;
        return true;
    }
    private int poids;
    private boolean transportable;

    public ObjetZork(String nom){
        this.nom = nom;
    }    

    public ObjetZork(String n,int p,boolean t){
        this.nom = n;
        this.poids = p;
        this.transportable = t;
    }  
    
    public String getNom(){
        return this.nom;
    }

    public int getPoids(){
        return this.poids;
    }

    public boolean getTransportable(){
        return this.transportable;
    }

    /**
     * @param S'utilise sur un joueur avec limite=capaciteJoueur-poidsObjet
     * @requires td
     * @return Renvoie Vrai si l'objet est transportable Faux sinon
     * @ensures 
     */
    public boolean estTransportablePar(int limite){
        if(this.poids<limite) { return true; }
        else{ return false; }
    }

    @Override //on remplace ce que renvoie de base tostring

    /*
    *@return "Renvoie les données de l'objet"
    */
    public String toString(){
    return nom + " -- "  + poids + "Kg";
    }
    static public void iniatilize (ObjetZork[] tab){

        for(int i=0;i<tab.length;i++){
            tab[i]=null;
        }

    }



    }
    
    