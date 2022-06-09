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

	private static int compteur = 0;
	private IntegerProperty pvProperty;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int vitesseDeplacement;
	private int id;
	private Environnement environnement;
	private Inventaire inventaire;
	private int hauteurSaut;
	private int hauteurMaxSaut;
	private int[] taille;
	private Checkpoint checkpoint;
	private boolean saut;
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement,Inventaire inventaire, int hauteurSaut, int[] taille, int longueurSaut, Checkpoint checkpoint){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = inventaire;
		this.hauteurSaut = hauteurSaut;
		this.taille = taille;
		this.hauteurMaxSaut = this.hauteurSaut;
		this.environnement.ajouter(this);
		this.checkpoint = checkpoint;
		this.id=compteur;
		compteur++;
	}

	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement, int[] taille){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = new Inventaire(20);
		this.hauteurSaut = 0;
		this.hauteurMaxSaut = this.hauteurSaut;
		this.taille = taille;
		this.environnement.ajouter(this);
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
		for (int i=0; i<val; i++)
			if(this.toucheY(false)) 
				translationY(-1);

	}
	
	/**
	 * Permet de savoir si on ne touche rien au niveau des y au dessus du perso si auDessus est true est en dessous sinon 
	 * 
	 * 
	 * @param auDessus si il est vrai on vérifie les collision audessus du Sprite sinon en dessous
	 * @return 	true si il peut passer (pas de colission, touche un arbre, touche une plante) 
	 * 			false si il ne peut pas passer (si il touche un autre bloc)
	 */
	public boolean toucheY(boolean auDessus) {
		boolean gaucheTuile=true;
		boolean droiteSprite;
		Ressource blocAEmplacement;
		int i=0;
		if(auDessus) {
			while (i<taille[0] && gaucheTuile) {
				blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+32*i+2, this.getY()-32);
				gaucheTuile = ( blocAEmplacement == null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
				i++;
			}
			blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+32*taille[0]-2, this.getY()-32);
			droiteSprite = (blocAEmplacement ==null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
		}
		else {
			while (i<taille[0] && gaucheTuile) {
				blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+32*i+2, this.getY()+64);
				gaucheTuile = ( blocAEmplacement == null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
				i++;
			}
			blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+32*taille[0]-2, this.getY()+64);
			droiteSprite = (blocAEmplacement ==null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
		}
		return (gaucheTuile && droiteSprite);
	}

	/**
	 * Sauter permet uniquement de sauter de la hauteur du saut du personnage.
	 * Et est donc différent de monter car un perso pourrait être projeter par une attaque
	 * a une valeur plus haute/basse que celle de son saut.
	 */
	public void sauter() {
		saut = true;
		this.monter(this.hauteurSaut);
		hauteurSaut++;
		if (hauteurSaut==12) {
			this.saut = false;
			hauteurSaut = hauteurMaxSaut;
		}
	}
	
	/**
	 * Permet de faire un saut en fonction du paramètre d'entrée direction
	 * @param direction : true pour droite, false pour gauche
	 */
	public void sauter(boolean direction) { // a finir
		this.monter(hauteurSaut);
		int i = 0;
		if(direction) {
			while(i < this.hauteurMaxSaut) {
				this.translationX(-1); // TODO sleep
				i++;
			}
		}
		else {
			while(i < this.hauteurMaxSaut) {
				this.translationX(1);
				i++;
			}
		}
		this.descendre(hauteurSaut);
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
	
	/**
	 * Permet de savoir si on ne touche rien au niveau des x à droite du perso si aDroite est true est à gauche sinon 
	 * 
	 * @param aDroite si il est vrai on vérifie les collision à droite du Sprite sinon à gauche
	 * @return 	true si il peut passer (pas de colission, touche un arbre, touche une plante) 
	 * 			false si il ne peut pas passer (si il touche un autre bloc)
	 */
	private boolean toucheX(boolean aDroite) {
		boolean hautTuileTouchePas = true;
		boolean basTuileTouchePas;
		Ressource blocAEmplacement;
		int i = 0;
		if(aDroite) {
			while (i<taille[1] && hautTuileTouchePas) {
				blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+33, this.getY()+32*i);
				hautTuileTouchePas = (blocAEmplacement == null || blocAEmplacement instanceof Bois|| blocAEmplacement instanceof Plante);
				i++;
			}
			blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+33, this.getY()+32*taille[1]-1);
			basTuileTouchePas = (blocAEmplacement == null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
		}
		else {
			while (i<taille[1] && hautTuileTouchePas) {
				blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()-1, this.getY()+32*i);
				hautTuileTouchePas = (blocAEmplacement == null || blocAEmplacement instanceof Bois|| blocAEmplacement instanceof Plante);
				i++;
			}
			blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()-1, this.getY()+32*taille[1]-1);
			basTuileTouchePas = (blocAEmplacement == null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
		}
		return (hautTuileTouchePas && basTuileTouchePas);
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
		this.descendre(2);
		if (this.saut)
			sauter();			
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
		return this.hauteurMaxSaut;
	}
	
	public final void setLongueurSaut(int val) {
		this.hauteurMaxSaut = val;
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
	
	public void meurt() {
		this.setX(-32);
		this.setY(-32);
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
		Joueur joueur = this.getEnvironnement().getJoueur();
		return this.getX() - val <= joueur.getX()  && this.getX() >= joueur.getX() || this.getX() + val >= joueur.getX()  && this.getX() <= joueur.getX();
	}

	public int getId() {
		return id;
	}
}