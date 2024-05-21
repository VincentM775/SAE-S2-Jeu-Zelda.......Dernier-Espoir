package com.example.dernierespoirsae.modele;

public class MasticatorZ extends Zombie{

    public MasticatorZ(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "MasticatorZ", environnement, 20, 4, longTuile, largeTuile, nbTuile);
    }

    @Override
    protected int getId() {
        return super.getId();
    }
}
