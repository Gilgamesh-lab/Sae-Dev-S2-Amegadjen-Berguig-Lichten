package application.controleur;

import application.modele.personnages.Pnj;
import application.vue.PersonnageVue;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class IaControleur implements ChangeListener<Pnj>{
	
	private Pnj pnj;
	private PersonnageVue pnjSprite;
	private boolean saut;
	private int tempsSaut;




	public IaControleur(Pnj pnj, PersonnageVue pnjSprite) {
		this.pnj = pnj;
		this.pnjSprite = pnjSprite; 
		saut = false;
		tempsSaut = 0;
	}

	public Pnj getPnj() {
		return this.pnj;
	}
	
	public PersonnageVue getPersonnageVue () {
		return this.pnjSprite;
	}
	
	public boolean isSaut() {
		return saut;
	}

	public void setSaut(boolean saut) {
		this.saut = saut;
	}

	public int getTempsSaut() {
		return tempsSaut;
	}
	
	public void incremterTempsSaut() {
		this.tempsSaut+=1;
	}
	
	public void reinisialiseTempsSaut() {
		this.tempsSaut = 0;
	}


	@Override
	public void changed(ObservableValue<? extends Pnj> observable, Pnj oldValue, Pnj newValue) {

		
	}
}
