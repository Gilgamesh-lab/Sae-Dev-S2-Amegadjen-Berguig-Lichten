package application.modele;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur extends Personnage {
	
	private final static  int MAX_PV = 7;
	private IntegerProperty faimProperty;
	private final  int MAX_FAIM = 7;
	private Item objetEquiper;
	private Inventaire inventaireRaccourci;
	private Checkpoint checkpoint;
	private final Poing POING =  new Poing();
	private final int VITESSE_COURRIR = 20;
	private final int VITESSE_MARCHE = 10;
	private BooleanProperty estAccroupiProperty;
	private final int VITESSE_ACCROUPIE = 5;
	
	
	public Joueur(int pv,int x, int y,
	Environnement carte, int faim, Inventaire inventaire,
	Item objetEquiper, Inventaire inventaireRaccourci, int saut, Statut statut) {
		super(pv, x, y,5, carte,inventaire, saut);
		this.faimProperty = new SimpleIntegerProperty(faim);
		this.objetEquiper = objetEquiper;
		this.inventaireRaccourci = inventaireRaccourci;
		this.estAccroupiProperty = new SimpleBooleanProperty(false);
	}

	public Joueur(int x, int y, Environnement environnement) {
		super(MAX_PV, x, y, 5, environnement );
		this.faimProperty = new SimpleIntegerProperty(MAX_FAIM);
		this.objetEquiper = this.POING;
		this.inventaireRaccourci = new Inventaire(10);
		this.estAccroupiProperty = new SimpleBooleanProperty(false);
	}
	
	public Checkpoint getCheckpoint() {
		return this.checkpoint;
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
	
	public final boolean getEstAccroupi() {
		return this.estAccroupiProperty.getValue();
	}
	
	public final void setEstAccroupi(Boolean val) {
		this.estAccroupiProperty.setValue(val);
	}
	
	public final BooleanProperty estAccroupiProperty() {
		return this.estAccroupiProperty;
	}
	
	public void manger() {
		this.setFaim(this.getFaim() + 1 );
	}
	
	public void manger(int nourriture) {
		for (int i = 0; this.getFaim() < this.MAX_FAIM & i < nourriture ; i++) {
			this.manger();
		}
		/* this.setFaim(this.getFaim() + nourriture);
		if(this.getFaim() > this.MAX_FAIM) {
			this.setFaim(MAX_FAIM); */ 
	}
	
	public void appetit() {
		this.setFaim(this.getFaim() - 1 );
	}
	
	public Inventaire getInventaireRaccourci() {
		return this.inventaireRaccourci;
	}
	
	
	public void remplacerObjetRaccourci(int indice, Item item) {
		this.inventaireRaccourci.remplacer(item, indice);
	}
	
	public void equiper(Item item) {
		this.objetEquiper = item;
	}
	
	public void desequiper() {
		this.objetEquiper = this.POING;
	}

	public void marcher() {
		super.setVitesseDeplacement(VITESSE_MARCHE);
	}
	
	public void courrir() {
		super.setVitesseDeplacement(VITESSE_COURRIR);
	}
	
	public void accroupie() {
		super.setVitesseDeplacement(VITESSE_ACCROUPIE);
	}
	
	public void attaquer() {
		if (!((Arme)this.objetEquiper).estEnRecharge()) {
			// attaque à coder
			((Arme)this.objetEquiper).enRecharge();
			
		}
	}
	
	public void gauche(int nbPixel) {
		super.setX(super.getX()-nbPixel);
	}
	
	public void saut(int hauteur) {
		super.setY(super.getY()-hauteur);

	public void droite(int nbPixel) {
		this.gauche(-nbPixel);

  }
}
