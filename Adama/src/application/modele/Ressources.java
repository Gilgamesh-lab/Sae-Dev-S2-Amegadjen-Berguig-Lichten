package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Ressources implements Item {
	private boolean posable;
	private int nombre;
	private int durabiliter;
	private boolean craftable;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	
	public Ressources(int nombre, boolean craftable, int x, int y) {
		this.posable = false;
		this.nombre = nombre;
		this.durabiliter = -1;
		this.craftable = craftable;
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
	}
	
	public Ressources(int nombre, int durabiliter, boolean craftable, int x, int y) {
		this.posable = true;
		this.nombre = nombre;
		this.durabiliter = durabiliter;
		this.craftable = craftable;
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
	}
	
	public final int getX() {
		return this.xProperty.getValue();
	}
	
	public final void setX(int val) {
		this.xProperty.setValue(val);
	}
	
	public final IntegerProperty xProperty() {
		return this.xProperty;
	}
	
	public final int getY() {
		return this.yProperty.getValue();
	}
	
	public final void setY(int val) {
		this.yProperty.setValue(val);
	}
	
	public final IntegerProperty yProperty() {
		return this.yProperty;
	}
	
	public void setNombre(int val) {
		this.nombre = val;
	}
	
	public void augmenterNombre(int val) {
		this.nombre += val;
	}
	
	public void diminuerNombre(int val) {
		augmenterNombre(-val);
	}
	
	public void utiliser() {
		
	}
	
	public boolean estPosable() {
		return this.posable;
	}
	
	public int getNombre() {
		return this.nombre;
	}
	
	
	public void prendreDegat(int degat) {
		this.durabiliter -= degat;
	}
	
	
	public boolean estCasser() {
		return this.durabiliter <= 0;
	}
}
