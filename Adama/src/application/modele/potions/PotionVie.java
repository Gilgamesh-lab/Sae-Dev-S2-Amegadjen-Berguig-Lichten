package application.modele.potions;

public class PotionVie implements Potion {
	
	private static final int NOMBRE_PV_RESTAURER = 3;
	
	public PotionVie() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliser(int val) {
	
	}
	
	public static int getNombrePvRestaurer() {
		return NOMBRE_PV_RESTAURER;
	}
}
