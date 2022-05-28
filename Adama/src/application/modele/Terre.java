package application.modele;

public class Terre extends Ressource {

	
	public Terre(boolean posable, int x, int y) {
		super(  posable, x, y);
	}
	
	public Terre() {
		super(true,5,5);
	}

	@Override
	public void utiliser() {
		
		
	}
	
	
	public String toString() {
		return "Terre";
	}

	

}