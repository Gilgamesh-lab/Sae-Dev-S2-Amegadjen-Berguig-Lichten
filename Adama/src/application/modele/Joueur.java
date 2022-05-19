package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur extends Personnage {

	private final static int MAX_PV = 7;
	private IntegerProperty faimProperty;
	private final static int MAX_FAIM = 7;
	private Item objetEquiper;
	private Inventaire inventaireRaccourci;



	public Joueur(int pv,int x, int y,
			Environnement carte, int faim, Inventaire inventaire,
			Item objetEquiper, Inventaire inventaireRaccourci) {
		super(pv, x, y,5, carte,inventaire);
		this.faimProperty = new SimpleIntegerProperty(faim);
		this.objetEquiper = objetEquiper;
		this.inventaireRaccourci = inventaireRaccourci;
	}

	public Joueur(int x, int y,
			Environnement carte) {
		super(MAX_PV, x, y,5, carte);
		this.faimProperty = new SimpleIntegerProperty(MAX_FAIM);
		this.objetEquiper = null;
		this.inventaireRaccourci = new Inventaire(10);
	}

	public final int getFaim() {
		return this.faimProperty.getValue();
	}

	public final void setFaim(int val) {
		this.faimProperty.setValue(val);
	}

	public final IntegerProperty faimProperty() {
		return this.faimProperty;
	}

	public void remplacerObjetRaccourci(int indice, Item item) {
		this.inventaireRaccourci.remplacer(item, indice);
	}

	public void equiper(Item item) {
		this.objetEquiper = item;
	}

	public void desequiper() {
		// Arme main =  new Poing();
		// this.objetEquiper = main;
	}

	public void gauche(int vitesse) {
		super.setX(super.getX()-vitesse);
	}

	public void droite(int vitesse) {
		this.gauche(-vitesse);
	}
	
	public void saut(int hauteur) {
		super.setY(super.getY()+hauteur);
	}

}