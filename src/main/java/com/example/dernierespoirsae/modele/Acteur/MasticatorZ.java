package com.example.dernierespoirsae.modele.Acteur;

import com.example.dernierespoirsae.modele.Environnement;

public class MasticatorZ extends Zombie {

    public MasticatorZ(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "MasticatorZ", environnement, 100, 4, 4, longTuile, largeTuile, nbTuile, 4+(int) (Math.random()*2));
    }

}
