package com.example.dernierespoirsae.modele;

public class MasticatorZ extends Zombie{

    public MasticatorZ(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "MasticatorZ", environnement, 100, 5, 4, longTuile, largeTuile, nbTuile, 5+(int) (Math.random()*2));
    }

}
