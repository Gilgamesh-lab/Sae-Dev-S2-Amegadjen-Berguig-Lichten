package application.controleur;


import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.Item;
import application.modele.exception.ErreurInventairePlein;
import application.modele.outils.Hache;
import application.modele.outils.Pelle;
import application.modele.outils.Pioche;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;
import application.modele.ressources.Ressource;
import application.vue.EnvironnementVue;
import application.vue.JoueurVue;
import application.vue.RessourceView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.collections.ListChangeListener;
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
    @FXML
    private Label nbPVResant;
    @FXML
	private TilePane inventaire;
//    @FXML
//    private Label nbPVMax;

	private Timeline gameLoop;
	private int temps;
	private InventaireControleur invControleur;
	private Joueur perso;
	private JoueurVue persoVue;
	private JoueurControleur persoControleur;
	private Environnement env;
	private EnvironnementVue envVue;
	private ListChangeListener<Ressource> listResssourceListener;
	private ListChangeListener<Personnage> listPersonnageListener;

	@FXML
	void ouvrirInventaire(ActionEvent event) {
		ouvrirInventaire();
	}

	@FXML
	void sourisPresse(MouseEvent event) {
		String click = event.getButton().name();
//		System.out.println(click);
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
	void equiper(MouseEvent event) {
		System.out.println("Click dans l'inventaire");
		System.out.println("X = " + event.getX());
		System.out.println("Y = " +event.getY());
		
		ImageView ev = (ImageView) event.getTarget();
		int indiceDansInventaire = Integer.parseInt(ev.getId());
		Item objetAEquiper = perso.getInventaire().getItem(indiceDansInventaire);
		
		if(objetAEquiper.equals(perso.getObjetEquiper()))
			perso.desequiper();
		else
			perso.equiper(objetAEquiper);
	}

	@FXML
	void touchePresse(KeyEvent event) {
		String touchePresse = event.getCode().toString().toLowerCase();
		/*
		 * TODO Mettre un switch pour gérer les action qui nécessite un wait (ex: pause avec echap)
		 * et en default persoControleur.touchePresse(touchePresse)
		 * 
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			env =new Environnement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		envVue = new EnvironnementVue(env, carte);
		persoVue = new JoueurVue();

		listResssourceListener = (cs -> {
			System.out.println("changement bloc");
			while(cs.next()) {
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
				for (Ressource nouveau : cs.getAddedSubList()) {
					if (nouveau != null) {
						System.out.println(nouveau);
						System.out.println(cs.getAddedSubList());
						indiceBloc = nouveau.getIndice();
						System.out.println(indiceBloc+ " indice Bloc");
						carte.getChildren().set(indiceBloc, new RessourceView(nouveau, env));
					}
				}
			}});

		listPersonnageListener = (pc -> {
			System.out.println("changement peronnage");
			while(pc.next()) {
				for (Personnage mort : pc.getRemoved()) {
					this.plateau.getChildren().remove(mort);
				}
				for (Personnage nouveau : pc.getAddedSubList()) {
					System.out.println(nouveau.getClass());
					this.plateau.getChildren().add(persoVue.getSprite());
					if (nouveau instanceof Joueur) {
						persoControleur = new JoueurControleur((Joueur)nouveau, persoVue, env);
						persoVue.getSprite().xProperty().bind(nouveau.xProperty());
						persoVue.getSprite().yProperty().bind(nouveau.yProperty());
						persoVue.getSprite().setFitHeight(64);
						persoVue.getSprite().setFitWidth(32);
					}
				}
			}});


		env.getCarte().getBlockMap().addListener(listResssourceListener);
		env.getPersonnages().addListener(listPersonnageListener);
		perso  = new Joueur(320, 0, env);
		perso.setHauteurSaut(4);
//		perso.equiper(new Pelle(env));
		invControleur = new InventaireControleur(inventaire);
		perso.getInventaire().getItems().addListener(invControleur);
		try {
			perso.getInventaire().ajouter(new Hache(env));
			perso.getInventaire().ajouter(new Pelle(env));
			perso.getInventaire().ajouter(new Pioche(env));
		} catch (ErreurInventairePlein e) {
			System.out.println("Plein");
		}
		nbPVResant.textProperty().bind(perso.pvProperty().asString());
//		nbPVMax.textProperty().bind(Joueur.maxPvProperty().asString());
		envVue.creerEnvironnement();

		
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

					else if(temps==251)
						System.out.println("Toto");
					perso.gravite();
					temps++;
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
}
