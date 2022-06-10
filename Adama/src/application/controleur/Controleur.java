package application.controleur;


import java.net.URL;
import java.util.ResourceBundle;

import application.modele.Carte;
import application.modele.Environnement;
import application.modele.exception.ErreurInventairePlein;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.outils.Pelle;
import application.modele.personnages.Cerf;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;
import application.modele.personnages.Slime;
import application.modele.ressources.Ressource;
import application.modele.ressources.Terre;
import application.vue.EnvironnementVue;
import application.vue.JoueurVue;
import application.vue.PNJVue;
import application.vue.RessourceView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.collections.ListChangeListener;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controleur implements Initializable {

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

	private Timeline gameLoop;
	private int temps;
	private InventaireControleur inv;
	private Joueur perso;
	private JoueurVue persoVue;
	private JoueurControleur persoControleur;

	private Environnement env;
	private EnvironnementVue envVue;

	private ListChangeListener<Ressource> listResssourceListener;
	private ListChangeListener<Personnage> listPersonnageListener;

	private PNJVue nouveauPnjVue;
	private Cerf cerf;
	private Slime monstre;

	@FXML
	void ouvrirInventaire(ActionEvent event) {
		ouvrirInventaire();
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
	void equiper(MouseEvent event) {

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
		case "m":
			try {
				perso.meurt();
			} catch (ErreurInventairePlein e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			persoVue.getSprite().setVisible(false);
			break;
		default:
			persoControleur.touchePresse(touchePresse);
		}
	}

	private void ouvrirInventaire() {
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
		///////////// Création de l'environement
		try {
			env =new Environnement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		envVue = new EnvironnementVue(env, carte);

		
///////////List Listener

	//////////Bloc de la carte
		listResssourceListener = (cs -> {
			System.out.println("changement bloc");
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

	//////////Personnages dans l'environnement
		listPersonnageListener = (pc -> {
			System.out.println("changement peronnage");
			while(pc.next()) {
				for (Personnage mort : pc.getRemoved()) {
					this.plateau.getChildren().remove(mort);
				}
				for (Personnage nouveau : pc.getAddedSubList()) {
					System.out.println(nouveau.getClass());
					if (nouveau instanceof Joueur) {
						persoVue = new JoueurVue();
						this.plateau.getChildren().add(persoVue.getSprite());
						persoControleur = new JoueurControleur((Joueur)nouveau, persoVue, env);
						persoVue.getSprite().xProperty().bind(nouveau.xProperty());
						persoVue.getSprite().yProperty().bind(nouveau.yProperty());
						persoVue.getSprite().setFitHeight(64);
						persoVue.getSprite().setFitWidth(32);
					}
					else {
						nouveauPnjVue = new PNJVue(nouveau.getClass().getSimpleName());
						System.out.println(nouveau.getClass().getSimpleName());
						this.plateau.getChildren().add(nouveauPnjVue.getSprite());
						nouveauPnjVue.getSprite().xProperty().bind(nouveau.xProperty());
						nouveauPnjVue.getSprite().xProperty().bind(nouveau.xProperty());
						nouveauPnjVue.getSprite().setFitHeight(nouveau.getTaille()[0]);
						nouveauPnjVue.getSprite().setFitHeight(nouveau.getTaille()[1]);
					}
				}
			}});

	//////////Ajout des listener aux deux liste de l'environement
		env.getCarte().getBlockMap().addListener(listResssourceListener);
		env.getPersonnages().addListener(listPersonnageListener);


	///////////Création du Joueur et de son menu

	////////Ajout du Joueur et bind au Sprite du Joueur
		perso  = new Joueur(420, 0, env);
		persoVue.getSprite().xProperty().bind(perso.xProperty());
		persoVue.getSprite().yProperty().bind(perso.yProperty());
	////////Ajout du Joueur et bind au Sprite du Joueur
		nbPVResant.textProperty().bind(perso.pvProperty().asString());
		inv = new InventaireControleur(inventaire);
		perso.getInventaire().getItems().addListener(inv);

		/*
		 * Test
		 */
		cerf = new Cerf(320,0,env);
		//		cerfVue = new CerfVue();
		//		plateau.getChildren().add(cerfVue.getSprite());
		//		cerfControleur = new IACerf(cerf, cerfVue);
		//		cerfVue.getSprite().xProperty().bind(cerf.xProperty());
		//		cerfVue.getSprite().yProperty().bind(cerf.yProperty());
		//		cerfVue.getSprite().setFitHeight(64);
		//		cerfVue.getSprite().setFitWidth(32);
		// Test
		monstre = new Slime(120,0,1, env);
		//		monstreVue = new MonstreVue();
		//		plateau.getChildren().add(monstreVue.getSprite());
		//		monstreControleur = new IAEnnemi(monstre, monstreVue);
		//		monstreVue.getSprite().xProperty().bind(monstre.xProperty());
		//		monstreVue.getSprite().yProperty().bind(monstre.yProperty());
		//		monstreVue.getSprite().setFitHeight(32);
		//		monstreVue.getSprite().setFitWidth(32);
		/*
		 * Test
		 */

		//////////// Gameloop
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
					try {
						this.cerf.agir();
					} catch (ErreurObjetIntrouvable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					try {
						if(!monstre.estMort()) {
							monstre.agir();
							//							if(monstre.getTempsSaut() < 8 && monstre.isSaut()) {
							//									System.out.println(2);
//																monstre.monter(4);
//																monstre.translationX(-2);
							//									monstre.incremterTempsSaut();
							//							}
							//							
							//							if(monstre.getTempsSaut() >= 8){
							//								for (int k = 0 ; k < 32 ; k++) {
							//									perso.translationX(-2);
							//								}
							//								
							//								monstre.reinisialiseTempsSaut();
							//								monstre.setSaut(false);
							//							}
							//							
							//							
							//							if(monstre.getTempsSaut()== 32 && monstre.isSaut()) {
							//								System.out.println("Ok");
							//								monstre.setSaut(false);
							//								monstre.reinisialiseTempsSaut();
							//							}
							//							
							//							System.out.println(monstre.getTempsSaut());
						}
					} catch (ErreurObjetIntrouvable  e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					perso.gravite();
					temps++;
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
}

