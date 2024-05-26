package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Armes.Armes;
import com.example.dernierespoirsae.modele.Joueur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueArmes {
    private Pane armePane;
    private Armes arme;

    private Acteur joueur;


    public VueArmes(Pane armePane, Armes arme, Acteur joueur) {
        this.armePane = armePane;
        this.arme = arme;
        this.joueur = joueur;
        creeViewArme();
    }
    public void creeViewArme() {

        Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/hache.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        armePane.getChildren().add(imageView);
        imageView.translateXProperty().bind(this.joueur.xProperty());
        imageView.translateYProperty().bind(this.joueur.yProperty());
        imageView.setId(""+this.arme.getId());
    }
}
