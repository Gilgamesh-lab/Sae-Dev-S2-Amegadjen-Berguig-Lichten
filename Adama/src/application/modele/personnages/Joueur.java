package application.modele.personnages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import application.modele.Carte;
import application.modele.Checkpoint;
import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.Item;
import application.modele.armes.Arc;
import application.modele.armes.Arme;
import application.modele.armes.Epee;
import application.modele.armes.Poing;
import application.modele.effet.Effet;
import application.modele.effet.Ralentir;
import application.modele.exception.ErreurArmeEtOutilPasJetable;
import application.modele.exception.ErreurInventairePlein;
import application.modele.outils.Seau;
import application.modele.potions.Potion;
import application.modele.potions.PotionDegat;
import application.modele.potions.PotionVie;
import application.modele.ressources.Ressource;
import application.modele.ressources.Terre;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Personnage {

	private final static IntegerProperty MAX_PV_PROPERTY = new SimpleIntegerProperty(7);
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
			Environnement carte, int faim, Inventaire inventaire,
			Item objetEquiper, Inventaire inventaireRaccourci, int saut) {
		super(pv, x, y,5, carte,inventaire, saut, TAILLE);
		this.faimProperty = new SimpleIntegerProperty(faim);
		this.objetEquiper = objetEquiper;
		this.inventaireRaccourci = inventaireRaccourci;
		this.estAccroupiProperty = new SimpleBooleanProperty(false);
	}

	public Joueur(int x, int y,
			Environnement carte) {
		super(Joueur.getMaxPv(), x, y,5, carte, TAILLE);
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
		super.setVitesseDeplacement(VITESSE_MARCHE);
	}

	public void courrir() {
		super.setVitesseDeplacement(VITESSE_COURRIR);
	}

	public void utiliserMain(int emplacement) {
		this.objetEquiper.utiliser(emplacement);
		if (objetEquiper instanceof Terre) {
			Carte carte = this.getEnvironnement().getCarte();
			carte.getBlockMap().remove(emplacement);
			carte.getBlockMap().add(emplacement, (Terre)this.objetEquiper);
//			carte.getBlockMap().set(emplacement, (Terre)this.objetEquiper);
		}
		else if (objetEquiper instanceof Potion) {
			String potion = objetEquiper.getClass().getSimpleName();
			switch (potion) {
			case "PotionVie":
				incrementerPv(PotionVie.getNombrePvRestaurer());
				break;
			case "PotionVitesse":
//				super.Deplacement(objetEquiper.getDuree());
				break;
//			case "
			default:
				break;
			}
		}
	}
	
	
	public void accroupie() {
		super.setVitesseDeplacement(VITESSE_ACCROUPIE);
	}

	public boolean estUneArmeOuUnOutil(Item item) {
		return item instanceof Arme || item instanceof Ressource;
	}

	public void attaquer() {
		if (!((Arme)this.objetEquiper).estEnRecharge()) {
			// attaque à coder
			((Arme)this.objetEquiper).enRecharge();

		}
	}



	public Item craft (ArrayList<Item> items) { // à tester/ à finir
		//		String pierre = new Pierre().getClass().getSimpleName();
		//		String bois = new Bois().getClass().getSimpleName();
		//		String plante = new Plante().getClass().getSimpleName();
		String pierre = "Pierre";
		String bois = "Bois";
		String planteDeNike = "PlanteDeNike";
		String planteHercule = "¨PlanteHercule";
		String planteMedicinale = "PlanteMedicinale";
		String fils = "Fils";
		Seau PossedeSeau = null;
		String seau = null;
		int indiceSaut = items.indexOf(PossedeSeau);
		if(indiceSaut != -1);
			PossedeSeau = (application.modele.outils.Seau) super.getInventaire().getItems().get(indiceSaut);
			if(PossedeSeau.EstRempli())
				seau = "Seau";

		Map<String, Integer> recette = new HashMap<String, Integer>();

		recette.put(pierre, 0);
		recette.put(bois, 0);
		recette.put(planteDeNike, 0);


		for(int k = 0 ; k < items.size() ; k ++) {
			for (String ressource : recette.keySet()) {
				if(items.get(k).getClass().getName() == ressource) {
					recette.put(ressource, recette.get(ressource) + 1);
				}
			}
		}

		if(recette.get(bois) == 2 && recette.get(pierre) == 1) {
			return new Epee();
		}

		else if(recette.get(bois) == 3 && recette.get(fils) == 1) {
			return new Arc();
		}
		
		else if(recette.get(bois) == 5)
			return new Seau(getEnvironnement());
		
		else if (seau != null && recette.get(planteHercule) == 2)
			return new PotionDegat();
		
		else if (seau != null && recette.get(planteMedicinale) == 3)
			return new PotionVie();
		else {
			return new Poing();
		}
	}

	public void afficherMap(Map<String, Integer> maps) {
		for (String ressource : maps.keySet()) {
			System.out.println(ressource + " = " + maps.get(ressource));
		}
	}

	public void jeter(Item item) throws ErreurInventairePlein, ErreurArmeEtOutilPasJetable {
		if(!this.estUneArmeOuUnOutil(item)) {
			this.getInventaire().supprimer(item);
			this.getEnvironnement().getCarte().getItems().add(item);
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

	public void poser(boolean direction, Ressource ressource ) {
		if(direction) {
			ressource.setX(this.getX() + 32);
			ressource.setY(this.getY());
		}
		else {
			ressource.setX(this.getX() - 32);
			ressource.setY(this.getY());
		}
		this.getInventaire().supprimer(ressource);
		this.getEnvironnement().getCarte().getBlockMap().add(ressource);
	}

	public static int getMaxPv() {
		return MAX_PV_PROPERTY.getValue();
	}
	
	public static IntegerProperty MAX_PV_PROPERTY() {
		return MAX_PV_PROPERTY;
	}
}