package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Acteur{

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(340, 260, "Johnny", environnement, 20, 7, longTuile, largeTuile, nbTuile);
    }

    @Override
    public void seDeplacer() {
        int dx = 0;
        int dy = 0;

        if (getDirection().contains("up")) {
            dy -= super.getVitesse();
        }
        if (getDirection().contains("down")) {
            dy += super.getVitesse();
        }
        if (getDirection().contains("left")) {
            dx -= super.getVitesse();
        }
        if (getDirection().contains("right")) {
            dx += super.getVitesse();
        }

        deplacementActeur(dx, dy);
    }

    private void deplacementActeur(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
