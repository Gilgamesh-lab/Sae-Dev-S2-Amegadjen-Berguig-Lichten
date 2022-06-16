package application.vue;

public class JoueurVue extends PersonnageVue {


	public JoueurVue() {
		super("perso");
	}

	
	public void sautVue(int temps) {
		if (temps<1) 
			this.getsprite.setImage(new Image("ressource/persoAccroupi.jpg"));
		else if (temps==10) 
			this.sprite.setImage(new Image("ressource/perso.png"));
	}
	

	
}
