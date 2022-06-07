package application.modele.personnages;

import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.effet.Effet;
import application.modele.effet.Ralentir;
import application.modele.ressources.Bois;
import application.modele.ressources.Plante;
import application.modele.ressources.Ressource;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	private ObservableList<Effet> effets;
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement,Inventaire inventaire, int hauteurSaut, int[] taille){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = inventaire;
		this.hauteurSaut = hauteurSaut;
		this.taille = taille;
		this.effets = FXCollections.observableArrayList();
		effets.addAll(null, null, null, null); //Chaque valeur correspond à un effet different
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
		this.effets = FXCollections.observableArrayList();
		effets.addAll(null, null, null, null);
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
			gauche = (this.environnement.getCarte().emplacement(this.getX()+1, this.getY()-32)==null 
			|| this.environnement.getCarte().emplacement(this.getX()+1, this.getY()-32) instanceof Bois
			|| this.environnement.getCarte().emplacement(this.getX()+1, this.getY()-32) instanceof Plante);
			
			droite = (this.environnement.getCarte().emplacement(this.getX()+31, this.getY()-32)==null
			|| this.environnement.getCarte().emplacement(this.getX()+31, this.getY()-32) instanceof Bois
			|| this.environnement.getCarte().emplacement(this.getX()+31, this.getY()-32) instanceof Plante);
		}
		else {
			gauche = (this.environnement.getCarte().emplacement(this.getX()+1, this.getY()+64)==null
			|| this.environnement.getCarte().emplacement(this.getX()+1, this.getY()+64) instanceof Bois
			|| this.environnement.getCarte().emplacement(this.getX()+1, this.getY()+64) instanceof Plante);
			
			droite = (this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+64)==null
			|| this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+64) instanceof Bois
			|| this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+64) instanceof Plante);
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
			teteCogne = (this.environnement.getCarte().emplacement(this.getX()+31, this.getY())==null
			|| this.environnement.getCarte().emplacement(this.getX()+31, this.getY()) instanceof Bois
			|| this.environnement.getCarte().emplacement(this.getX()+31, this.getY()) instanceof Plante);
			
			corpCogne = (this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+32)==null
			|| this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+32) instanceof Bois
			|| this.environnement.getCarte().emplacement(this.getX()+31, this.getY()+32) instanceof Plante);
		}
		else {
			teteCogne = (this.environnement.getCarte().emplacement(this.getX()+1, this.getY())==null 
			|| this.environnement.getCarte().emplacement(this.getX()+1, this.getY()) instanceof Bois
			|| this.environnement.getCarte().emplacement(this.getX()+1, this.getY()) instanceof Plante);
			
			corpCogne = (this.environnement.getCarte().emplacement(this.getX(), this.getY()+32)==null 
			|| this.environnement.getCarte().emplacement(this.getX(), this.getY()+32) instanceof Bois
			|| this.environnement.getCarte().emplacement(this.getX(), this.getY()+32) instanceof Plante);
		}
		return (teteCogne && corpCogne) && !((teteCogne || corpCogne) && !(teteCogne && corpCogne));// négation d'un ou exclusif
	}
	
	/**
	 * Ajoute un effet à la liste effets en fonction de quelle type d'effet il s'agit
	 * Si effet est Empoisonner 1er position 
	 * Si effet est Ralentir 2eme position 
	 * Si effet est Renfoncer 3eme position 
	 * Si effet est Accelerer 4eme position 
	 * @param effet
	 */
	public void ajouterEffet(Effet effet) {
		String quelleEffet = effet.getClass().getSimpleName();
		switch (quelleEffet) {
		case "Empoisoner":
			effets.add(0, effet);
			break;
		case "Ralentir":
			effets.add(1, effet);
		case "Renforcer":
			effets.add(2, effet);
			break;
		case "Accelerer":
			effets.add(3, effet);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Remplace l'effet a l'indice indice par null
	 * @param indice
	 */
	public void SupprimerEffet(int indice) {
		effets.set(indice, null);
	}
	

	public boolean estMort() {
		return this.getPv() == 0;
	}

	/**
	 * Sera utile quand potion implementé
	 * @param vitesseBonus
	 * @return
	 */
	public int Deplacement(int vitesseBonus, boolean accelerer) {
		if(accelerer)
			return this.vitesseDeplacement*(1 + (vitesseBonus/100));
		return this.vitesseDeplacement*(1 - (vitesseBonus/100));
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