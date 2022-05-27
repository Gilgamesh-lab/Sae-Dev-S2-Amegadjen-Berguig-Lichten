package application.vue;

import java.io.IOException;


import application.modele.Environnement;
import application.modele.Ressource;
import javafx.scene.layout.TilePane;

public class EnvironnementVue {
	
	private TilePane carte;
	private Environnement env;
	
	public EnvironnementVue(Environnement env, TilePane carte) {
		this.carte=carte;
		this.env=env;
	}
	
	public void creerEnvironnement() throws IOException {
		int tailleMap = (env.getCarte().getHauteur()) * (env.getCarte().getLargeur());
		Ressource bloc;
		for(int i=0; i<tailleMap; i++) {
			bloc= env.getCarte().getBlockMap().get(i);
			this.carte.getChildren().add(new RessourceView(bloc, env));
		}
//		ImageCursor changer l'image de la souris
	}
	


}
