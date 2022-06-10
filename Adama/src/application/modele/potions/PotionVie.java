package application.modele.potions;

import application.modele.exception.ErreurInventairePlein;
import application.modele.personnages.Joueur;

public class PotionVie implements Potion {
	
	private static final int NOMBRE_PV_RESTAURER = 3;
	
	public PotionVie() {
		// TODO Auto-generated constructor stub
	}

	public static int getNombrePvRestaurer() {
		return NOMBRE_PV_RESTAURER;
	}

	@Override
	public void utiliser(int val, Joueur joueur) throws ErreurInventairePlein {
		// TODO Auto-generated method stub
		
	}
}
