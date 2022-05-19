package application.modele;
import java.io.IOException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
	
	private IntegerProperty pvProperty ;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int vitesseDeplacement;
	private Environnement environnement;
	private Inventaire inventaire;
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
		this.yProperty.setValue(this.getY() + val);
	}
	
	public void descendre(int val) throws IOException {
		if(!this.estSurDeLaTerre()) {
			this.monter(-val);
		}
	}
	
	public boolean estSurDeLaTerre() throws IOException {// bloc taille : 32 * 32
		int[][] tab = Csv.csvToTab("exemple.csv", this.getEnvironnement().getCarte().getLargeur(),this.getEnvironnement().getCarte().getHauteur() );
	    
    	if(tab[this.getY() + 1][this.getX()] == 1) { //  fixer le +2
    		return true;
    	}
    	else {
    		return false;
    	}
	}
	
	public void sauter() {
		this.monter(this.getHauteurSaut());
	}
	
	public void droite(int val) {
		this.xProperty.setValue(this.getX() + val);
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
	
	public int Deplacement(int vitesseBonus) {
		return this.vitesseDeplacement*(vitesseBonus/100)+this.vitesseDeplacement;
	}
	
	public void reculer() {
		this.xProperty.setValue(this.xProperty.getValue() - this.vitesseDeplacement);
	}
	
	public void avancer() {
		this.xProperty.setValue(this.xProperty.getValue() + this.vitesseDeplacement);
	}
	
	public void perdreRessources() { // Lorsque mort perd ses ressources
		for(int i = 0 ; i < this.inventaire.getTaille(); i++) {
			if(this.inventaire.getItem(i) instanceof Ressources) {
				this.inventaire.supprimer(i);
			}
		}
	} 
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
