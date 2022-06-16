package application.controleur;



import java.net.URL;

import java.util.ResourceBundle;

import application.modele.Carte;
import application.modele.Checkpoint;
import application.modele.Environnement;
import application.modele.exception.ErreurObjetIntrouvable;
import application.modele.outils.Pelle;
import application.modele.personnages.Cerf;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;

import application.modele.personnages.Slime;

import application.modele.ressources.Ressource;
import application.modele.ressources.Terre;
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
import javafx.scene.layout.HBox;
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
	private HBox inventaire;

	private Timeline gameLoop;
	private int temps;
	private int timeRespawn;
	private int valRecul;
	private InventaireControleur inv;
	private Joueur perso;
	private JoueurVue persoVue;
	private JoueurControleur persoControleur;
	
	private Environnement env;
	private EnvironnementVue envVue;
//	private ListChangeListener<Ressource> listResssourceListener;
//	private ListChangeListener<Personnage> listPersonnageListener;
	
	private Cerf cerf;
	private CerfVue cerfVue;
	private CerfControleur cerfControleur;
	
	private Slime monstre;
	private MonstreVue monstreVue;
	private MonstreControleur monstreControleur;
	
	private boolean toucherM = false;
	private int tempsToucherM = 0 ;
	
	private boolean toucherJ = false;
	private int tempsToucherJ = 0 ;
	
	private boolean toucherE = false;
	private int tempsToucherE = 0 ;
	
	private Personnage ennemie;
	
	
	



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
	void touchePresse(KeyEvent event) throws ErreurObjetIntrouvable{
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
			monstre.meurt();
			monstreVue.getSprite().setVisible(false);
			System.out.println("You Win");
			break;
			
		case "p":
			if(monstre.estMort()) {
				monstre.setPv(15);
				monstre.setX(perso.getCheckpoint().getX());
				monstre.setY(perso.getCheckpoint().getY());
				monstreVue.getSprite().setVisible(true);
				System.out.println("Respawn du slime");
			}
			break;
			
		case "l":
			cerf.meurt();
			cerfVue.getSprite().setVisible(false);
			System.out.println("Good Meal");
			break;
			
		case "o":
			if(cerf.estMort()) {
				cerf.setPv(cerf.getPv());
				cerf.setX(perso.getCheckpoint().getX());
				cerf.setY(perso.getCheckpoint().getY());
				cerfVue.getSprite().setVisible(true);
				System.out.println("Respawn du cerf");
			}
			break;
			
		case "k":
			perso.meurt();
			persoVue.getSprite().setVisible(false);
			System.out.println("Game Over");
			break;
			
		case "i":
			if(perso.estMort()) {
				perso.incrementerPv(7);
				perso.teleporterToCheckpoint();
				persoVue.getSprite().setVisible(true);
				System.out.println("Respawn");
			}
			break;
			
		case "w":
			perso.equiper(new Terre(0));
			
		case "a":
			toucherE = true;
//			Personnage ennemie;
//			try {
//				ennemie = perso.estPrèsDunPerso(perso.getArmeEquiper().getPorter()* Carte.TAILLE_BLOCK, 1);
//				toucherE = true;
//				
//			} catch (ErreurObjetIntrouvable e) {
//				System.out.println("Pas d'ennemie");
//			}
			
			
//			Personnage ennemie;
//			try {
//				ennemie = perso.estPrèsDunPerso(perso.getArmeEquiper().getPorter(), 1);
//				if(ennemie.ouSeTrouveLeJoueur()) {
//					ennemie.translationX(Carte.TAILLE_BLOCK  * 2);
//				}
//				else {
//					ennemie.translationX(-Carte.TAILLE_BLOCK  * 2);
//				}
//				this.toucherM = true;
//				ennemie.decrementerPv(perso.getArmeEquiper().getDegat());
//				System.out.println(ennemie.getPv());
//				if(ennemie.estMort()) {
//					if(ennemie instanceof Cerf) {
//						cerfVue.getSprite().setVisible(false);
//				
//					}
//					if(ennemie instanceof Slime) {
//						monstreVue.getSprite().setVisible(false);
//					}
//				}
//				
//			} catch (ErreurObjetIntrouvable e) {
//				System.out.println("Pas d'ennemie");
//			}
			
			

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
		Checkpoint checkpoint = new Checkpoint(340,576,env);
		perso.setCheckpoint(checkpoint);
		perso.setHauteurSaut(4);
		nbPVResant.textProperty().bind(perso.pvProperty().asString());
		persoVue = new JoueurVue();
		persoControleur = new JoueurControleur(perso, persoVue);
		plateau.getChildren().add(persoVue.getSprite());
		envVue.creerEnvironnement();
		inv = new InventaireControleur(inventaire);
		perso.equiper(new Pelle(env));
		persoVue.getSprite().xProperty().bind(perso.xProperty());
		persoVue.getSprite().yProperty().bind(perso.yProperty());
		perso.getInventaire().getItems().addListener(inv);
		persoVue.getSprite().setFitHeight(64);
		persoVue.getSprite().setFitWidth(32);
		
		cerf = new Cerf(320,58,env);
		cerfVue = new CerfVue();
		plateau.getChildren().add(cerfVue.getSprite());
		cerfControleur = new CerfControleur(cerf, cerfVue);
		cerfVue.getSprite().xProperty().bind(cerf.xProperty());
		cerfVue.getSprite().yProperty().bind(cerf.yProperty());
//
//		
//		
//		
//		
		monstre = new Slime(120,11, env);
		monstreVue = new MonstreVue();
		plateau.getChildren().add(monstreVue.getSprite());
		monstreControleur = new MonstreControleur(monstre, monstreVue);
		monstreVue.getSprite().xProperty().bind(monstre.xProperty());
		monstreVue.getSprite().yProperty().bind(monstre.yProperty());

		initAnimation();
		gameLoop.play();
	}

	private void initAnimation() {
		gameLoop = new Timeline();
		temps=0;
		timeRespawn = -1;
		valRecul = 0;
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		monstre.meurt();
		cerf.meurt();
		
		KeyFrame kf = new KeyFrame(Duration.seconds(0.017),
				(ev -> {
//					if(temps==100)
//						System.out.println("ok");
//					
//					else if(temps==251)
//						System.out.println("Toto");
//					else if(temps>300)
//						perso.equiper(new Terre(0));
					
					if(!cerf.estMort()) {
						try {
							this.cerfControleur.agir();
						} catch (ErreurObjetIntrouvable e1) {
							// TODO Auto-generated catch block
							System.out.println("Pas d'ennemie");
						}
					}
//
//							
//					
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
							
							if(monstreControleur.getTempsSaut() >= 8) {
								if(monstre.ouSeTrouveLeJoueur()) {
									valRecul = -2;
								}
								else {
									valRecul = 2;
								}
								
								System.out.println(valRecul);
								for (int k = 0 ; k < 32 ; k++) {
									perso.translationX(valRecul);
								}
								monstre.getEnvironnement().getJoueur().decrementerPv(2);
								
								monstreControleur.reinisialiseTempsSaut();
								monstreControleur.setSaut(false);
								this.toucherJ = true;
							}
							
							
							if(monstreControleur.getTempsSaut()== 32 && monstreControleur.isSaut()) {
								System.out.println("0k");
								monstreControleur.setSaut(false);
								monstreControleur.reinisialiseTempsSaut();
							}
						}
					}catch (ErreurObjetIntrouvable  e) {
						System.out.println("Pas d'ennemie");
					}
					
					if(perso.estMort() && timeRespawn ==  -1) {
						persoVue.getSprite().setVisible(false);
						System.out.println("Game over");
						timeRespawn =  0;
					}
					
					if(timeRespawn > 1) {
						perso.incrementerPv(7);
						perso.teleporterToCheckpoint();
						persoVue.getSprite().setVisible(true);
						System.out.println("Respawn");
						timeRespawn =  -1;
						
					}
					
					if(!perso.estMort()) {
						if(persoControleur.getTempsSaut()<30&&persoControleur.isSaut()) {
								perso.monter(4);
								//perso.sauter();
								persoControleur.incremterTempsSaut();
							}	
							
							
							
						else if(persoControleur.getTempsSaut()==30&&persoControleur.isSaut()) {
							persoControleur.setSaut(false);
							persoControleur.reinisialiseTempsSaut();
						}
					}
					if(tempsToucherM%2==0 && tempsToucherM <50 && toucherM) {
						monstreVue.getSprite().setVisible(false);
					}
					
					if(tempsToucherM%2 ==1 && tempsToucherM < 50 && toucherM) {
						if(toucherM) {
							monstreVue.getSprite().setVisible(true);
						}
					}
					
					if(tempsToucherJ%2==0 && tempsToucherJ < 50 && toucherJ) {
						persoVue.getSprite().setVisible(false);
					}
					
					if(tempsToucherJ%2 ==1 && tempsToucherJ < 50 && toucherJ) {
						if(toucherJ) {
							persoVue.getSprite().setVisible(true);
						}
					}
					
					if(toucherE && tempsToucherE == 0) {
						Personnage ennemie;
						try {
							System.out.println("Porter : " + perso.getArmeEquiper().getPorter());
							ennemie = perso.estPrèsDunPerso(perso.getArmeEquiper().getPorter() * Carte.TAILLE_BLOCK, 1);
							System.out.println("marche");
							if(ennemie.ouSeTrouveLeJoueur()) {
								ennemie.translationX(Carte.TAILLE_BLOCK  * 2);
							}
							else {
								ennemie.translationX(-Carte.TAILLE_BLOCK  * 2);
							}
							ennemie.decrementerPv(perso.getArmeEquiper().getDegat());
							System.out.println(ennemie.getPv());
							if(ennemie.estMort()) {
								if(ennemie instanceof Cerf) {
									cerfVue.getSprite().setVisible(false);
							
								}
								if(ennemie instanceof Slime) {
									monstreVue.getSprite().setVisible(false);
								}
							}
						}catch (ErreurObjetIntrouvable e) {
							System.out.println("Pas d'ennemie");
						}

					}
					
					if(tempsToucherE == 1 && toucherE) {
						persoVue.setSprite("ressource/persoEpeeLever.png");
					}
					
					if(tempsToucherE >= 10 && toucherE) {
						persoVue.setSprite("ressource/persoEpeeRanger.png");
					}
					
					System.out.println("x : " + perso.getX() + "y : " + perso.getY());
					
					if(temps%2==0)
//						
						for(Personnage personnage : env.getPersonnages()) {
							if(!personnage.estMort()) {
								personnage.gravite();
							}
							else if (!personnage.estEnDehorsMap(0,0)) {
								personnage.meurt();
								if(personnage instanceof Cerf) {
									cerfVue.getSprite().setVisible(false);
							
								}
								if(personnage instanceof Slime) {
									monstreVue.getSprite().setVisible(false);
								}
								
							}
						}
					temps++;
					if(toucherM) {
						tempsToucherM ++;
					}
					if(tempsToucherM> 50) {
						toucherM = false;
						tempsToucherM =0;
					}
					
					if(tempsToucherE> perso.getArmeEquiper().getTempsRecharge() * 10 ) { // temps de recharge arme
						toucherE = false;
						tempsToucherE =0;
					}
					
					if(toucherE) {
						tempsToucherE ++;
					}
					
					
					if(toucherJ) {
						tempsToucherJ ++;
					}
					if(tempsToucherJ> 50) {
						toucherJ = false;
						tempsToucherJ =0;
					}
					if(timeRespawn >= 0) {
						timeRespawn++;
					}
					
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
}