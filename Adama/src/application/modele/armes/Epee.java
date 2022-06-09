package application.modele.armes;

import application.modele.ressources.Ressource;
import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.personnages.Personnage;

public class Epee extends Arme{
	public Epee() {
		super(1, 1, 2);
		
	}
	
	public void utiliser() {
		
	}

	public void attaquer(Inventaire inventaire, Environnement environnement, int lieu) {
		int largeur = environnement.getCarte().getLargeur();
		if (environnement.emplacement(lieu) instanceof Personnage)
			environnement.attaquerPersonnages(lieu, this.getDegat());
		if (environnement.emplacement(lieu) instanceof Personnage)
			environnement.attaquerPersonnages(lieu-largeur, this.getDegat());
		if (environnement.emplacement(lieu) instanceof Personnage)
			environnement.attaquerPersonnages(lieu+largeur, this.getDegat());
		
	}
	
	public String toString() {
		return "Epee";
	}

	@Override
	public Ressource utiliser(int val) {
		return null;
		// TODO Auto-generated method stub
		
	}
}