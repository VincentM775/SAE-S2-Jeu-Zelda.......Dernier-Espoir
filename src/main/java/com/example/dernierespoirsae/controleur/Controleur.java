package com.example.dernierespoirsae.controleur;
import com.example.dernierespoirsae.algo.BFS;
import com.example.dernierespoirsae.Vue.*;
import com.example.dernierespoirsae.modele.*;
import com.example.dernierespoirsae.modele.Armes.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.net.URL;

public class Controleur implements Initializable {

    @FXML
    private TilePane terrainPane;
    @FXML
    private Pane persoPane;
    @FXML
    private Pane principalPane;
    private VueInventaire vueInventaire;
    @FXML
    private VBox inventairePane;
    @FXML
    private Pane armePaneMap;
    private Environnement environnement;
    private Timeline gameLoop;
    private int temps;
    private BFS bfs;

    public void initialize(URL location, ResourceBundle ressource) {

        //creation de l'environement
        this.environnement = new Environnement(32, 125, 125);

        //Creation du joueur
        Acteur joueur = new Joueur(environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());

        /* ObservateurActeurs est une methode qui va observer les changement (ajout ou supression)
         * dans la liste d'acteur de l'environement (qui est une liste Observable)
         */
        ObservateurActeurs observateurActeurs = new ObservateurActeurs(persoPane);

        //Initialisation du BFS
        this.bfs = new BFS(this.environnement);

        //Initialisation de la vueInventaire
        this.vueInventaire = new VueInventaire(inventairePane);

        //Initialisation de la vue Terrain
        VueTerrain terrain =  new VueTerrain(environnement.getMap(), this.terrainPane);

        //Initialise un observateur pour une liste d'arme
        ObservateurArmes observateurArme = new ObservateurArmes(armePaneMap);

        //Creer des haches
        Arme hache = new Hache(60,150);
        Arme hache1 = new Hache(80,50);
        Arme hache3 = new Hache(100,200);

        //Creer un pistolet
        Arme pistolet = new Pistolet(800,300);
        Arme pistolet1 = new Pistolet(300,1000);
        Arme pistolet2 = new Pistolet(900,300);

        //Génére un terrain avec des tuile aléatoire
        environnement.getMap().generTerrain(environnement.getInfoTuile()[1] * environnement.getInfoTuile()[2]);

        //Initialisation de la taille des tuiles du FXML dépendamment de celles indiquées lors de la création de l'environnement
        this.terrainPane.setPrefTileWidth(this.environnement.getInfoTuile()[0]);
        this.terrainPane.setPrefTileHeight(this.environnement.getInfoTuile()[0]);

        //Initialisation du nombre de colonnes et nombres de ligne du Terrain, dépendamment de celles indiquées lors de la création de l'environnement
        this.terrainPane.setPrefWidth(this.environnement.getInfoTuile()[1] * this.environnement.getInfoTuile()[0]);
        this.terrainPane.setPrefHeight(this.environnement.getInfoTuile()[2] * this.environnement.getInfoTuile()[0]);

        //Lie l'observateur d'acteur a l'environnement
        environnement.setListenerActeurs(observateurActeurs);

        //Ajout du joueur a l'environnement
        environnement.setJoueur(joueur);

        //Ajout du BFS dans l'environnement
        this.environnement.setBfs(this.bfs);

        //Affiche le terrain
        terrain.afficherTerrain();

        //Lie cet observateur a la liste d'arme dans l'environnement
        environnement.setListenerArmes(observateurArme);

        //Ajoute des haches a l'environnement
        environnement.getListArmes().add(hache);
        environnement.getListArmes().add(pistolet);
        environnement.getListArmes().add(hache1);
        environnement.getListArmes().add(hache3);
        environnement.getListArmes().add(pistolet1);
        environnement.getListArmes().add(pistolet2);

        //Crée un Ennemi et l'ajoute a l'environnement
        Ennemi acteur1 = new MasticatorZ(360,260, environnement,(int) this.terrainPane.getPrefTileWidth(), (int) this.terrainPane.getPrefTileHeight(), this.terrainPane.getPrefColumns());
        environnement.addActeurs(acteur1);

        ChangeListener<Number> listenerX = new ObservateurPositionX(principalPane, joueur);
        joueur.xProperty().addListener(listenerX);

        ChangeListener<Number> listenerY = new ObservateurPositionY(principalPane, joueur);
        joueur.yProperty().addListener(listenerY);

        //Initialialisation du keyHandler pour les gestions des entrée clavier utilisateur
        KeyHandler keyHandler = new KeyHandler(environnement);
        persoPane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        persoPane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);

