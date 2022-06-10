package application.modele.personnages;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import application.modele.exception.ErreurPasDobjetCraftable;
import application.modele.Carte;
import application.modele.Checkpoint;
import application.modele.Inventaire;
import application.modele.armes.Poing;
import application.modele.Item;
import application.modele.Environnement;
import application.modele.exception.ErreurArmeEtOutilPasJetable;
import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.exception.ErreurObjetInvalide;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import application.modele.ressources.Ressource;
import application.modele.ressources.Terre;
import application.modele.armes.Arme;
import application.modele.armes.Epee;
import application.modele.armes.Arc;
import application.modele.armes.Fleche;

public class Joueur extends Personnage {

	private final static IntegerProperty MAX_PV = new SimpleIntegerProperty(7);
	private IntegerProperty faimProperty;
	private final  int MAX_FAIM = 7;
	private Item objetEquiper;
	private Inventaire inventaireRaccourci;
	private Checkpoint checkpoint;
	private final Poing POING =  new Poing();
	private final static int VITESSE_COURRIR = 20;
	private final static int VITESSE_MARCHE = 10;
	private final static int VITESSE_ACCROUPIE = 5;
	private BooleanProperty estAccroupiProperty;
	private final static int[] TAILLE = {1,2};



	public Joueur(int pv,int x, int y,
			Environnement environnement, int faim, Inventaire inventaire,
			Item objetEquiper, Inventaire inventaireRaccourci, int hauteurSaut, int longueurSaut, Checkpoint checkpoint) {
		super(pv, x, y,5, environnement,inventaire, hauteurSaut, TAILLE, longueurSaut, checkpoint);
		this.faimProperty = new SimpleIntegerProperty(faim);
		this.objetEquiper = objetEquiper;
		this.inventaireRaccourci = inventaireRaccourci;
		this.estAccroupiProperty = new SimpleBooleanProperty(false);
	}

	public Joueur(int x, int y, Environnement environnement) {
		super(Joueur.getMaxPv(), x, y, 5, environnement, TAILLE );
		this.faimProperty = new SimpleIntegerProperty(MAX_FAIM);
		this.objetEquiper = this.POING;
		this.inventaireRaccourci = new Inventaire(10);
		this.estAccroupiProperty = new SimpleBooleanProperty(false);
	}

	public Checkpoint getCheckpoint() {
		return this.checkpoint;
	}

