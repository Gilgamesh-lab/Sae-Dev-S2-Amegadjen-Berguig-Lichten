package application.modele.effet;

public class Accelerer extends Effet {
	
	private static final int POURCENTAGE_ACCELERATION = 75;
	
	public Accelerer() {
		super();
	}
	public Accelerer (int durée) {
		super(durée);
	}
	
	@Override
	public int appliquerEffet() {
		int p = POURCENTAGE_ACCELERATION;
		return p;
		
	}
	public static int getPourcentageAcceleration() {
		return POURCENTAGE_ACCELERATION;
	}

}
