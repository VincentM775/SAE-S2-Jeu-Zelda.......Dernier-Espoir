package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueMasticatorZ extends VueActeur{


    public VueMasticatorZ(Pane persoPane, TilePane terrainPane, Acteur acteur, Environnement environnement) {
        super(persoPane, terrainPane, acteur, environnement);
    }

    @Override
    public int definitionCouleur() {
        return 1;
    }
}
