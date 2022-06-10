package application.modele.effet;

public abstract class Effet {
	
	private int durée;
	
	public Effet(int durée) {
		this.setDurée(durée);
	}
	
	public Effet() {
		durée = 0;
	}
	
	
	public abstract int appliquerEffet();

	public int getDurée() {
		return durée;
	}

	public void setDurée(int durée) {
		this.durée = durée;
	}
}
