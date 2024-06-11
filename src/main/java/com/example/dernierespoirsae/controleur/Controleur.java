package com.example.dernierespoirsae.controleur;

import com.example.dernierespoirsae.Observateur.*;
import com.example.dernierespoirsae.algo.BFS;
import com.example.dernierespoirsae.Vue.*;
import com.example.dernierespoirsae.modele.*;
import com.example.dernierespoirsae.modele.Acteurs.*;
import com.example.dernierespoirsae.modele.Armes.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.net.URL;

public class Controleur implements Initializable {

    @FXML
    private TilePane terrainPane;
    @FXML
    private Pane persoPane, barreViePane, principalPane;
    @FXML
    private BorderPane fenetre;
    @FXML
    private Pane armePaneEquipee;
    @FXML
    private VBox inventaireVBox;
    @FXML
    private Pane armePaneMap;
    @FXML
    private Pane projectilePane;
    private Environnement environnement;
    private Timeline gameLoop;
    private int temps;
    private BFS bfs;

    public void initialize(URL location, ResourceBundle ressource) {
        LoadJSON loadJSON = new LoadJSON("src/main/resources/com/example/dernierespoirsae/terrain0.json");

        //creation de l'environement
        this.environnement = new Environnement(32, 100, 100);

        //Definition de la taille des tuiles du FXML dépendamment de celles indiquées lors de la création de l'environnement
        this.terrainPane.setPrefTileWidth(this.environnement.getInfoTuile()[0]);
        this.terrainPane.setPrefTileHeight(this.environnement.getInfoTuile()[0]);

        //Definition du nombre de colonnes et nombres de ligne du Terrain, dépendamment de celles indiquées lors de la création de l'environnement
        this.terrainPane.setPrefWidth(this.environnement.getInfoTuile()[1] * this.environnement.getInfoTuile()[0]);
        this.terrainPane.setPrefHeight(this.environnement.getInfoTuile()[2] * this.environnement.getInfoTuile()[0]);

        //Creation du joueur
        Joueur joueur = new Joueur(environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns(), inventaireVBox, armePaneEquipee);

        ObservateurInventaire observateurInventaire =new ObservateurInventaire(inventaireVBox, joueur.getInventaire());
        joueur.getInventaire().getListeArmeInventaire().addListener(observateurInventaire);

        //Initialisation de la vue Terrain
        VueTerrain vueTerrain =  new VueTerrain(environnement.getTerrain(), this.terrainPane,loadJSON.getMap(), loadJSON.getMap2(),environnement);

        /* ObservateurActeurs est une methode qui va observer les changement (ajout ou supression)
        *  dans la liste d'acteur de l'environement (qui est une liste Observable) */
        ObservateurActeurs observateurActeurs = new ObservateurActeurs(persoPane,barreViePane,terrainPane,environnement,vueTerrain);

        joueur.getArmeEquipeeProperty().addListener((observable, oldValue, newValue) -> {
            new VueArmeEquipee(joueur.getArmeEquipeeProperty().getValue(),joueur, this.armePaneEquipee);
        });

        //Lie l'observateur d'acteur a l'environnement
        environnement.setListenerActeurs(observateurActeurs);

        //Ajout du joueur a l'environnement
        environnement.setJoueur(joueur);

        //Initialise un observateur pour une liste d'arme
        ObservateurArmes observateurArme = new ObservateurArmes(armePaneMap);

        //Lie cet observateur a la liste d'arme dans l'environnement
        environnement.setListenerArmeEnvironnement(observateurArme);

        //Creer des haches
        Arme hache = new Hache(60,150,environnement);
        Arme hache1 = new Hache(80,50,environnement);
        Arme hache3 = new Hache(100,200,environnement);

        //Creer un pistolet
        Arme pistolet = new Pistolet(800,300,environnement);
        Arme pistolet1 = new Pistolet(300,1000,environnement);
        Arme pistolet2 = new Pistolet(900,300,environnement);

        //Ajoute les armes a l'environnement
        environnement.getListArmeEnvironnement().add(hache);
        environnement.getListArmeEnvironnement().add(pistolet);
        environnement.getListArmeEnvironnement().add(hache1);
        environnement.getListArmeEnvironnement().add(hache3);
        environnement.getListArmeEnvironnement().add(pistolet1);
        environnement.getListArmeEnvironnement().add(pistolet2);

        //Génére un terrain avec des tuile aléatoire
        environnement.getTerrain().generTerrain(environnement.getInfoTuile()[1] * environnement.getInfoTuile()[2]);

        //Création d'un premier zombie MasticartorZ
        Ennemi acteur1 = new MasticatorZ(360,260, environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());
        environnement.addActeurs(acteur1);

        //Création d'un 2e zombie LeZamikaze
        Ennemi acteur2 = new Zamikaze(400,340, environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());
        environnement.addActeurs(acteur2);

        //Création d'un 3e zombie le Bave-Zmort
        Ennemi acteur3 = new BaveZmort(470,400, environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());
        environnement.addActeurs(acteur3);

        //Créer le lien entre la liste Des Projectiles et la class observableProjectile
        environnement.getListProjectile().addListener(new ObservateurProjectile(this.projectilePane,environnement));

        //Créer le lien entre la liste Des flaques de baves et la class observableBave
        environnement.getListBave().addListener(new ObservateurTrainerBave(this.environnement,this.terrainPane,vueTerrain));

        ObservateurPositionX obsX = new ObservateurPositionX(principalPane, joueur);
        ObservateurPositionY obsY = new ObservateurPositionY(principalPane, joueur);

        joueur.xProperty().addListener(obsX);

        joueur.yProperty().addListener(obsY);

        //Initialialisation du keyHandler pour la gestions des entrées clavier utilisateur
        KeyHandler keyHandler = new KeyHandler(environnement);
        persoPane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        persoPane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);

        ClickHandler clickHandler= new ClickHandler(environnement,obsX,obsY);
        fenetre.setOnMouseClicked(clickHandler);

        //Initialisation du BFS
        this.bfs = new BFS(this.environnement);

        //Ajout du BFS dans l'environnement
        this.environnement.setBfs(this.bfs);

        //Demarrage de la gameLoop
        initAnimation();
        gameLoop.play();
    }

    private void initAnimation() {
        this.gameLoop = new Timeline();
        this.temps=0;
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
            // on définit le FPS (nbre de frame par seconde)
            Duration.seconds((0.035)),
            // on définit ce qui se passe à chaque frame
            // c'est un eventHandler d'ou le lambda
            (ev ->{

//                    if(temps==10){
//                        System.out.println("boucle fini");
//                        gameLoop.stop();
//                    }
                this.environnement.setTemps(this.environnement.getTemps()+1);
                this.environnement.unTour();
                this.temps++;
            })
        );
        this.gameLoop.getKeyFrames().add(kf);
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        persoPane.requestFocus();
    }

}