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
import application.modele.outils.Sceau;
import application.modele.personnages.Cerf;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;
//import application.vue.MonstreVue;
import application.modele.personnages.Pnj;
import application.modele.personnages.Slime;
import application.modele.ressources.Ressource;
import application.modele.ressources.Terre;
//import application.vue.CerfVue;
import application.vue.EnvironnementVue;
import application.vue.JoueurVue;
import application.vue.PersonnageVue;
import application.vue.RessourceView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
	private InventaireControleur invControleur;
	private Joueur perso;
	private JoueurVue persoVue;
	private JoueurControleur persoControleur;

	private Environnement env;
	private EnvironnementVue envVue;
	private ListChangeListener<Ressource> listResssourceListener;
	private ListChangeListener<Personnage> listPersonnageListener;
	private Cerf cerf;
	private Sceau seau;
	private PersonnageVue nouveauPnjVue;
	private ChangeListener<IntegerProperty> scrollingListener;
	//	private CerfVue cerfVue;
	//	private IACerf cerfControleur;

	private Slime monstre;

	@FXML
	void ouvrirInventaire(ActionEvent event) {
		ouvrirInventaire();
	}

	@FXML
	void sourisPresse(MouseEvent event) {
		String click = event.getButton().name();
		int x = (int) event.getSceneX();
		int y = (int) event.getSceneY();
		Ressource cible = env.getCarte().emplacement(x, y);
		if (cible==null) {
			int indiceDansMap = (x/Carte.TAILLE_BLOCK) + ((y/Carte.TAILLE_BLOCK) * Carte.LARGEUR);
			persoControleur.sourisPresse(click, indiceDansMap);
		}
		else {
			persoControleur.sourisPresse(click, env.getCarte().getBlockMap().indexOf(cible));//a voir si problème avec click sur bouton
		}
	}

	@FXML
	void equiper(MouseEvent event) {
		try {
			ImageView ev = (ImageView) event.getTarget();
			int indiceDansInventaire = Integer.parseInt(ev.getId());
			Item objetAEquiper = perso.getInventaire().getItem(indiceDansInventaire);
			String newStyle = "-fx-background-color: #bbbbbb;-fx-border-style:solid;-fx-border-color:red;-fx-border-width:5px;";
			String oldStyle = "-fx-background-color: #bbbbbb;-fx-border-style:none;-fx-border-width:0px;";
			for (Node a : inventaire.getChildren())
				if(a.getStyle().equals(newStyle)) {
					a.setStyle(oldStyle);
					a.applyCss();
				}
			if(objetAEquiper.equals(perso.getObjetEquiper())) {
				perso.desequiper();
			}
			else {
				perso.equiper(objetAEquiper);
				ev.getParent().setStyle(newStyle);
				ev.getParent().applyCss();
			}

		}catch(Exception e) {
			System.err.println("Merci de ne pas cliquer sur le bord gris claire");
		}

	}

	@FXML
	void touchePresse(KeyEvent event) {
		String touchePresse = event.getCode().toString().toLowerCase();
		/*
		 * TODO Mettre un switch pour gérer les action qui nécessite un wait (ex: pause avec echap)
		 * et en default persoControleur.touchePresse(touchePresse)
		 *
		 */

//		System.out.println(touchePresse);
		switch (touchePresse) {
		case "e":
			ouvrirInventaire();
			break;

		case "m":

			try {
				monstre.meurt();
			} catch (ErreurInventairePlein e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			//monstreVue.getSprite().setVisible(false);
			System.out.println("You Win");
			break;

		case "p":
			if(monstre.estMort()) {
				monstre.setPv(10);
				monstre.setX(perso.getCheckpoint().getX());
				monstre.setY(perso.getCheckpoint().getY());
				//monstreVue.getSprite().setVisible(true);
				System.out.println("Respawn du slime");
			}
			break;

		case "l":
			try {
				cerf.meurt();
			} catch (ErreurInventairePlein e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			///cerfVue.getSprite().setVisible(false);
			System.out.println("Good Meal");
			break;

		case "o":
//			if(cerf.estMort()) {
				cerf.setPv(10);
				cerf.setX(perso.getCheckpoint().getX());
				cerf.setY(perso.getCheckpoint().getY());
				//cerfVue.getSprite().setVisible(true);
				System.out.println("Respawn du cerf");
//			}
			break;

		case "k":
			try {
				perso.meurt();
			} catch (ErreurInventairePlein e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				perso.meurt();
			} catch (ErreurInventairePlein e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			persoVue.getSprite().setVisible(false);
			break;

		case "i":
			if(perso.estMort()) {
				perso.incrementerPv(7);
				perso.teleporterToCheckpoint();
				persoVue.getSprite().setVisible(true);
				System.out.println("Respawn");
			}
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
	@FXML
	void Organiser(MouseEvent event) {
		
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
			while(cs.next()) {
				int indiceBloc;
				for (Ressource ancien : cs.getRemoved()) {
					if (ancien!=null) {
						indiceBloc = ancien.getIndice();
						carte.getChildren().set(indiceBloc, new RessourceView(null, env));
					}
				}
				for (Ressource nouveau : cs.getAddedSubList()) {
					if (nouveau != null) {
						indiceBloc = nouveau.getIndice();
						carte.getChildren().set(indiceBloc, new RessourceView(nouveau, env));
					}
				}
			}});

	//////////Personnages dans l'environnement
		listPersonnageListener = (pc -> {
			while(pc.next()) {
				for (Personnage mort : pc.getRemoved()) {
					this.plateau.getChildren().remove(mort);
				}
				for (Personnage nouveau : pc.getAddedSubList()) {
					if (nouveau instanceof Joueur) {
						persoVue = new JoueurVue();
						this.plateau.getChildren().add(persoVue.getSprite());
						persoControleur = new JoueurControleur((Joueur)nouveau, persoVue);
						persoVue.getSprite().xProperty().bind(nouveau.xProperty());
						persoVue.getSprite().yProperty().bind(nouveau.yProperty());
						persoVue.getSprite().setFitHeight(64);
						persoVue.getSprite().setFitWidth(32);
					}
					else {
						nouveauPnjVue = new PersonnageVue(nouveau.getClass().getSimpleName());
						this.plateau.getChildren().add(nouveauPnjVue.getSprite());
						nouveauPnjVue.getSprite().xProperty().bind(nouveau.xProperty());
						nouveauPnjVue.getSprite().yProperty().bind(nouveau.yProperty());
						nouveauPnjVue.getSprite().setFitHeight(nouveau.getTaille()[0]*Carte.TAILLE_BLOCK);
						nouveauPnjVue.getSprite().setFitHeight(nouveau.getTaille()[1]*Carte.TAILLE_BLOCK);
					}
				}
			}});

	//////////Ajout des listener aux deux liste de l'environement
		env.getCarte().getBlockMap().addListener(listResssourceListener);
		env.getPersonnages().addListener(listPersonnageListener);


	///////////Création du Joueur et de son menu

		////////Ajout du Joueur et bind au Sprite du Joueur
		perso  = new Joueur(420, 199, env);
		persoVue.getSprite().xProperty().bind(perso.xProperty().multiply(Carte.LARGEUR/10));
		persoVue.getSprite().yProperty().bind(perso.yProperty());

		////////Ajout du Joueur et bind au Sprite du Joueur
		nbPVResant.textProperty().bind(perso.pvProperty().asString());
		invControleur = new InventaireControleur(inventaire);
		perso.getInventaire().getItems().addListener(invControleur);

		/*
		 * Test
		 */
		cerf = new Cerf(320,100,env);
		// Test
		monstre = new Slime(120,100,100, env);
		/*
		 * Test
		 */
//		plateau.translateXProperty().bind(perso.xProperty().divide(Carte.LARGEUR/12).negate());
//		plateau.translateYProperty().bind(perso.yProperty().negate());

		seau = new Sceau(env);
		try {
			perso.getInventaire().ajouter(new Hache(env));
			perso.getInventaire().ajouter(new Pelle(env));
			perso.getInventaire().ajouter(new Pioche(env));
			perso.getInventaire().ajouter(seau);
			perso.getInventaire().ajouter(new Terre(0));
		} catch (ErreurInventairePlein e) {
			System.out.println("Plein");
		}

////////////Gameloop
		initAnimation();
		gameLoop.play();
	}

	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev -> {
					if (temps%Sceau.getTempsRemplissage()==0 && !seau.EstRempli() && temps!=0) {
						seau.remplir();
					}
					if(temps==100)
						System.out.println("ok");
					env.getPersonnages().forEach(pnj -> {
						if (pnj instanceof Pnj)
							((Pnj)pnj).agir();
					});
					env.getPersonnages().forEach(pj -> pj.gravite());

					temps++;

				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
}
