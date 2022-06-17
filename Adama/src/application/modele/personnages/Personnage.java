package application.modele.personnages;

import application.modele.Checkpoint;
import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.effet.Effet;
import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.Carte;
import application.modele.ressources.Bois;
import application.modele.ressources.Plante;
import application.modele.ressources.Ressource;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
	private ObservableList<Effet> effets;
	private boolean direction; //true = droite || gauche = false

	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement,Inventaire inventaire, int hauteurSaut, int[] taille, Checkpoint checkpoint){
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
		this.id= compteur;
		compteur++;
		this.direction=false;
	}

	
	
	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement, int[] taille){
		this.pvProperty = new SimpleIntegerProperty(pv);
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.vitesseDeplacement = vitesseDeplacement;
		this.environnement = environnement;
		this.inventaire = new Inventaire(20);
		this.hauteurSaut = 11;
		this.hauteurMaxSaut = this.hauteurSaut;
		this.taille = taille;
		this.environnement.ajouter(this);
		this.checkpoint = new Checkpoint(x,y,environnement);

		this.id= compteur;
		compteur++;
		this.direction=false;
	}

	public Personnage(int pv, int x, int y, int vitesseDeplacement, Environnement environnement,
			Inventaire inventaire, int hauteurSaut, int[] taille) {
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
		this.id= compteur;
		compteur++;
		this.direction=false;
	}



	public void setCheckpoint(Checkpoint checkpoint) {
		this.checkpoint = checkpoint;
	}

	public boolean getDirection() {
		return this.direction;
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
	public void degat() {
		this.setPv(this.getPv() - 1);
	}

	public void decrementerPv(int degat) throws ErreurInventairePlein {
		for (int i = 0; this.getPv() > 0 && i < degat ; i++) {
			this.degat();
		}
		if(this.estMort()) {
			this.meurt();
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
		if(this.touchePasY(true))
			translationY(val);
	}

	/**
	 * Permet d'effectuer une translationY de -val si toucheY est true
	 * @param val
	 */
	public void descendre(int val) {
		for (int i=0; i<val; i++)
			if(this.touchePasY(false))
				translationY(-1);

	}

	/**
	 * Permet de savoir si on ne touche rien au niveau des y au dessus du perso si auDessus est true est en dessous sinon
	 *
	 *
	 * @param auDessus si il est vrai on vérifie les collision audessus du personnage sinon en dessous
	 * @return 	true si il peut passer (pas de colission, touche un arbre, touche une plante)
	 * 			false si il ne peut pas passer (si il touche un autre bloc)
	 */
	public boolean touchePasY(boolean auDessus) {
		boolean gaucheTuile=true;
		boolean droiteSprite;
		Ressource blocAEmplacement;
		int i=0;
		if(auDessus) {
			while (i<taille[0] && gaucheTuile) {
				blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+Carte.TAILLE_BLOCK*i+1, this.getY()-Carte.TAILLE_BLOCK*taille[1]);
				gaucheTuile = ( blocAEmplacement == null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
				i++;
			}
			blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+Carte.TAILLE_BLOCK*taille[0]-1, this.getY()-Carte.TAILLE_BLOCK*taille[1]);
			droiteSprite = (blocAEmplacement ==null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
		}
		else {
			while (i<taille[0] && gaucheTuile) {
				blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+Carte.TAILLE_BLOCK*i+1, this.getY()+Carte.TAILLE_BLOCK*taille[1]);
				gaucheTuile = ( blocAEmplacement == null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
				i++;
			}
			blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+Carte.TAILLE_BLOCK*taille[0]-1, this.getY()+Carte.TAILLE_BLOCK*taille[1]);
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
		hauteurSaut--;
		if (hauteurSaut==0) {
			this.saut = false;
			hauteurSaut = hauteurMaxSaut;
		}
	}

	public void descendre() {
		this.monter(-this.hauteurSaut);
	}

	/**
	 * Permet de faire un saut en fonction du paramètre d'entrée direction
	 * @param direction : true pour droite, false pour gauche
	 */
	public void sauterEnDirection(boolean direction) { //TODO a finir saut en direction
		this.sauter();
		if(direction) {
			this.droite();
		}
		else {
			this.gauche();
		}
	}

	public void translationX(int val) {
		this.xProperty.setValue(this.getX() - val);
	}

	public void droite() {
		if(touchePasX(true) && !this.estEnDehorsMap(vitesseDeplacement))
			this.translationX(-vitesseDeplacement);
		this.direction=true;
	}

	public void gauche() {
		if(touchePasX(false) && !this.estEnDehorsMap(-vitesseDeplacement)) {
			this.translationX(vitesseDeplacement);
		}

		this.direction=false;
	}
	
	public void droitePousse(int pousseDe) {
		if(touchePasX(true))
			this.translationX(-pousseDe);
		this.direction=false;
	}

	public void gauchePousse(int pousseDe) {
		if(touchePasX(false))
			this.translationX(pousseDe);
		this.direction=true;
	}

	/**
	 * Permet de savoir si on ne touche rien au niveau des x à droite du perso si aDroite est true est à gauche sinon
	 *
	 * @param aDroite si il est vrai on vérifie les collision à droite du personnage sinon à gauche
	 * @return 	true si il peut passer (pas de colission, touche un arbre, touche une plante)
	 * 			false si il ne peut pas passer (si il touche un autre bloc)
	 */
	public boolean touchePasX(boolean aDroite) {
		boolean hautTuileTouchePas = true;
		boolean basTuileTouchePas;
		Ressource blocAEmplacement;
		int i = 0;
		if(aDroite) {
			while (i<taille[1] && hautTuileTouchePas) {
				blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+Carte.TAILLE_BLOCK+1, this.getY()+Carte.TAILLE_BLOCK*i);
				hautTuileTouchePas = (blocAEmplacement == null || blocAEmplacement instanceof Bois|| blocAEmplacement instanceof Plante);
				i++;
			}
			blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()+Carte.TAILLE_BLOCK+1, this.getY()+Carte.TAILLE_BLOCK*taille[1]-1);
			basTuileTouchePas = (blocAEmplacement == null || blocAEmplacement instanceof Bois || blocAEmplacement instanceof Plante);
		}
		else {
			while (i<taille[1] && hautTuileTouchePas) {
				blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()-1, this.getY()+Carte.TAILLE_BLOCK*i);
				hautTuileTouchePas = (blocAEmplacement == null || blocAEmplacement instanceof Bois|| blocAEmplacement instanceof Plante);
				i++;
			}
			blocAEmplacement = this.environnement.getCarte().emplacement(this.getX()-1, this.getY()+Carte.TAILLE_BLOCK*taille[1]-1);
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
				this.inventaire.supprimer(i);
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

	public final int getHauteurMaxSaut() {
		return this.hauteurMaxSaut;
	}

	public final void setHauteurMaxSaut(int val) {
		this.hauteurMaxSaut = val;
	}

	
	public boolean estEnDehorsMap(int valX) {
		return this.getX() + valX <= 0 || this.getX() + valX >= Carte.TAILLE_BLOCK * Carte.LARGEUR ;
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
	

	public boolean getSaut() {
		return saut;
	}

	public void setSaut(boolean saut) {
		this.saut = saut;
	}

	public void meurt() throws ErreurInventairePlein {
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
	public boolean ouSeTrouveLeJoueur()  { // peut-être à mettre dans Personnage
		return this.getEnvironnement().getJoueur().getX() > this.getX();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Vérifie si le joueur se trouve dans un rayon de val blocs autour du personnage, val étant la varaiable passé en paramètre
	 * @param val : valeur correspondant au rayon du cercle qui définit si le joueur est à portee
	 * @param
	 * @return Retourne true si le joueur est près du personnage , false sinon
	 */
	
	public boolean estPrèsDuJoueur(int valX, int valY) throws ErreurObjetIntrouvable { // peut-être à mettre dans Personnage
		Joueur joueur = this.getEnvironnement().getJoueur(); // TODO a commenter, a debugger
		if(this.getX() - valX <= joueur.getX()  && this.getX() >= joueur.getX()) { // Si le joueur se trouve dans un rayon de valX derriere le personnage
			
			if (this.getY() == joueur.getY() ) {
				return true;
			}
			
			else if((this.getY() + valY  >= joueur.getY() && this.getY() <= joueur.getY())) {
				return true;
			}
			
			else if(this.getY() - valY <= joueur.getY()&& this.getY() >= joueur.getY()){
				return true;
			}
		}
		if (this.getX() + valX >= joueur.getX()  && this.getX() < joueur.getX()) { // Si le joueur se trouve dans un rayon de valX devant le personnage
			
			if(this.getY() == joueur.getY()) {
				return true;
			}
			
			else if(this.getY() - valY  <= joueur.getY()  && this.getY() >= joueur.getY()){
				return true;
			}
			
			else if(this.getY() + valY >= joueur.getY() && this.getY() <= joueur.getY()){
				return true;
			}
		}
		
		return  false;
	}


	public int getId() {
		return id;
	}

	public void SupprimerEffet(int i) {
		// TODO Auto-generated method stub

	}
}