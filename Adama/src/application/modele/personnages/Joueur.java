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
import application.modele.effet.Effet;
import application.modele.effet.Ralentir;
import application.modele.exception.ErreurArmeEtOutilPasJetable;
import application.modele.exception.ErreurInventairePlein;
import application.modele.outils.Seau;
import application.modele.potions.AntiPoison;
import application.modele.potions.Potion;
import application.modele.potions.PotionDegat;
import application.modele.potions.PotionVie;
import application.modele.potions.PotionVitesse;
import application.modele.ressources.Ressource;
import application.modele.ressources.Terre;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Personnage {

	private final static IntegerProperty MAX_PV_PROPERTY = new SimpleIntegerProperty(7);
	private IntegerProperty faimProperty;
	private final  int MAX_FAIM = 7;
	private Item objetEquiper;
	private Inventaire inventaireRaccourci;
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
		super.setVitesseDeplacement(VITESSE_COURRIR);
	}

	
	public void utiliserMain(int emplacement) throws ErreurInventairePlein {
		if (objetEquiper instanceof Potion) {
			String potion = objetEquiper.getClass().getSimpleName();
			switch (potion) {
			case "PotionVie":
				incrementerPv(PotionVie.getNombrePvRestaurer());
				break;
			case "PotionVitesse":
//				super.Deplacement(objetEquiper.getDuree());
				break;
			case "AntiPoison":
				super.SupprimerEffet(0);
			default:
				break;
			}
		}
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
		String planteDeNike = "PlanteDeNike";
		String planteHercule = "¨PlanteHercule";
		String planteMedicinale = "PlanteMedicinale";
		String fils = "Fils";
		String antiPoison = "AntiPoison";
		Seau PossedeSeau = null;
		String seau = null;
		int indiceSaut = items.indexOf(PossedeSeau);
		if(indiceSaut != -1);
			PossedeSeau = (application.modele.outils.Seau) super.getInventaire().getItems().get(indiceSaut);
			if(PossedeSeau.EstRempli())
				seau = "Seau";

		Item item;
		Map<String, Integer> recette = new HashMap<String, Integer>();

		recette.put(pierre, 0);
		recette.put(bois, 0);
		recette.put(planteDeNike, 0);
		recette.put(planteMedicinale, 0);
		recette.put(planteHercule, 0);
		recette.put(antiPoison, 0);
		int k = 0;
		try {
			for(k = 0 ; k < items.size() ; k ++) {
				recette.put(items.get(k).getClass().getSimpleName(), recette.get(items.get(k).getClass().getSimpleName()) + 1);
			}
		}catch (java.lang.NullPointerException e) {
			throw new ErreurObjetInvalide(items.get(k));
		}

		if(recette.get(bois) == 2 && recette.get(pierre) == 1) // 2 bois et 1 pierre crée une épée
			item = new Epee();


		else if(recette.get(bois) == 1 && recette.get(pierre) == 1) // 1 de bois et 1 pierre crée une flèche
			item = new Fleche();
		else if(recette.get(bois) == 3 && recette.get(fils) == 1) 
			item = new Arc(super.getInventaire());
		

		else if(recette.get(bois) == 5)
			return new Seau(getEnvironnement());

		else if (seau != null && recette.get(planteDeNike) == 2)
			return new PotionVitesse();
		else if (seau != null && recette.get(planteHercule) == 2)
			return new PotionDegat();

		else if (seau != null && recette.get(planteMedicinale) == 3)
			return new PotionVie();
		else if (seau != null && recette.get(planteMedicinale) == 2 && recette.get(antiPoison) == 1)
			return new AntiPoison();
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

	public void incrementerPv(int nombrePvRestaurer) {
		for (int i = 0; this.getPv() < Joueur.getMaxPv() && i < nombrePvRestaurer ; i++) {
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
		return MAX_PV_PROPERTY;
	}


	public void teleporterToCheckpoint() {
		this.setX(this.getCheckpoint().getX());
		this.setY(this.getCheckpoint().getY());
	}

	public  void agir() throws ErreurObjetIntrouvable{

	}



	public static int getMaxPv() {
		return MAX_PV_PROPERTY.getValue();
	}

	public Item getObjetEquiper() {
		return objetEquiper;
	}












}
