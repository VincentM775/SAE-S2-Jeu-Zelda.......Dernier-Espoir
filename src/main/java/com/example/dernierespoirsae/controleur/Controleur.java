package com.example.dernierespoirsae.controleur;
import com.example.dernierespoirsae.algo.BFS;
import com.example.dernierespoirsae.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
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
    private BFS bfs;

    public void initialize(URL location, ResourceBundle ressource) {
        this.environnement = new Environnement(40,25,15);
        Acteur joueur = new Joueur(environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        this.environnement.setJoueur(joueur);
        this.bfs = new BFS(this.environnement);
        this.environnement.setBfs(this.bfs);

        Ennemi zombie1 = new MasticatorZ(360,260, environnement,(int) this.mapPane.getPrefTileWidth(), (int) this.mapPane.getPrefTileHeight(), this.mapPane.getPrefColumns());
        zombie1.setVitesse(2); // Exemple : régler la vitesse à 2
        zombie1.setNombreDePixelDeplacer(100); // Exemple : régler la distance à 100 pixels
        environnement.addActeurs(zombie1);

        creerSprite(environnement.getJoueur());

        for (Acteur acteur : environnement.getActeurs()){
            creerSprite(acteur);
        }

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
                if (temps%5==0){
                    for (Acteur acteur : this.environnement.getActeurs()) {
                        if (acteur instanceof Ennemi) {
                            ((Ennemi) acteur).seDeplacer();
                        }
                    }
                }



                // Démarrer la recherche récursive
//                bfs.algoBFS(environnement.getJoueur().getY() / environnement.getInfoTuile()[0], environnement.getJoueur().getX() / environnement.getInfoTuile()[0], 0);

                // Afficher le résultat
//                for (int[] tab : bfs.getTableauDesDistances()) {
//                    for (int val : tab) {
//                        System.out.print(val + " ");
//                    }
//                    System.out.println();
//                }

                environnement.getJoueur().seDeplacer();

                temps++;

            })
        );
        gameLoop.getKeyFrames().add(kf);
    }


    public void afficherMap(Terrain terrain) {
        for (int x = 0; x < terrain.getListTuiles().size(); x++) {
            ImageView imageView = new ImageView();
            switch (terrain.getListTuiles().get(x)) {
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
            Rectangle rectangle = new Rectangle(15,15);
            rectangle.setFill(Color.BLUE);
            rectangle.translateXProperty().bind(acteur.xProperty());
            rectangle.translateYProperty().bind(acteur.yProperty());
            persoPane.getChildren().add(rectangle);
        }
        else if (acteur instanceof MasticatorZ){
            Rectangle rectangle = new Rectangle(15,15);
            rectangle.setFill(Color.RED);
            rectangle.translateXProperty().bind(acteur.xProperty());
            rectangle.translateYProperty().bind(acteur.yProperty());
            persoPane.getChildren().add(rectangle);
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        persoPane.requestFocus();
    }
}