package application.modele;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Random;

import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.exception.TailleMapException;
import application.modele.personnages.Cerf;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;
import application.modele.personnages.Pnj;
import application.modele.personnages.Slime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

	private ObservableList<Personnage> personnages;
	private Carte carte;

	public Environnement() throws TailleMapException, IOException {
		this.carte = new Carte();
		this.personnages = FXCollections.observableArrayList();
	}
	
	public void initJeu() {
		ajouter(new Joueur(420, 576, this));
		Checkpoint checkpoint = new Checkpoint(340,576,this);
		this.getJoueur().setCheckpoint(checkpoint);
	}
	
	public void tourDejeu() {
//		if (personnages.size()<200)
//			ajouter(faireSpawner());
//		personnages.forEach(pnj -> {
//			if (pnj instanceof Pnj)
//				try {
//					((Pnj)pnj).agir();
//				} catch (ErreurObjetIntrouvable e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		});
		personnages.forEach(perso -> perso.gravite());
	}
	
	private ArrayList<Pnj> faireSpawner() {
		ArrayList<Pnj> vontSpawner = new ArrayList<Pnj>();
		double chanceSpwan = Math.random();
		int x = (int) (Math.random()*Carte.TAILLE_BLOCK*Carte.LARGEUR);
		int y = this.carte.aMemeLeSol(x);
		if (chanceSpwan<0.001)
			vontSpawner.add(new Slime(x, y-Carte.TAILLE_BLOCK*Slime.TAILLE[1], this));
		else if (chanceSpwan<0.003)
			vontSpawner.add(new Cerf(x, y-Carte.TAILLE_BLOCK*Cerf.TAILLE[1], this));
		return vontSpawner;
	}
	
	
	
	public void ajouter(ArrayList<Pnj> pnjs) {
		this.personnages.addAll(pnjs);
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
	
	public int getEmplacementPerso(Personnage perso) {
		for (int i = 0; i < this.getPersonnages().size() ; i++) {
			if(this.getPersonnages().get(i).getX() == perso.getX() && this.getPersonnages().get(i).getY() == perso.getY()) {
				return i;
			}
		}
		return -1;
	}

	public Joueur getJoueur() {
		for (int i = 0; i < this.getPersonnages().size() ; i++) {
			if(this.getPersonnages().get(i) instanceof Joueur) {
				return (Joueur) this.getPersonnages().get(i);
			}
		}
		return null;
	}

	public ObservableList<Personnage> getPersonnages(){
		return this.personnages;
	}

	public Carte getCarte(){
		return this.carte;
	}

	public void attaquerPersonnages(int lieu, int degat) throws ErreurInventairePlein {
		for (Personnage perso : this.getPersonnages()) {
			if (this.getJoueur().getX() + Carte.TAILLE_BLOCK >= perso.getX()  && this.getJoueur().getX() <= perso.getX()){
					this.personnages.get(lieu).decrementerPv(degat);
//					if (this.personnages.get(lieu).estMort()) {
//						this.personnages.get(lieu).meurt();
//						this.supprimer(lieu);
//					}
			} 
		}
	}

	public ArrayList<Personnage> emplacementPerso(int x, int y, int[] taille) {
		ArrayList<Personnage> personnagesTouches = new ArrayList<>();
		int tailleBloc = Carte.TAILLE_BLOCK;
		boolean toucheX;
		boolean toucheY;
		for (Personnage perso : this.personnages) {
			toucheX = 	(perso.getX()+perso.getTaille()[0]*tailleBloc)>=x && perso.getX()<=x
					||	(perso.getX()+perso.getTaille()[0]*tailleBloc)<=x && perso.getX()>=x;

			toucheY = 	(perso.getY()+perso.getTaille()[1]*tailleBloc)>=y && perso.getY()<=y
					||	(perso.getY()+perso.getTaille()[1]*tailleBloc)<=y && perso.getY()>=y;

			if (toucheX && toucheY)
				personnagesTouches.add(perso);				
		}
		
		return personnagesTouches;
	}

}

