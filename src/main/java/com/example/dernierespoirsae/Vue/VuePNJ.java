package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.Main;
import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Acteurs.PNJ;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VuePNJ extends VueActeur{



    public VuePNJ(Pane persoPane, Pane barreViePane, TilePane terrainPane, Acteur acteur, Environnement environnement) {
        super(persoPane, barreViePane, terrainPane, acteur, environnement);
        creerLabelMessage();
    }

    @Override
    public int definitionCouleur() {
        return 0;
    }

    @Override
    public void definitionbarreDeVie() {
        //Pas de barre de vie
    }

    public void creerLabelMessage(){
        Label messagePNJ = new Label();
        messagePNJ.setPrefWidth(500);
        messagePNJ.setText("gcyudgyugf");
        messagePNJ.setPrefHeight(300);
        //if()
            super.getPersoPane().getChildren().add(messagePNJ);
        //messagePNJ.setLayoutX();
        //messagePNJ.setLayoutY(getActeur().getEnvironnement().getJoueur().g);
    }
}
