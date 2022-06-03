package application.modele.effet;

public class Ralentir extends Effet {

	private static final int POURCENTAGE_RALENTISSEMENT = 75;
	public Ralentir() {
		super();
	}

	public Ralentir(int durée) {
		super(durée);
	}

	@Override
	public void appliquerEffet() {

	}

	public static int getPourcentageRalentissement() {
		return POURCENTAGE_RALENTISSEMENT;
	}

}
