package com.example.dernierespoirsae.modele;

public class Zombie extends Ennemi{

    public Zombie(int x, int y, String nom, Environnement environnement, int vie, int vitesse, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile,int portee) {
        super(x, y, nom, environnement, vie, vitesse, nombreDeDegat, longTuile, largeTuile, nbTuile, portee);
    }
}
