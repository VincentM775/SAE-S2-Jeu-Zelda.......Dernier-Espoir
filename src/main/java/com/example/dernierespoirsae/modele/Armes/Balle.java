package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;

public class Balle extends Projectile {

    public Balle(int degats, int x, int y, Environnement environnement, Acteur acteurQuiALancer) {
        super(degats, x, y, environnement,acteurQuiALancer);
    }


}
