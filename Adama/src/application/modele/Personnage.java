package application.modele;

import java.io.IOException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Personnage {
	
	private IntegerProperty pvProperty;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int vitesseDeplacement;
	private Environnement environnement;
	private Inventaire inventaire;
	private int hauteurSaut;
	private int[] taille = new int[2];
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement,Inventaire inventaire, int hauteurSaut, int[] taille){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = inventaire;
		this.hauteurSaut = hauteurSaut;
		this.taille = taille;
	}

	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement, int[] taille){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = new Inventaire(20);
		this.hauteurSaut = 1;
		this.taille = taille;
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
		return this.hauteurSaut;
	}
	
	public final void setHauteurSaut(int val) {
		this.hauteurSaut=val;
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
	
	/**
	 * Effectue un mouvement vers le haut
	 * @param val si elle est négative le joueur descend
	 */
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
			return this.environnement.getCarte().emplacement(this.getX(), this.getY()-32)==null;
		else
			return this.environnement.getCarte().emplacement(this.getX(), this.getY()+64)==null;
    }
	
	/**
	 * Sauter permet uniquement de sauter de la hauteur du saut du personnage.
	 * Et est donc différent de monter car un perso pourrait être projeter par une attaque
	 * a une valeur plus haute/basse que celle de son saut.
	 * @throws IOException
	 */
	public void sauter() throws IOException {
		this.monter(this.hauteurSaut);
	}
	
	/**
	 * 
	 * @param val
	 */
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
		boolean teteCogne;
		boolean corpCogne;
		if(aDroite) {
			teteCogne = this.environnement.getCarte().emplacement(this.getX()+32, this.getY())==null;
			corpCogne = this.environnement.getCarte().emplacement(this.getX()+32, this.getY()+32)==null;

		}
		else {
			teteCogne = this.environnement.getCarte().emplacement(this.getX(), this.getY())==null;
			corpCogne = this.environnement.getCarte().emplacement(this.getX(), this.getY()+32)==null;
		}
		return !((teteCogne || corpCogne) && !(teteCogne && corpCogne));// négation d'un ou exclusif
	}

	
	
	public boolean estMort() {
		return this.getPv() == 0;
	}
	
	/**
	 * Sera utile quand potion implementé
	 * @param vitesseBonus
	 * @return
	 */
	public int Deplacement(int vitesseBonus) {
		return this.vitesseDeplacement*(vitesseBonus/100)+this.vitesseDeplacement;
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