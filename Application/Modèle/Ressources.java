
public abstract class Ressources implements Item {
	public boolean posable;
	public int nombre;
	
	public Ressources(boolean posable, int nombre) {
		this.posable = posable;
		this.nombre = nombre;
	}
	
	public Ressources(boolean posable) {
		this.posable = posable;
		this.nombre = 1;
	}
	
	public void utiliser() {
		
	}
	
	public boolean estPosable() {
		return this.posable;
	}
	
	public int nombre() {
		return this.nombre;
	}
}
