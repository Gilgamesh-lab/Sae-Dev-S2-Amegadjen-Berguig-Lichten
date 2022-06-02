package application.modele.armes;

import application.modele.ressources.Ressource;

public class Epee extends Arme{
	public Epee() {
		super(1, 1, 2);
		
	}
	
	public void utiliser() {
		
	}

//	public void attaquer(Inventaire inventaire, Environnement environnement, int lieu) {
//		int largeur = environnement.getCarte().getLargeur();
//		if (environnement. instanceof Personnage)
//			environnement.attaquerPersonnages(lieu, this.getDegat());
//		if (environnement. instanceof Personnage)
//			environnement.attaquerPersonnages(lieu-largeur, this.getDegat());
//		if (environnement. instanceof Personnage)
//			environnement.attaquerPersonnages(lieu+largeur, this.getDegat());
//		
//	}

	@Override
	public Ressource utiliser(int val) {
		return null;
		// TODO Auto-generated method stub
		
	}
}