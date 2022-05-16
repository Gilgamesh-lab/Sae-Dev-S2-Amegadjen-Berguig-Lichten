import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
	
	private IntegerProperty pvProperty ;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int vitesseDeplacement;
	private Environnement environnement;
	private static Inventaire inventaire;
	private IntegerProperty hauteurSautProperty;
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement,Inventaire inventaire, int hauteurSaut){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = inventaire;
		this.hauteurSautProperty = new SimpleIntegerProperty(hauteurSaut);
	}
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = new Inventaire(20);
		this.hauteurSautProperty = new SimpleIntegerProperty(1);
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
	
	public final int getHauteurSaut() {
		return this.hauteurSautProperty.getValue();
	}
	
	public final void setHauteurSaut(int val) {
		this.hauteurSautProperty.setValue(val);
	}
	
	public final IntegerProperty hauteurSautProperty() {
		return this.hauteurSautProperty;
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
	
	public void incrementerPv(int soin) {
		this.setPv(pvProperty.getValue() + soin);
	}
	
	public void decremeterPv(int degat) {
		this.incrementerPv(-degat);
	}
	
	
	public void monter(int val) {
		this.yProperty.setValue(this.yProperty.getValue() + val);
	}
	
	public void descendre(int val) {
		this.monter(-val);
	}
	
	public void sauter() {
		this.monter(this.getHauteurSaut());
	}
	
	public void droite(int val) {
		this.xProperty.setValue(this.xProperty.getValue() + val);
	}
	
	public void gauche(int val) { 
		this.droite(-val);
	}
	
	public void droite() { 
		this.droite(this.vitesseDeplacement);
	}
	
	public void gauche() {
		this.gauche(this.vitesseDeplacement);
	}
	
	public boolean estMort() {
		return this.getPv() == 0;
	}
	
	public void perdreRessources() { // Lorsque mort perd ses ressources
		for(int i = 0 ; i < this.inventaire.getTaille(); i++) {
			if(this.inventaire.getItem(i) instanceof Ressources) {
				this.inventaire.supprimer(i);
			}
		}
	} 
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}