        //Demarrage de la gameLoop
        initAnimation();
        gameLoop.play();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
            // on définit le FPS (nbre de frame par seconde)
            Duration.seconds((0.040)),
            // on définit ce qui se passe à chaque frame
            // c'est un eventHandler d'ou le lambda
            (ev ->{

//                    if(temps==10){
//                        System.out.println("boucle fini");
//                        gameLoop.stop();
//                    }

                //A modifier
               int rayonInteraction = 5;//Nombre de pixel

               for (int i = 0; i < environnement.getListActeurs().size(); i++) {

                   Rectangle rectangle = (Rectangle) persoPane.lookup("#" + environnement.getListActeurs().get(i).getId());

                   if (temps % 50 == 0) {

                       //Si un acteur est dans un rayon de 'rayonInteraction' autours du joueur alors
                       if ((environnement.getJoueur().getY() + rectangle.getWidth() + rayonInteraction) >= environnement.getListActeurs().get(i).getY()
                               && ((environnement.getJoueur().getY() - rectangle.getWidth() - rayonInteraction) <= environnement.getListActeurs().get(i).getY())
                               && (environnement.getJoueur().getX() + rectangle.getWidth() + rayonInteraction) >= environnement.getListActeurs().get(i).getX()
                               && ((environnement.getJoueur().getX() - rectangle.getWidth() - rayonInteraction) <= environnement.getListActeurs().get(i).getX())) {

                           if(environnement.getListActeurs().get(i) != environnement.getJoueur()){

                               //Enlève 10 pv a l'acteur
                               environnement.getListActeurs().get(i).perdPV(10);

                               //Vérifie si l'acteur doit mourir, si Oui il le supprime de l'environnement
                               environnement.getListActeurs().get(i).meurtOuVie();
                           }
                       }
                   }
               }
                //A modifier
                for (int i = 0; i < environnement.getListArmes().size(); i++) {

                    ImageView imageView = (ImageView) armePaneMap.lookup("#" + environnement.getListArmes().get(i).getId());

                    //Si le joueur est entré dans un rayon de 'rayonInteraction' autour d'une arme alors
                    if ((environnement.getJoueur().getY() + imageView.getFitWidth() + rayonInteraction) >= environnement.getListArmes().get(i).getY()
                       && ((environnement.getJoueur().getY() - imageView.getFitWidth() - rayonInteraction) <= environnement.getListArmes().get(i).getY())
                       && (environnement.getJoueur().getX() + imageView.getFitWidth() + rayonInteraction) >= environnement.getListArmes().get(i).getX()
                       && ((environnement.getJoueur().getX() - imageView.getFitWidth() - rayonInteraction) <= environnement.getListArmes().get(i).getX())) {

                        //Ajoute l'arme a l'inventaire
                        environnement.getJoueur().getInventaire().getArmes().add(environnement.getListArmes().get(i));

                        //Recupère la position de l'arme ajouté dans l'inventaire
                        int dernierElement = environnement.getJoueur().getInventaire().getArmes().size() - 1;

                        //Supprime l'arme de la liste d'armes contenue dans l'environnement
                        environnement.getListArmes().remove(i);

                        //Affiche cette arme dans l'inventaire
                        this.vueInventaire.addViewArmeInventaire(environnement.getJoueur().getInventaire().getArmes().get(dernierElement));
                    }
                }

                environnement.getJoueur().seDeplacer();

                for (Acteur acteur : this.environnement.getListActeurs()) {
                    if (acteur instanceof Ennemi) {
                        acteur.seDeplacer();
                    }
                }

                environnement.getJoueur().seDeplacer();
                temps++;
            })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        persoPane.requestFocus();
    }

}