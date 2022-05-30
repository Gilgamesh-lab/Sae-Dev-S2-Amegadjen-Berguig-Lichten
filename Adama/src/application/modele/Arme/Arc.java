package application.modele.Arme;

import java.util.ArrayList;

import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;

public class Arc extends Arme {
	private Fleche fleche;
	private ArrayList<Fleche> carquois;
	
	
	public Arc() {
		super(1, 1, 2);
		this.carquois = new ArrayList<Fleche>();
	}
	
	
	public void bander() throws ErreurObjetIntrouvable {
		this.fleche = carquois.remove(0);
	}
	
	public void debander() throws ErreurInventairePlein {
		this.carquois.add(this.fleche);
		this.fleche = null;
		
	}
	
//	public void tirer(Environnement environnement, boolean direction) throws ErreurInventairePlein {
//		int distance = 0;
//		int lieu = 0;
//		int largeur = environnement.getCarte().getLargeur();
//		environnement.getCarte().getPoubelle().ajouter(this.fleche);
//		while(this.getPorter() >= distance) {
//			fleche.tirer(direction);
//			lieu = fleche.getX();
//			distance++;
//			//TODO méthode pour récupérer 
//			if (environnement. instanceof Personnage)
//				environnement.attaquerPersonnages(lieu, this.fleche.getDegat());
//			if (environnement.getCarte().emplacement(lieu-largeur) instanceof Personnage)
//				environnement.attaquerPersonnages(lieu-largeur, this.fleche.getDegat());
//			if (environnement.getCarte().emplacement(lieu+largeur) instanceof Personnage)
//				environnement.attaquerPersonnages(lieu+largeur, this.fleche.getDegat());
//		}
//		
//		this.fleche.detruire();
//		this.fleche = null;
//	}
//	
//	

	
	public void utiliser() {
		
	}


	@Override
	public void utiliser(int val) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}