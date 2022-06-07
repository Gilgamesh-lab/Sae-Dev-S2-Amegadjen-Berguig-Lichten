package application.modele.potions;

import application.modele.personnages.Joueur;

public class PotionVie implements Potion {
	
	private static final int NOMBRE_PV_RESTAURER = 3;
	
	
	@Override
	public void utiliser(Joueur joueur) {
		joueur.incrementerPv(NOMBRE_PV_RESTAURER);
	}


	@Override
	public void utiliser(int val) {
		// TODO Auto-generated method stub
		
	}
	
	public static int getNombrePvRestaurer() {
		return NOMBRE_PV_RESTAURER;
	}
}
