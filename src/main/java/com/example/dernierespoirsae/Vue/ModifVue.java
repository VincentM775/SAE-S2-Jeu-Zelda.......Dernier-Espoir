package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ModifVue {
    public static void setImageAtIndex(int index, Image image, Environnement environnement, Pane terrainPane) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(environnement.getInfoTuile()[0]);
        imageView.setFitHeight(environnement.getInfoTuile()[0]);
        terrainPane.getChildren().set(index, imageView);
    }
    public static void addGifToPane(int x, int y,int taille, String image, double gifDurationMs, Pane persoPane) {
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
}
