package application.controleur;

import application.modele.personnages.Pnj;
import application.vue.PersonnageVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class IaControleur implements ChangeListener<Pnj> {
	
	private Pnj pnj;
	private PersonnageVue pnjSprite;
	
	public IaControleur(Pnj pnj, PersonnageVue pnjSprite) {
		this.pnj=pnj;
		this.pnjSprite=pnjSprite;
	}

	@Override
	public void changed(ObservableValue<? extends Pnj> observable, Pnj oldValue, Pnj newValue) {
	
		
	}
}
