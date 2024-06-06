package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.*;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public abstract class VueActeur {
    private Pane persoPane;
    private TilePane terrainPane;
    private Environnement environnement;
    private Acteur acteur;

    public VueActeur(Pane persoPane,TilePane terrainPane, Acteur acteur,Environnement environnement) {
        this.persoPane = persoPane;
        this.terrainPane=terrainPane;
        this.environnement = environnement;
        this.acteur = acteur;
        creerRectangle(acteur);
    }

    public void creerRectangle(Acteur acteur) {

        Rectangle rectangle = new Rectangle(15, 15);
        rectangle.translateXProperty().bind(acteur.xProperty());
        rectangle.translateYProperty().bind(acteur.yProperty());
        rectangle.setId(String.valueOf(acteur.getId()));
        persoPane.getChildren().add(rectangle);
        System.out.println(definitionCouleur());
        //Modifie la couleur du rectangle selon ce qu'il est
        switch (definitionCouleur()) {
            case 0:
                rectangle.setFill(Color.BLUE);
                break;
            case 1:
                rectangle.setFill(Color.BLACK);
                break;
            case 2:
                rectangle.setFill(Color.RED);
                break;
            case 3:
                rectangle.setFill(Color.YELLOW);
                break;
        }
    }
    public abstract int definitionCouleur();
    public void setImageAtIndex(int index, String imagePath) {
        // Obtenir le nœud à l'index spécifique
        Node node = terrainPane.getChildren().get(index);

        // Vérifier si le nœud est bien une instance d'ImageView
        if (node instanceof ImageView) {
            ImageView imageView = (ImageView) node;

            // Définir la nouvelle image
            Image image = new Image(imagePath);
            imageView.setImage(image);
        }
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
}
