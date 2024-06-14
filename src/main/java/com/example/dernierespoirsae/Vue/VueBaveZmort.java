package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueBaveZmort extends VueZombie{


    public VueBaveZmort(Pane persoPane,Pane barreViePane, TilePane terrainPane, Acteur acteur, Environnement environnement) {
        super(persoPane,barreViePane, terrainPane, acteur, environnement);
    }

    @Override
    public String imageACreer() {
        return "file:src/main/resources/com/example/dernierespoirsae/images/bavezmort0.png";
    }
    @Override
    public int[] placementImage() {
        return new int[]{0, 0};
    }
}
