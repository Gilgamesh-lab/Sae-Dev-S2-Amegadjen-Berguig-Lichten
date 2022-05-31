package application.controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.Outils.Hache;
import application.modele.Outils.Pelle;
import application.modele.Outils.Pioche;
import application.modele.Ressources.Ressource;
import application.modele.Ressources.Terre;
import application.modele.personnage.Joueur;
import application.vue.EnvironnementVue;
import application.vue.JoueurVue;
import application.vue.RessourceView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controleur implements Initializable{

	@FXML
	private Pane plateau;
	@FXML
	private TilePane carte;
	@FXML
	private Button boutonInventaire;

	private Timeline gameLoop;
	private int temps;
	private InventaireControleur inv;
	private Joueur perso;
	private JoueurVue persoVue;
	private JoueurControleur persoControleur;
	private Environnement env;
	private EnvironnementVue envVue;
	@FXML
	private HBox inventaire;



	@FXML
	void ouvrirInventaire(ActionEvent event) {
		ouvrirInventaire();
	}

	private void ouvrirInventaire() {
		System.out.println("Bonjour");
		if(!inventaire.isVisible()) {
			inventaire.setDisable(false);
			inventaire.setVisible(true);
		}
		else {
			inventaire.setDisable(true);
			inventaire.setVisible(false);
		}
	}

	@FXML
	void sourisPresse(MouseEvent event) {
		String click = event.getButton().name();
		System.out.println(click);
		int x = (int) event.getSceneX();
		int y = (int) event.getSceneY();
		Ressource cible = env.getCarte().emplacement(x, y);
		if (cible==null) {
			int indiceDansMap = (x/Carte.TAILLE_BLOCK) + ((y/Carte.TAILLE_BLOCK) * Carte.LARGEUR);
			persoControleur.sourisPresse(click, indiceDansMap);
		}
		else {
			System.out.println(env.getCarte().getBlockMap().indexOf(cible));
			persoControleur.sourisPresse(click, env.getCarte().getBlockMap().indexOf(cible));//a voir si problème avec click sur bouton
		}
	}

	@FXML
	void touchePresse(KeyEvent event) {
		String touchePresse = event.getCode().toString().toLowerCase();
		/*
		 * TODO
		 * Mettre un switch pour gérer les action qui nécessite un wait (ex: pause avec echap)
		 * et en default persoControleur.touchePresse(touchePresse)
		 */

		System.out.println(touchePresse);
		switch (touchePresse) {
		case "e":
			ouvrirInventaire();
			break;

		default:
			persoControleur.touchePresse(touchePresse);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			env =new Environnement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		envVue = new EnvironnementVue(env, carte);
		ListChangeListener<Ressource> listen = (cs -> {
			System.out.println("changement");
			while(cs.next()) {
				if (cs.wasRemoved()) {
					int indiceBloc;
					for (Ressource ancien : cs.getRemoved()) {
						if (ancien!=null) {
							System.out.println(ancien);
							System.out.println(cs.getRemoved());
							indiceBloc = ancien.getIndice();
							System.out.println(indiceBloc+ " indice Bloc");
							carte.getChildren().set(indiceBloc, new RessourceView(null, env));
						}
					}
				}
				else {
					int indiceBloc;
					for (Ressource nouveau : cs.getAddedSubList()) {
						if (nouveau != null) {
							System.out.println(nouveau);
							System.out.println(cs.getAddedSubList());
							indiceBloc = nouveau.getIndice();
							System.out.println(indiceBloc+ " indice Bloc");
							carte.getChildren().set(indiceBloc, new RessourceView(nouveau, env));
						}
					}
				}
			}});
		env.getCarte().getBlockMap().addListener(listen);
		perso  = new Joueur(320, 0, env);
		persoVue = new JoueurVue();
		persoControleur = new JoueurControleur(perso, persoVue);
		plateau.getChildren().add(persoVue.getSprite());
		try {
			envVue.creerEnvironnement();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inv = new InventaireControleur(inventaire);
		perso.equiper(new Pelle(env));
		persoVue.getSprite().xProperty().bind(perso.xProperty());
		persoVue.getSprite().yProperty().bind(perso.yProperty());
		perso.getInventaire().getItems().addListener(inv);
		persoVue.getSprite().setFitHeight(64);
		persoVue.getSprite().setFitWidth(32);

		initAnimation();
		gameLoop.play();
	}

	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev -> {
					if(temps==100)
						System.out.println("ok");
					else if(temps%2==0)
						try {
							perso.gravite();
						} catch (IOException e) {
							e.printStackTrace();
						}
					else if(temps==251)
						System.out.println("Toto");
					else if(temps>300)
						perso.equiper(new Terre(0));
					//					else if (temps>1500 && temps<1600) {
					//						System.out.println("Changement d'outils");//teste de la pioche elle marche
					//						perso.equiper(new Pioche(env));
					//					}
					//					else if(temps>1600 && temps<1700) {
					//						System.out.println("Changement outils");
					//						perso.equiper(new Hache(env));
					//					}
					temps++;
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
}
