package application.modele;

public class Bois extends Ressource {

	public Bois(boolean posable, int x, int y, int indice) {
		super(posable, x, y, indice);
		
	}
	
	
	public Bois(int indice) {
		super(false, 5,5, indice);
	}


	@Override
	public void utiliser(int val) {
		// TODO Auto-generated method stub
		
	}
}
