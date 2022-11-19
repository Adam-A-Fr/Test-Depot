public class Jeu{

	public static void main(String args[]){
		Carte unecarte;
		unecarte=new Carte();
		unecarte.f=famille.Pique;
		unecarte.r=rang.As;
		
		System.out.println(unecarte.toString());
	}
}	
