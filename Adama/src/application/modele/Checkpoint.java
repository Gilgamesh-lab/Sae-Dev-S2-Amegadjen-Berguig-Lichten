package application.modele;

public class Checkpoint {
	private int x;
	private int y;
	private Carte environnement;

	public Checkpoint(int x, int y, Carte environnement) {
		this.x = x;
		this.y = y;
		this.environnement = environnement;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Carte getEnvironnement(){
		return this.environnement;
	}
}

