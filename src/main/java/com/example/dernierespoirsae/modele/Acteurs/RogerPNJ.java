package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.scene.layout.Pane;

public class RogerPNJ extends PNJ{

    private boolean premiereInterraction;

    public RogerPNJ(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile, Pane principalPane) {
        super(x, y, "Roger", environnement, 1, 0, longTuile, largeTuile, nbTuile, 15, 15, principalPane);
        this.premiereInterraction = false;
    }


    @Override
    public void interraction() {
        if(!premiereInterraction){

        }
    }
}