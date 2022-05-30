package application.modele;

public abstract class Plante extends Ressource {

	public Plante(boolean posable,int x, int y, int indice) {
		super(posable, x, y, indice);
		
	}
	
	
	public Plante(int indice) {
		super(false, 5,5, indice);
	}	
	
	@Override
	public void utiliser() {
		
		
	}

}
