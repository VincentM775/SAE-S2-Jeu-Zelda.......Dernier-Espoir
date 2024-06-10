package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.scene.layout.Pane;

public abstract class PNJ extends Acteur{

    public PNJ(int x, int y, String nom, Environnement environnement, int vie, int vitesse, int longTuile, int largeTuile, int nbTuile, int longBox, int largeBox, Pane principalPane) {
        super(x, y, nom, environnement, vie, vitesse, longTuile, largeTuile, nbTuile, longBox, largeBox, principalPane);
    }


    @Override
    public void meurtOuVie() {
        //??
    }

    @Override
    public void seDeplacer() {
        //Le PNJ ne se déplace pas
    }

    @Override
    public void agit() {
        //??
    }

    public abstract void interraction();
}