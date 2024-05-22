package com.example.dernierespoirsae.modele;

public class Zombie extends Ennemi{


    public Zombie(int x, int y, String nom, Environnement environnement, int vie, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, nom, environnement, vie,2, nombreDeDegat, longTuile, largeTuile, nbTuile);
    }

}
