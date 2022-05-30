package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Ressource implements Item {
	private boolean posable;
	private int nombre;
	private int durabiliter;
	@SuppressWarnings("unused")
	private boolean estUnMateriauDeConstruction;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int indice;
	
	public Ressource(boolean posable, int x, int y, int indice){
		this.posable = posable;
		this.nombre = 1;
		this.durabiliter = 5;
		this.estUnMateriauDeConstruction = !posable;
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.indice = indice;
	
	}
	
	
	public Ressource(boolean posable, int nombre, int durabiliter, boolean estUnMateriauDeConstruction, int x, int y, int indice) {
		this.posable = posable;
		this.nombre = nombre;
		this.durabiliter = durabiliter;
		this.estUnMateriauDeConstruction = estUnMateriauDeConstruction;
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.indice = indice;
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
	
	public boolean estUnMateriauDeConstruction(){
		return this.estUnMateriauDeConstruction;
	}
	
	public void prendreDegat(int val) {
		this.durabiliter -= val;
	}
	
//	public void decremeterPv(int degat) {
//		for (int i = 0; this.getDurabiliter() < 0 && i < degat ; i++) {
//			this.prendreDegat();
//		}
//	}
	
	
	public int getDurabiliter() {
		return this.durabiliter;
	}


	public boolean estCasser() {
		return this.durabiliter <= 0;
	}
	
	
	
	public void casser() {
		this.setX(-32);
		this.setY(-32);
	}


	public boolean estDetruit() {
		return this.durabiliter <= 0;
	}


	public int getIndice() {
		return indice;
	}


	public void setIndice(int indice) {
		this.indice = indice;
	}
	
}
