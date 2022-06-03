package application.modele;

public class Checkpoint {
	private int x;
	private int y;
	private Environnement environnement;

	public Checkpoint(int x, int y, Environnement environnement) {
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
	
	public Environnement getEnvironnement(){
		return this.environnement;
	}
}