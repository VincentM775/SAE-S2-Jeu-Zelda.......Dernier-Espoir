package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Armes.Armes;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueArmes {
    private Pane armePane;
    private Armes arme;
    private Inventaire inventaire;
    private Acteur joueur;


    public VueArmes(Pane armePane, Armes arme, Acteur joueur, Inventaire inventaire) {
        this.armePane = armePane;
        this.arme = arme;
        this.joueur = joueur;
        creeViewArme();
    }
    public void creeViewArme() {

        Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/hache.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(95);
        imageView.setFitHeight(95);
        armePane.getChildren().add(imageView);
        imageView.translateXProperty().setValue(arme.getX());
        imageView.translateYProperty().setValue(arme.getY());
        imageView.setId(""+this.arme.getId());

    }
}
