
public abstract class Ressources implements Item {
	private boolean posable;
	private int nombre;
	private int durabiliter;
	
	public Ressources(boolean posable, int nombre) {
		this.posable = posable;
		this.nombre = nombre;
		this.durabiliter = 0;
	}
	
	public Ressources(boolean posable, int nombre,int durabiliter) {
		this.posable = posable;
		this.nombre = nombre;
		this.durabiliter = durabiliter;
	}
	
	public void setNombre(int val) {
		this.nombre = val;
	}
	
	public void augmenterNombre(int val) {
		this.nombre += val;
	}
	
	public void diminuerNombre(int val) {
		this.nombre -= val;
	}
	
	public void utiliser() {
		
	}
	
	public boolean estPosable() {
		return this.posable;
	}
	
	public int getNombre() {
		return this.nombre;
	}
	
	
	public boolean estCasser() {
		return this.durabiliter <= 0;
	}
}
