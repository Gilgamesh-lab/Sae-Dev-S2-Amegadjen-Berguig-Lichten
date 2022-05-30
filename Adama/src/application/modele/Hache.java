package application.modele;

public class Hache extends Outil {

	private final static int DEGATS=10;
//	private int x;
//	private int y;
	
	public Hache(Environnement env) {
		super(env, DEGATS);
	}

	/**
	 * 
	 */
	public void utiliser(int lieu) {
		int largeur=super.getEnvironnement().getCarte().getLargeur();
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Bois)
			super.getEnvironnement().getCarte().attaquerBlock(lieu, DEGATS);
		try {
		if (super.getEnvironnement().getCarte().emplacement(lieu-largeur) instanceof Bois)
			this.utiliser(lieu-largeur);
//		if (super.getEnvironnement().getCarte().emplacement(lieu+largeur) instanceof Bois)
//			this.utiliser(lieu+largeur);
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Pas du bois");
		}
	}


}
