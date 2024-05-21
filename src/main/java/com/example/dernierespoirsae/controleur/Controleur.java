package com.example.dernierespoirsae.controleur;
import com.example.dernierespoirsae.Vue.ObservateurActeurs;
import com.example.dernierespoirsae.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import java.util.ResourceBundle;
import java.net.URL;



public class Controleur implements Initializable {
    @FXML
    private TilePane mapPane;
    @FXML
    private Pane persoPane;
    private Environnement environnement;

    //sert la gameloop :
    private Timeline gameLoop;
    private int temps;

/*
//Creé un environement de taille n
        Environnement environnement = new Environnement(375);
        this.keyHandler = new KeyHandler(environnement);

        //Affiche cet envirenoement
        afficherMap(environnement.getMap());

        //Crer un joueur et l'ajoute dans l'environement
        Joueur joueur = new Joueur("Johnny", environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        environnement.setJoueur(joueur);

        //ObservateurActeurs c'est une methode qui va observer les changement (ajout ou supression)
        //dans la liste d'acteur de l'environement (qui est une liste Observable)
        ObservateurActeurs observateurActeurs = new ObservateurActeurs(persoPane);

        //Lie l'observateur d'acteur a l'envirenoment
        environnement.setListenerActeurs(observateurActeurs );

        Acteur acteur1 = new Acteur("Ariles",environnement, (int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns() );
        environnement.addActeurs(acteur1);

        Acteur acteur2 = new Acteur("Ariles",environnement, (int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns() );
        environnement.addActeurs(acteur1);

        Acteur acteur3 = new Acteur("Ariles",environnement, (int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns() );
        environnement.addActeurs(acteur1);

        Acteur acteur4 = new Acteur("Ariles",environnement, (int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns() );
        environnement.addActeurs(acteur1);

        MasticatorZ zombie1 = new MasticatorZ(360,260, environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        environnement.addActeurs(zombie1);

        Acteur acteur5 = new Acteur("Ariles",environnement, (int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns() );
        environnement.addActeurs(acteur1);

        //Creer un sprite qui represente le joueur
        observateurActeurs.creerSprite(joueur);

        //Je sais pas
        persoPane.addEventHandler(KeyEvent.KEY_PRESSED,this.keyHandler);
        persoPane.requestFocus();
 */
    public void initialize(URL location, ResourceBundle ressource) {
        this.environnement = new Environnement(375);

        Acteur joueur = new Joueur(environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        environnement.setJoueur(joueur);
        /*
        ObservateurActeurs c'est une methode qui va observer les changement (ajout ou supression)
        dans la liste d'acteur de l'environement (qui est une liste Observable)
        */
        ObservateurActeurs observateurActeurs = new ObservateurActeurs(persoPane);

        //Lie l'observateur d'acteur a l'envirenoment
        environnement.setListenerActeurs(observateurActeurs);

        Ennemi zombie1 = new MasticatorZ(360,260, environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        zombie1.setVitesse(2); // Exemple : régler la vitesse à 2
        zombie1.setNombreDePixelDeplacer(100); // Exemple : régler la distance à 100 pixels
        environnement.addActeurs(zombie1);


        Ennemi acteur1 = new MasticatorZ(360,260, environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        environnement.addActeurs(acteur1);

        //Creer un sprite qui represente le joueur
        observateurActeurs.creerSprite(joueur);

        KeyHandler keyHandler = new KeyHandler(environnement);
        persoPane.addEventHandler(KeyEvent.KEY_PRESSED, keyHandler);
        persoPane.addEventHandler(KeyEvent.KEY_RELEASED, keyHandler);
        afficherMap(environnement.getMap());
        initAnimation();
        gameLoop.play();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
            // on définit le FPS (nbre de frame par seconde)
            Duration.seconds((0.016)),
            // on définit ce qui se passe à chaque frame
            // c'est un eventHandler d'ou le lambda
            (ev ->{


//                    if(temps==10){
//                        System.out.println("boucle fini");
//                        gameLoop.stop();
//                    }
                System.out.println("un tour");
                environnement.getJoueur().seDeplacer();
                if (temps%5==0){
                    for (Acteur acteur : this.environnement.getListActeurs()) {
                        if (acteur instanceof Ennemi) {
                            ((Ennemi) acteur).seDeplacer();
                        }
                    }
                }

                environnement.getJoueur().seDeplacer();

                temps++;

            })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    public void afficherMap(Map map) {
        for (int x = 0; x < map.getListTuiles().size(); x++) {
            ImageView imageView = new ImageView();
            switch (map.getListTuiles().get(x)) {
                case 0:
                    Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/Grass_02_v2.png");
                    imageView.setImage(image);
                    imageView.setFitWidth(39);
                    imageView.setFitHeight(39);
                    break;
            }
            mapPane.getChildren().add(imageView);
        }
    }
    public void creerSprite(Acteur acteur) {
        if (!(acteur instanceof Zombie)) {
            Circle cercle = new Circle(10);
            cercle.setFill(Color.BLUE);
            cercle.translateXProperty().bind(acteur.xProperty());
            cercle.translateYProperty().bind(acteur.yProperty());
            persoPane.getChildren().add(cercle);
        }
        else if (acteur instanceof MasticatorZ){
            Circle cercle = new Circle(15);
            cercle.setFill(Color.RED);
            cercle.translateXProperty().bind(acteur.xProperty());
            cercle.translateYProperty().bind(acteur.yProperty());
            persoPane.getChildren().add(cercle);
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        persoPane.requestFocus();
    }
}