	public void setCheckpoint(Checkpoint checkpoint) {
		this.checkpoint = checkpoint;
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

	public final void setEstAccroupi() {
		this.estAccroupiProperty.setValue(!this.getEstAccroupi());
	}

	public final BooleanProperty estAccroupiProperty() {
		return this.estAccroupiProperty;
	}

	public void incrementerNourriture() {
		this.setFaim(this.getFaim() + 1 );
	}

	public void manger(int nourriture) {
		for (int i = 0; this.getFaim() < this.MAX_FAIM && i < nourriture ; i++) {
			this.incrementerNourriture();
		}
	}

	public void decrementerNourriture() {
		this.setFaim(this.getFaim() - 1 );
	}

	public Inventaire getInventaireRaccourci() {
		return this.inventaireRaccourci;
	}


	public void remplacerObjetRaccourci(int indice, Item item) {
		this.inventaireRaccourci.remplacer(item, indice);
	}

	public void ajouterObjetRaccourci(Item item) throws ErreurInventairePlein {
		this.inventaireRaccourci.ajouter(item);
	}

	public void equiper(Item item) {
		this.objetEquiper = item;
	}

	public void desequiper() {
		this.objetEquiper = this.POING;
	}

	public void marcher() {
		this.setVitesseDeplacement(VITESSE_MARCHE);
	}


	public void courrir() {
		this.setVitesseDeplacement(VITESSE_COURRIR);
	}

	public void accroupie() {
		super.setVitesseDeplacement(VITESSE_ACCROUPIE);
		this.setEstAccroupi(true);
	}

	public void setModeDeplacement() { // TODO trouver un meilleure nom
		if(this.getVitesseDeplacement() != VITESSE_COURRIR) {
			this.setVitesseDeplacement(VITESSE_COURRIR);
		}
		else {
			this.setVitesseDeplacement(VITESSE_MARCHE);
			if(this.getEstAccroupi()) {
				this.setEstAccroupi();
			}
		}
	}

	public void utiliserMain(int emplacement) throws ErreurInventairePlein {
		Ressource bloc =this.objetEquiper.utiliser(emplacement);
		if (objetEquiper instanceof Terre) {
			Carte carte = this.getEnvironnement().getCarte();
			if(carte.getBlockMap().get(emplacement)== null) {
				carte.getBlockMap().remove(emplacement);
				carte.getBlockMap().add(emplacement, (Terre)this.objetEquiper);
				//			carte.getBlockMap().set(emplacement, (Terre)this.objetEquiper);
			}
		}
		if (bloc!=null)
			this.getInventaire().ajouter(bloc);
	}

	public boolean estUneArmeOuUnOutil(Item item) {
		return item instanceof Arme || item instanceof Ressource;
	}

	public void attaquer() throws ErreurObjetInvalide {
		if(this.objetEquiper instanceof Arme) {
			if (!((Arme)this.objetEquiper).estEnRecharge()) {
				((Arme)this.objetEquiper).attaquer();
				((Arme)this.objetEquiper).enRecharge();
			}
		}
		else {
			throw new ErreurObjetInvalide(this.objetEquiper);
		}
	}



	public Item craft (ArrayList<Item> items) throws ErreurPasDobjetCraftable, ErreurObjetInvalide {
		String pierre = "Pierre";
		String bois = "Bois";
		String plante = "Plante";

		Item item;
		Map<String, Integer> recette = new HashMap<String, Integer>();

		recette.put(pierre, 0);
		recette.put(bois, 0);
		recette.put(plante, 0);

		int k = 0;

		try {
			for(k = 0 ; k < items.size() ; k ++) {
				recette.put(items.get(k).getClass().getSimpleName(), recette.get(items.get(k).getClass().getSimpleName()) + 1);
			}
		}catch (java.lang.NullPointerException e) {
			throw new ErreurObjetInvalide(items.get(k));
		}

		if(recette.get(bois) == 3 && recette.get(plante) == 1) {  // 3 bois et 1 plante crée un arc
			item = new Arc(this.getInventaire()); // TODO revoir recette pour l'arc (remplacer la plante part de la ficelle)
		}

		else if(recette.get(bois) == 2 && recette.get(pierre) == 1) { // 2 bois et 1 pierre crée une épée
			item = new Epee();
		}


		else if(recette.get(bois) == 1 && recette.get(pierre) == 1) { // 1 de bois et 1 pierre crée une flèche
			item = new Fleche();
		}

		else {
			throw new ErreurPasDobjetCraftable();
		}

		return item;
	}

	public void afficherMap(Map<String, Integer> maps) {
		for (String ressource : maps.keySet()) {
			System.out.println(ressource + " = " + maps.get(ressource));
		}
	}

	public void jeter(Item item) throws ErreurArmeEtOutilPasJetable {
		if(!this.estUneArmeOuUnOutil(item)) {

			this.getInventaire().supprimer(item);;
		}
		else {
			throw new ErreurArmeEtOutilPasJetable();
		}

	}

	public void soin() {
		this.setPv(this.getPv() + 1);
	}

	public void incrementerPv(int nourriture) {
		for (int i = 0; this.getPv() < Joueur.getMaxPv() && i < nourriture ; i++) {
			this.soin();
		}
	}

	public void poser(boolean direction) throws ErreurInventairePlein {
		if(direction) {
			((Ressource) this.objetEquiper).setX(this.getX() + 32);
			((Ressource) this.objetEquiper).setY(this.getY());
		}
		else {
			((Ressource) this.objetEquiper).setX(this.getX() - 32);
			((Ressource) this.objetEquiper).setY(this.getY());
		}
		this.getInventaire().supprimer(this.objetEquiper);
//		this.getEnvironnement().getCarte().getItems().ajouter(this.objetEquiper);
		this.desequiper();
	}

	public static IntegerProperty maxPvProperty() {
		return MAX_PV;
	}

	public void teleporterToCheckpoint() {
		this.setX(this.getCheckpoint().getX());
		this.setY(this.getCheckpoint().getY());
	}

	public  void agir() throws ErreurObjetIntrouvable{

	}


	public static int getMaxPv() {
		return MAX_PV.getValue();
	}

	public Item getObjetEquiper() {
		return objetEquiper;
	}
	
}
