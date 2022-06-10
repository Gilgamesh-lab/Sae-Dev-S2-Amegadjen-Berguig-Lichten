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
	public int appliquerEffet() {
		return 0;

	}

	public static int getPourcentageRalentissement() {
		return POURCENTAGE_RALENTISSEMENT;
	}

}
