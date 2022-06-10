package application.modele.potions;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class PotionVitesse implements Potion {
	
	private static final int POURCENTAGE_AMELOIRATION_VITESSE = 25;

	public PotionVitesse() {
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}
}
