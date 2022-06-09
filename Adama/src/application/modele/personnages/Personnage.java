package application.modele.personnages;

import java.io.IOException;

import application.modele.Checkpoint;
import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.ressources.Bois;
import application.modele.ressources.Plante;
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
	private int longueurSaut;
	private int[] taille;
	private Checkpoint checkpoint;
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement,Inventaire inventaire, int hauteurSaut, int[] taille, int longueurSaut, Checkpoint checkpoint){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = inventaire;
		this.hauteurSaut = hauteurSaut;
		this.taille = taille;
		this.longueurSaut = longueurSaut;
		this.environnement.ajouterPersonnage(this);
		this.checkpoint = checkpoint;
	}

	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = new Inventaire(20);
		this.hauteurSaut = 64;
		this.longueurSaut = 5;
		//this.taille[0] = 1;
		//this.taille[1] = 1;
		this.environnement.ajouterPersonnage(this);
		this.checkpoint = new Checkpoint(x,y,environnement);
	}
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement, int[] taille){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = new Inventaire(20);
		this.hauteurSaut = 1;
		this.longueurSaut = 5;
		this.taille = taille;
		this.environnement.ajouterPersonnage(this);
		this.checkpoint = new Checkpoint(x,y,environnement);
	}
	
	
	
	/**
	 * Diminue les PV
	 * @param degat nombre de pv perdue
	 */
	public void degat() {
		this.setPv(this.getPv() + 1);
	}
	
	public void decrementerPv(int degat) {
		for (int i = 0; this.getPv() > 0 && i < degat ; i++) {
			this.degat();
		}
	}
	
	public Checkpoint getCheckpoint() {
		return this.checkpoint;
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
			|| this.environnement.getCarte().emplacement(this.getX()+1, this.getY()+64) instanceof Plante); // faire varier  64 , 32 en fonction taille
			
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
	
	public void descendre() {
		this.monter(-this.hauteurSaut);
	}
	
	/**
	 * Permet de faire un saut en fonction du paramètre d'entrée direction
	 * @param direction : true pour droite, false pour gauche
	 * @throws IOException
	 */
	public void sauter(boolean direction) throws IOException { // a finir
		this.monter(this.hauteurSaut);
		int i = 0;
		while(i < this.hauteurSaut) {
			this.translationY(1); // TODO sleep
			i++;
		}
	

	}

	
	public void translationX(int val) {
		if(toucheX(true)) {
			this.xProperty.setValue(this.getX() - val);
		}
	}

	public void droite() {
		if(toucheX(true))
			this.translationX(-vitesseDeplacement);
	}

	public void gauche() {
		if(toucheX(false))
			this.translationX(vitesseDeplacement);
	}

	public boolean toucheX(boolean aDroite) {
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

	public void perdreRessources() throws ErreurInventairePlein { // Lorsque mort perd ses ressources
		for(int i = 0 ; i < this.inventaire.getTaille(); i++) {
			if(this.inventaire.getItem(i) instanceof Ressource) {
				this.getEnvironnement().getCarte().getItems().transferer(this.inventaire.getItem(i), this.inventaire);
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
		this.hauteurSaut = val;
	}
	
	public final int getLongueurSaut() {
		return this.longueurSaut;
	}
	
	public final void setLongueurSaut(int val) {
		this.longueurSaut = val;
	}
	
	public boolean estEnDehorsMap() {
		return this.getX() < 0 || this.getY() > 0;
	}
	
	
	public boolean estEnLaire() throws IOException {
		int[] taille = {1,2};//provisoire
		return this.environnement.getCarte().emplacement(this.getX(), this.getY(), taille)==null;
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
	
	public void meurt() throws ErreurInventairePlein {
		this.setX(-320);
		this.setY(-320);
		if(!this.estMort()) {
			this.setPv(0);
		}
		this.perdreRessources();
		
	}
	
	
	/**
	 * Vérifie si le joueur se trouve à droite ou à gauche du personnage
	 * @return Retourne true si le joueur se trouve à la droite du personnage false sinon
	 * @throws ErreurObjetIntrouvable Survient si aucune instance de la classe Joueur est présente dans Environnement.personnages
	 */
	public boolean ouSeTrouveLeJoueur() throws ErreurObjetIntrouvable { // peut-être à mettre dans Personnage
		return this.getEnvironnement().getJoueur().getX() > this.getX();
	}
	
	/**
	 * Vérifie si le joueur se trouve dans un rayon correspondant à la taille du saut en  blocs autour du personnage  
	 * @return Retourne true si le joueur est à porter du personnage , false sinon
	 * @throws ErreurObjetIntrouvable Survient si aucune instance de la classe Joueur est présente dans la carte 
	 */
	public boolean estAporterDuJoueur() throws ErreurObjetIntrouvable { // peut-être à mettre dans Personnage
		Joueur joueur = this.getEnvironnement().getJoueur();
		return this.getX() - this.getLongueurSaut() <= joueur.getX()  && this.getX() >= joueur.getX() || this.getX() + this.getLongueurSaut() >= joueur.getX()  && this.getX() <= joueur.getX();
	}
	
	/**
	 * Vérifie si le joueur se trouve dans un rayon de val blocs autour du personnage, val étant la varaiable passé en paramètre
	 * @param val : valeur correspondant au rayon du cercle qui définit si le joueur est à portee
	 * @return Retourne true si le joueur est près du personnage , false sinon
	 * @throws ErreurObjetIntrouvable Survient si aucune instance de la classe Joueur est présente dans la carte
	 */
	public boolean estPrèsDuJoueur(int val) throws ErreurObjetIntrouvable { // peut-être à mettre dans Personnage
		Joueur joueur = this.getEnvironnement().getJoueur(); // faire abscisse 
		return this.getX() - val <= joueur.getX()  && this.getX() >= joueur.getX() || this.getX() + val >= joueur.getX()  && this.getX() <= joueur.getX();
	}
	
	
	public boolean estSurLeJoueur() throws ErreurObjetIntrouvable { // peut-être à mettre dans Personnage
		return this.getEnvironnement().getJoueur().getX() + 16 == this.getX() && this.getEnvironnement().getJoueur().getY()  == this.getY() ||
				this.getEnvironnement().getJoueur().getX() - 16 == this.getX() && this.getEnvironnement().getJoueur().getY()  == this.getY() ||
				this.getEnvironnement().getJoueur().getX() + 16 == this.getX() && this.getEnvironnement().getJoueur().getY() + 16 == this.getY() ||
				this.getEnvironnement().getJoueur().getX() + 16 == this.getX() && this.getEnvironnement().getJoueur().getY() -16  == this.getY() ||
				this.getEnvironnement().getJoueur().getX()  == this.getX() && this.getEnvironnement().getJoueur().getY() + 16 == this.getY() ||
				this.getEnvironnement().getJoueur().getX()  == this.getX() && this.getEnvironnement().getJoueur().getY() -16  == this.getY();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}