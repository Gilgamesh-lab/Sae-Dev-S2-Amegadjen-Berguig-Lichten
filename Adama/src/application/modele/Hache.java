package application.modele;

public class Hache extends Outil {

	private final static int DEGATS=10;
//	private int x;
//	private int y;
	
	public Hache(Environnement env) {
		super(env, DEGATS);
	}

	/*
	 * TODO Posibilité de bug si l'arbre ets à un indice<largeur de la map 
	 * A régler dans le futur
	 */
	public void utiliser(int lieu) {
		int largeur=super.getEnvironnement().getCarte().getLargeur();
		if (super.getEnvironnement().getCarte().emplacement(lieu) instanceof Bois)
			super.getEnvironnement().getCarte().attaquerBlock(lieu, DEGATS);
		if (super.getEnvironnement().getCarte().emplacement(lieu-largeur) instanceof Bois)
			super.getEnvironnement().getCarte().attaquerBlock(lieu-largeur, DEGATS);
		if (super.getEnvironnement().getCarte().emplacement(lieu+largeur) instanceof Bois)
			super.getEnvironnement().getCarte().attaquerBlock(lieu+largeur, DEGATS);			
	}


}
