package application.modele;

import java.util.ArrayList;

public class Arc extends Arme {
	private Fleche fleche;
	private Inventaire inventaire;
	
	
	public Arc(Inventaire inventaire) {
		super(1, 1, 2);
		this.inventaire = inventaire;
	}
	
	
	public void bander() ErreurObjetIntrouvable {
		this.fleche = inventaire.getFleche();
		this.inventaire.supprimer(0);
	}
	
	public void derbander() ErreurInventairePlein {
		this.inventaire.ajouter(this.fleche);
		this.fleche = null;
		
	}
	
	public void tirer(Environnement environnement, boolean direction) throws ErreurInventairePlein {
		int distance = 0;
		int lieu = 0;
		int largeur = environnement.getCarte().getLargeur();
		environnement.getCarte().getPoubelle().ajouter(this.fleche);
		while(this.getPorter() >= distance) {
			fleche.tirer(direction);
			lieu = fleche.getX();
			distance++;
			if (environnement.getCarte().emplacement(lieu) instanceof Personnage)
				environnement.attaquerPersonnages(lieu, this.fleche.getDegat());
			if (environnement.getCarte().emplacement(lieu-largeur) instanceof Personnage)
				environnement.attaquerPersonnages(lieu-largeur, this.fleche.getDegat());
			if (environnement.getCarte().emplacement(lieu+largeur) instanceof Personnage)
				environnement.attaquerPersonnages(lieu+largeur, this.fleche.getDegat());
		}
		
		this.fleche.detruire();
		this.fleche = null;
	}
	
	

	
	public void utiliser() {
		
	}
	
	
	
}