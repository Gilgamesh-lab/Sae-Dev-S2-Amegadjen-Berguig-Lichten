package application.vue;

public class JoueurVue extends PersonnageVue {


	public JoueurVue() {
		super("perso");
	}

	
	public void sautVue(int temps) {
		if (temps<1) 
			super.setSprite("ressource/persoAccroupi.jpg");
		else if (temps==10) 
			super.setSprite("ressource/perso.png");
	}
	

	
}
