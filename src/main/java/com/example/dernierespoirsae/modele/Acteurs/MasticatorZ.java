package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;

public class MasticatorZ extends Ennemi {

    public MasticatorZ(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "MasticatorZ", environnement, 100, 2, 4, longTuile, largeTuile, nbTuile, 4+(int) (Math.random()*2));
    }

    @Override
    public void agit() {

    }
}
