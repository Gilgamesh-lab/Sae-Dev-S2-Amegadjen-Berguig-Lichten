package application.modele;

import java.io.IOException;

import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.exception.TailleMapException;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {
	
	private ObservableList<Personnage> personnages;
	private Carte carte;



	public Environnement(Carte carte) {
		this.carte = carte;
		this.personnages = FXCollections.observableArrayList();
	}

	public Environnement() throws TailleMapException, IOException {
		this.carte = new Carte();
		this.personnages = FXCollections.observableArrayList();
	}

	public void ajouter(Personnage personnage) {
		this.personnages.add(personnage);
	}
	
	public void supprimer(Personnage personnage) {
		this.personnages.remove(personnage);
	}
	
	public void supprimer(int indice) {
		this.personnages.remove(indice);
	}
	

	public Personnage emplacement(int x, int y) {
		int indiceDansMap = (x/this.getCarte().getHauteur()) + ((y/this.getCarte().getHauteur()) * this.getCarte().getLargeur());
		return this.personnages.get(indiceDansMap);
	}
	
	public Personnage emplacement(int indice) {
		return this.personnages.get(indice);
	}
	
	public Joueur getJoueur() throws ErreurObjetIntrouvable {
		for (int i = 0; i < this.getPersonnages().size() ; i++) {
			if(this.getPersonnages().get(i) instanceof Joueur) {
				return (Joueur) this.getPersonnages().get(i);
			}
		}
		throw new ErreurObjetIntrouvable("Joueur", "Environnement.personnages");
	}
	
	public ObservableList<Personnage> getPersonnages(){
		return this.personnages;
	}

	public Carte getCarte(){
		return this.carte;
	}
	
	
	
	public void attaquerPersonnages(int lieu, int degat) {
		this.personnages.get(lieu).decrementerPv(degat);;
		if (this.personnages.get(lieu).estMort()) {
			this.personnages.get(lieu).meurt();
			this.supprimer(lieu);
		}
	}
}