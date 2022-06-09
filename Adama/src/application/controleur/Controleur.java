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
import application.vue.CerfVue;
import application.vue.EnvironnementVue;
import application.vue.JoueurVue;
import application.vue.MonstreVue;
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
	
	private Cerf cerf;
	private CerfVue cerfVue;
	private CerfControleur cerfControleur;
	
	private Slime monstre;
	private MonstreVue monstreVue;
	private MonstreControleur monstreControleur;

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
					this.plateau.getChildren().add(new JoueurVue().getSprite());
					if (nouveau instanceof Joueur) {
						persoControleur = new JoueurControleur((Joueur)nouveau, persoVue, env);
						persoVue.getSprite().xProperty().bind(nouveau.xProperty());
						persoVue.getSprite().yProperty().bind(nouveau.yProperty());
						persoVue.getSprite().setFitHeight(64);
						persoVue.getSprite().setFitWidth(32);
						nbPVResant.textProperty().bind(nouveau.pvProperty().asString());
					}
				}
			}});

//		env.getCarte().getBlockMap().addListener(listen);
		perso  = new Joueur(420, 0, env);
		perso.setHauteurSaut(4);
		nbPVResant.textProperty().bind(perso.pvProperty().asString());
		
		persoVue = new JoueurVue();
		
		persoControleur = new JoueurControleur(perso, persoVue, env);
		env.getCarte().getBlockMap().addListener(listResssourceListener);
		env.getPersonnages().addListener(listPersonnageListener);
		perso  = new Joueur(320, 0, env);
		perso.setHauteurSaut(4);
//		nbPVResant.textProperty().bind(perso.pvProperty().asString());
		plateau.getChildren().add(persoVue.getSprite());
		
		envVue.creerEnvironnement();
		inv = new InventaireControleur(inventaire);
		perso.equiper(new Pelle(env));
		persoVue.getSprite().xProperty().bind(perso.xProperty());
		persoVue.getSprite().yProperty().bind(perso.yProperty());
		
		perso.getInventaire().getItems().addListener(inv);
		persoVue.getSprite().setFitHeight(64);
		persoVue.getSprite().setFitWidth(32);
		
		cerf = new Cerf(320,0,env);
		cerfVue = new CerfVue();
		plateau.getChildren().add(cerfVue.getSprite());
		cerfControleur = new CerfControleur(cerf, cerfVue);
		cerfVue.getSprite().xProperty().bind(cerf.xProperty());
		cerfVue.getSprite().yProperty().bind(cerf.yProperty());
		cerfVue.getSprite().setFitHeight(64);
		cerfVue.getSprite().setFitWidth(32);
		
		
		
		
		monstre = new Slime(120,0,1, env);
		monstreVue = new MonstreVue();
		plateau.getChildren().add(monstreVue.getSprite());
		monstreControleur = new MonstreControleur(monstre, monstreVue);
		monstreVue.getSprite().xProperty().bind(monstre.xProperty());
		monstreVue.getSprite().yProperty().bind(monstre.yProperty());
		monstreVue.getSprite().setFitHeight(32);
		monstreVue.getSprite().setFitWidth(32);
		perso.getInventaire().getItems().addListener(inv);
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
						this.cerfControleur.agir();
					} catch (ErreurObjetIntrouvable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
					
					try {
						if(!monstre.estMort()) {
							monstreControleur.agir();
							if(monstreControleur.getTempsSaut() < 8 &&monstreControleur.isSaut()) {
			//						perso.monter(4);
									System.out.println(2);
									monstre.monter(4);
									monstre.translationX(-2);
									monstreControleur.incremterTempsSaut();
							}
							
							if(monstreControleur.getTempsSaut() >= 8){
								for (int k = 0 ; k < 32 ; k++) {
									perso.translationX(-2);
								}
								
								monstreControleur.reinisialiseTempsSaut();
								monstreControleur.setSaut(false);
							}
							
							
							if(monstreControleur.getTempsSaut()== 32 && monstreControleur.isSaut()) {
								System.out.println("Ok");
								monstreControleur.setSaut(false);
								monstreControleur.reinisialiseTempsSaut();
							}
							
							System.out.println(monstreControleur.getTempsSaut());
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
