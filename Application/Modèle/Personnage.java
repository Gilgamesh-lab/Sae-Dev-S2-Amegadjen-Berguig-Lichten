import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
	
	private IntegerProperty pvProperty ;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int vitesseDeplacement;
	private Environnement environnement;
	private Inventaire inventaire;
	
	public Personnage(int pv, int x, int y, 
	int vitesseDeplacement, Environnement environnement,
	Inventaire inventaire){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = inventaire;
	}
	
	public Personnage(int pv, int x, int y, 
			int vitesseDeplacement, Environnement environnement){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = new Inventaire(20);
	}
	
	public final int getPv() {
		return this.pvProperty.getValue();
	}
	
	public final void setPv(int val) {
		this.pvProperty.setValue(val);
	}
	
	public final IntegerProperty pvProperty() {
		return this.pvProperty;
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
	
	public void setVitesseDeplacement(int vitesseDeplacement) {
		this.vitesseDeplacement = vitesseDeplacement;
	}
	
	
	
	public int getVitesseDeplacement() {
		return this.vitesseDeplacement;
	}
	
	public Environnement getEnvironnement() {
		return this.environnement;
	}
	
	public Inventaire getInventaire() {
		return this.inventaire;
	}
	
	public void decremeterPv(int degat) {
		this.pvProperty.setValue(this.pvProperty.getValue() - degat);
	}
	
	public void incrementerPv(int soin) {
		this.pvProperty.setValue(this.pvProperty.getValue() + soin);
	}
	
	public void grimper(int val) {
		this.yProperty.setValue(this.yProperty.getValue() + val);
	}
	
	public void descendre(int val) {
		this.yProperty.setValue(this.yProperty.getValue() - val);
	}
	
	public void avancer() {
		this.xProperty.setValue(this.xProperty.getValue() + this.vitesseDeplacement);
	}
	
	public void reculer() {
		this.xProperty.setValue(this.xProperty.getValue() - this.vitesseDeplacement);
	}
	
	public boolean estMort() {
		return this.pvProperty.getValue() == 0;
	}
	
	public Inventaire dropInventaire() {
		Inventaire inventaire2 = this.inventaire;
		this.inventaire = null;
		return inventaire2;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
