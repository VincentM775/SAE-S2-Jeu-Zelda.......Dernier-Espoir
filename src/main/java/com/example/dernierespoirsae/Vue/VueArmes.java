package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueArmes {
    private Arme arme;
    private Pane armePaneMap;
    public VueArmes(Arme arme, Pane armePaneMap) {
        this.arme = arme;
        this.armePaneMap = armePaneMap;
        creeViewArmeMap();
    }

    public void creeViewArmeMap() {

        Image imageArme = new Image("file:src/main/resources/com/example/dernierespoirsae/images/"+arme.getType()+".png");
        ImageView imageView = new ImageView(imageArme);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.translateXProperty().setValue(arme.getX());
        imageView.translateYProperty().setValue(arme.getY());
        this.armePaneMap.getChildren().add(imageView);
        imageView.setId(""+this.arme.getId());
    }
}