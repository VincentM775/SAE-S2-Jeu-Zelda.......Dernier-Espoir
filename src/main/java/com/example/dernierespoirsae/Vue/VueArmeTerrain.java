package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Objets.Armes.Arme;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueArmeTerrain extends VueArme{

    private Pane armePaneMap;

    public VueArmeTerrain(Objets objets, Pane armePaneMap) {
        super(objets);
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