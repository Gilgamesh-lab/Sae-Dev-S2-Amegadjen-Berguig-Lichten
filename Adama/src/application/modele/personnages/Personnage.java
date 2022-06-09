package application.modele.personnages;

import java.io.IOException;

import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.ressources.Bois;
import application.modele.ressources.Ressource;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Classe abstraite qui est au-dessus de Joueur et de PNJ
 * Elle a trois attributs qui sont ecoutables : pvProperty, xProperty, yProperty
 * Elle a une vitesse de déplacement, une hauteur de saut, un environement, une taille(tab[0]=tailleEnX, tab[1]=tailleEnY)
 * Et un inventaire(les pnj en ont également un qui aura les drops que l'on récupérera en les tuant)
 * @author jberguig
 *
 */
public abstract class Personnage {

	private IntegerProperty pvProperty;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int vitesseDeplacement;
	private Environnement environnement;
	private Inventaire inventaire;
	private int hauteurSaut;
	private int[] taille;
	
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
	
	/**
	 * Augmente les PV
	 * @param soin nombre de pv récupéré
	 */
	public void incrementerPv(int soin) {
		this.setPv(pvProperty.getValue() + soin);
	}
	
	/**
	 * Diminue les PV
	 * @param degat nombre de pv perdue
	 */
	public void decremeterPv(int degat) {
		this.incrementerPv(-degat);
	}

	/**
	 * Effectue un mouvement vers le haut si le param est négative le joueur descend
	 * @param val nombre dont l'on monte ou descend
	 */
	public void translationY(int val) {
		this.yProperty.setValue(this.getY() - val);
	}
	
	/**
	 * Permet d'effectuer une translationY de val si toucheY est true
	 * @param val
	 * @ failed or interrupted I/O operation
	 */
	public void monter(int val) {
		if(this.toucheY(true))
			translationY(val);
	}
	
	/**
	 * Permet d'effectuer une translationY de -val si toucheY est true
	 * @param val
	 * @ failed or interrupted I/O operation
	 */
	public void descendre(int val) {
		if(this.toucheY(false)) {
			translationY(-val);
		}
	}
	
	/**
	 * Permet de savoir si on ne touche rien au niveau des y au dessus du perso si auDessus est true est en dessous sinon 
	 * @param auDessus
	 * @return
	 * @
	 */
	public boolean toucheY(boolean auDessus) {
		boolean gauche;
		boolean droite;
		if(auDessus) {
			gauche = this.environnement.getCarte().emplacement(this.getX()+1, this.getY()-32)==null || this.environnement.getCarte().emplacement(this.getX()+1, this.getY()-32) instanceof Bois;
			droite = this.environnement.getCarte().emplacement(this.getX()+31, this.getY()-32)==null || this.environnement.getCarte().emplacement(this.getX()+31, this.getY()-32) instanceof Bois;
		}
		else {
			gauche = this.environnement.getCarte().emplacement(this.getX()+1, this.getY()+64)==null || this.environnement.getCarte().emplacement(this.getX()+1, this.getY()+64) instanceof Bois;
			droite = this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+64)==null || this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+64) instanceof Bois;
		}
		return (gauche && droite) && !((gauche || droite) && !(gauche && droite));
	}

	/**
	 * Sauter permet uniquement de sauter de la hauteur du saut du personnage.
	 * Et est donc différent de monter car un perso pourrait être projeter par une attaque
	 * a une valeur plus haute/basse que celle de son saut.
	 * @
	 */
	public void sauter() {
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
			teteCogne = this.environnement.getCarte().emplacement(this.getX()+31, this.getY())==null || this.environnement.getCarte().emplacement(this.getX()+31, this.getY()) instanceof Bois;
			corpCogne = this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+32)==null || this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+32) instanceof Bois;
		}
		else {
			teteCogne = this.environnement.getCarte().emplacement(this.getX()+1, this.getY())==null || this.environnement.getCarte().emplacement(this.getX()+1, this.getY()) instanceof Bois;
			corpCogne = this.environnement.getCarte().emplacement(this.getX(), this.getY()+32)==null || this.environnement.getCarte().emplacement(this.getX(), this.getY()+32) instanceof Bois;
		}
		return (teteCogne && corpCogne) && !((teteCogne || corpCogne) && !(teteCogne && corpCogne));// négation d'un ou exclusif
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

	public void gravite() {
		this.descendre(5);
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
	
	public int[] getTaille() {
		return taille;
	}
}