package application.modele;

public class Epee extends Arme{
	public Epee() {
		super(1, 1, 2);
		
	}
	
	public void utiliser() {
		
	}

	public void attaquer(Inventaire inventaire, Environnement environnement, int lieu) {
		int largeur = environnement.getCarte().getLargeur();
		if (environnement.getCarte().emplacement(lieu) instanceof Personnage)
			environnement.attaquerPersonnages(lieu, this.getDegat());
		if (environnement.getCarte().emplacement(lieu-largeur) instanceof Personnage)
			environnement.attaquerPersonnages(lieu-largeur, this.getDegat());
		if (environnement.getCarte().emplacement(lieu+largeur) instanceof Personnage)
			environnement.attaquerPersonnages(lieu+largeur, this.getDegat());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
