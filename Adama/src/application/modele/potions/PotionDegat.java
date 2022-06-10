package application.modele.potions;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class PotionDegat implements Potion {
	
	private final static double AUGMENTATION_DEGAT = 1.5;

	public PotionDegat() {
		
	}
	
	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}
}
