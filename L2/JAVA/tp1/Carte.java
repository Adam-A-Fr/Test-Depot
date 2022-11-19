public class Carte{

famille f;
rang r;
/*
*@param "Prend en paramètre une Famille et un Rang de Carte" 
*/
public Carte(){
	this.f= famille.Pique;
	this.r= rang.Roi;
}

public Carte(famille f,rang r){
	this.f= f;
	this.r= r;
}


@Override //on remplace ce que renvoie de base tostring

/*
*@return "Renvoie les données de la carte"
*/
public String toString(){
return r + " de "  + f;
}

}


