package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueObjetTerrain extends VueObjet{

    private Pane objetPaneMap;

    public VueObjetTerrain(Objets objets, Pane objetPaneMap) {
        super(objets);
        this.objetPaneMap = objetPaneMap;
        creeViewObjet();
    }

    @Override
    public void setPositionArme(ImageView imageView){
        imageView.translateXProperty().setValue(getObjets().getX());
        imageView.translateYProperty().setValue(getObjets().getY());
    }

    @Override
    public void ajoutImagePane(ImageView imageView){
        this.objetPaneMap.getChildren().add(imageView);
    }


}