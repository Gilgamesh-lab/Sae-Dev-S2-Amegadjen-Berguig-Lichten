package application.IA;



import application.modele.Carte;
import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.personnages.Cerf;
import application.modele.personnages.Personnage;
import application.modele.personnages.Pnj;
import application.vue.PNJVue;

public class PNJControleur {

	private Pnj pnj;
	private PNJVue pnjVue;


	public PNJControleur(Pnj pnj, PNJVue pnjVue) {
		this.pnj = pnj;
		this.pnjVue = pnjVue;
	}

	

	public void agir() throws ErreurObjetIntrouvable {
		if(pnj.estPrèsDuJoueur(Carte.TAILLE_BLOCK * 5, 6)) {
			pnj.agir();
		}
	}
}
