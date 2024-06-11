package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.*;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.jar.Manifest;

public abstract class VueActeur {
    private Pane persoPane,barreViePane;
    private TilePane terrainPane;
    private Environnement environnement;
    private Acteur acteur;
    private ProgressBar barreVie;

    public VueActeur(Pane persoPane,Pane barreViePane,TilePane terrainPane, Acteur acteur,Environnement environnement) {
        this.persoPane = persoPane;
        this.terrainPane=terrainPane;
        this.barreViePane = barreViePane;
        this.environnement = environnement;
        this.acteur = acteur;
        this.barreVie = new ProgressBar();
        creerImage(acteur);
        creerBarreVie(acteur);
//        ChangeListener<String> animation = ((obs, old, nouv)-> System.out.println());
//        acteur.getDirectionProperty().addListener(animation);
//        debutAnimation();
    }

    public void creerImage(Acteur acteur) {
        String imagePath = imageACreer();

        ImageView imageView = new ImageView(new Image(imagePath));

        imageView.translateXProperty().bind(acteur.xProperty().subtract(placementImage()[0]));
        imageView.translateYProperty().bind((acteur.yProperty().subtract(placementImage()[1])));

        imageView.setId(String.valueOf(acteur.getId()));
        persoPane.getChildren().add(imageView);
    }
    public abstract String imageACreer();
    public abstract int[] placementImage();


    public void setImageAtIndex(int index, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(getEnvironnement().getInfoTuile()[0]);
        imageView.setFitHeight(getEnvironnement().getInfoTuile()[0]);
        terrainPane.getChildren().set(index, imageView);
    }
    public void addGifToPane(int x, int y,int taille, String image, double gifDurationMs) {
        // Charger le GIF
        Image gifImage = new Image(image);

        // Créer un ImageView pour contenir le GIF
        ImageView imageView = new ImageView(gifImage);

        // Définir la position et la taille du ImageView
        imageView.setX((int) x-((double) taille /2));
        imageView.setY(y-taille+15+15);
        imageView.setFitWidth(taille);
        imageView.setFitHeight(taille);
        persoPane.getChildren().add(imageView);

        // Supprimer le GIF après la durée totale
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(gifDurationMs), event -> {
            persoPane.getChildren().remove(imageView);
        }));
        timeline.setCycleCount(1); // Exécuter une seule fois
        timeline.play();
    }


    public void creerBarreVie(Acteur acteur) {
        NumberBinding progressBinding = Bindings.createDoubleBinding(
                () -> acteur.getVieProperty().get() / (double) acteur.getMaxVie(),
                acteur.getVieProperty(), acteur.maxVie()
        );

        barreVie.progressProperty().bind(progressBinding);

        barreVie.setId("barre"+acteur.getId());

        definitionbarreDeVie();
    }
    public abstract void definitionbarreDeVie();

    public Environnement getEnvironnement() {
        return environnement;
    }

    public Pane getPersoPane() {
        return persoPane;
    }

    public TilePane getTerrainPane() {
        return terrainPane;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public Pane getBarreViePane() {
        return barreViePane;
    }

    public ProgressBar getBarreVie() {
        return barreVie;
    }
//    public void debutAnimation(){
//        // Chemin de l'image unique contenant les différentes tuiles de l'animation
//        String imagePath = "file:src/main/resources/com/example/dernierespoirsae/images/universal-lpc-sprite_male_01_full.png";
//
//        // Dimensions d'une tuile dans l'image
//        int tileWidth = 32;
//        int tileHeight = 32;
//
//        // Nombre total de tuiles dans l'image
//        int numTiles = 1; // Par exemple, si votre image contient 10 tuiles différentes
//
//        // Durée de chaque image dans l'animation (en millisecondes)
//        double frameDurationMs = 80;
//
//        // Création d'une Timeline pour gérer l'animation
//        Timeline timeline = new Timeline();
//
//        // Ajout d'une KeyFrame pour chaque tuile dans la séquence
//        for (int i = 0; i < numTiles; i++) {
//            int tileIndex = i;
//            KeyFrame keyFrame = new KeyFrame(
//                    Duration.millis(frameDurationMs * tileIndex),
//                    event -> {
//                        // Chargement de l'image contenant la tuile correspondant à l'index actuel
//                        Image spriteSheet = new Image(imagePath);
//
//                        // Calcul des coordonnées de la tuile dans l'image
//                        int sourceX = (tileIndex % (int) (spriteSheet.getWidth() / tileWidth)) * tileWidth;
//                        int sourceY = (tileIndex / (int) (spriteSheet.getWidth() / tileWidth)) * tileHeight;
//
//                        // Découpage de la tuile de l'image complète
//                        Image tileImage = new WritableImage(spriteSheet.getPixelReader(), sourceX, sourceY, tileWidth, tileHeight);
//
//                        // Mise à jour de l'image de l'acteur avec la nouvelle tuile
//                        // Vous devrez remplacer cette ligne par une logique spécifique à votre application
//                        // pour mettre à jour l'apparence de l'acteur sur la scène
//                        // Par exemple, vous pouvez utiliser setImageAtIndex() pour mettre à jour l'image dans un TilePane
//                        // ou modifier l'image d'un ImageView associé à votre acteur
//                        setImageAtIndex(tileIndex, tileImage);
//                    }
//            );
//            timeline.getKeyFrames().add(keyFrame);
//        }
//
//        // Démarrage de l'animation
//        timeline.play();
//    }
//    public void changementDirectionAnimation() {
//
//        if (acteur.getDirection().contains("up")) {
//
//        }
//        if (acteur.getDirection().contains("down")) {
//
//        }
//        if (acteur.getDirection().contains("left")) {
//
//        }
//        if (acteur.getDirection().contains("right")) {
//
//        }
//
//    }

}
