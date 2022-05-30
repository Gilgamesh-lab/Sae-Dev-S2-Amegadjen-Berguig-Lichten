package application.controleur;

import application.modele.Item;
import application.modele.Joueur;
import application.vue.JoueurVue;
import javafx.collections.ListChangeListener;

public class JoueurControleur implements ListChangeListener<Item>{
	
	private Joueur perso;
	private JoueurVue persoVue;
	private boolean saut;
	
	public JoueurControleur(Joueur perso, JoueurVue persoVue) {
		this.perso=perso;
		this.persoVue=persoVue;
		saut = false;
	}
	
	public void touchePresse(String touchePresse) {
		switch (touchePresse) {
		case "q":
			persoVue.orrientationSpriteGauche();
			perso.gauche();
			break;
		case "d":
			persoVue.orrientationSpriteDroite();
			perso.droite();
			break;
		case "z":
			this.saut = true;
			//perso.saut(40);
			break;
		case "s":
			break;
		}
	}

	@Override
	public void onChanged(Change<? extends Item> arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isSaut() {
		return saut;
	}

	public void setSaut(boolean saut) {
		this.saut = saut;
	}
	
	
	
	public void sourisPresse(String click, int emplacement) {
		switch (click) {
		case "PRIMARY":
			perso.utiliserMain(emplacement);
			break;
		default:
			break;
		}
	}
	
}
