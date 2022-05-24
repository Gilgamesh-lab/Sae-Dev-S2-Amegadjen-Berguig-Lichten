package application.modele;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
	private int[] taille = new int[2];
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement,Inventaire inventaire, int hauteurSaut){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = inventaire;
		this.hauteurSautProperty = new SimpleIntegerProperty(hauteurSaut);
		this.taille[0]=1; this.taille[1]=2;
	}
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = new Inventaire(20);
		this.hauteurSautProperty = new SimpleIntegerProperty(1);
		this.taille[0]=1; this.taille[1]=2;
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
	
	public void translationY(int val) {
		this.yProperty.setValue(this.getY() - val);
	}
	
	public void monter(int val) throws IOException {
		if(this.toucheY(true))
			translationY(val);
	}
	
	public void descendre(int val) throws IOException {
		if(this.toucheY(false)) {
			translationY(-val);
		}
	}
	
	public boolean toucheY(boolean auDessus) throws IOException {
		if(auDessus)
			return this.environnement.getCarte().emplacement(this.getX(), this.getY()-32, taille)==null;
		else
			return this.environnement.getCarte().emplacement(this.getX(), this.getY()+64, taille)==null;
    }
	
	public void sauter() throws IOException {
		this.monter(this.getHauteurSaut());
	}
	
	public void translationX(int val) {
		this.xProperty.setValue(this.getX() - val);
	}
	
	public void droite() {
		if(toucheX(true))
			this.translationX(-vitesseDeplacement);
	}
	
	public void gauche() {
		if(toucheX(false))
			this.translationX(vitesseDeplacement);
	}
	private boolean toucheX(boolean aDroite) {
		if(aDroite)
			return this.environnement.getCarte().emplacement(this.getX()+32, this.getY(), taille)==null || this.environnement.getCarte().emplacement(this.getX()+32, this.getY()+32, taille)==null || this.environnement.getCarte().emplacement(this.getX()+32, this.getY()+64, taille)==null;
		else
			return this.environnement.getCarte().emplacement(this.getX(), this.getY(), taille)==null || this.environnement.getCarte().emplacement(this.getX(), this.getY()+32, taille)==null || this.environnement.getCarte().emplacement(this.getX(), this.getY()+64, taille)==null;
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
			if(this.inventaire.getItem(i) instanceof Ressource) {
				this.inventaire.supprimer(i);
			}
		}
	}
	
	public void gravite() throws IOException {
		this.descendre(5);
	}
}