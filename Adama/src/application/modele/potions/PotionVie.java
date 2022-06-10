package application.modele.potions;

import application.modele.personnages.Joueur;
import application.modele.ressources.Ressource;

public class PotionVie implements Potion {
	
	private static final int NOMBRE_PV_RESTAURER = 3;
	
	public PotionVie() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void utiliser() {
		
	}


	@Override
	public Ressource utiliser(int val) {
		return null;
		
		
	}
	
	public static int getNombrePvRestaurer() {
		return NOMBRE_PV_RESTAURER;
	}
}
