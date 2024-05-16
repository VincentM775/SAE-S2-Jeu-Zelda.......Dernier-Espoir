package com.example.dernierespoirsae.modele;

public class Joueur extends Acteur{

    public Joueur(int x, int y, String nom, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, nom, environnement, 20, 100, longTuile, largeTuile, nbTuile);
    }
}