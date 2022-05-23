package application.modele;

public class Terre extends Ressources {

	public Terre(int nombre, int durabiliter, int x, int y, boolean posable) {
		super(nombre, durabiliter,  posable, x, y);
	}
	
	public Terre( int x, int y) {
		super(1, 5,  true, x, y);
	}

	@Override
	public void utiliser() {
		
		
	}
	
	public String toString() {
		return "Terre";
	}

	

}