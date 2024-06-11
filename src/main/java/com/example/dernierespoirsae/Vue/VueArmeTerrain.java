package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueArmeTerrain extends VueArme{

    private Pane armePaneMap;

    public VueArmeTerrain(Arme arme, Pane armePaneMap) {
        super(arme);
        this.armePaneMap = armePaneMap;
        creeViewArme();
    }

    @Override
    public void setPotitionArme(ImageView imageView){
        imageView.translateXProperty().setValue(getArme().getX());
        imageView.translateYProperty().setValue(getArme().getY());
    }

    @Override
    public void ajoutImagePane(ImageView imageView){
        this.armePaneMap.getChildren().add(imageView);
    }


}