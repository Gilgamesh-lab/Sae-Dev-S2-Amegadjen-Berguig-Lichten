package application.modele;

public class Terre extends Ressource {
	
	private static final int durabiliter = 10; //quand on pose le bloc combien en combien de coup les ennemis le casse

	public Terre( int x, int y, boolean posable) {
		super(3, x, y, posable);
	}

	@Override
	public void utiliser() {
		
		
	}

	

}
