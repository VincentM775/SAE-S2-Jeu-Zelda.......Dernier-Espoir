package com.example.dernierespoirsae.modele.Objets.Projectile;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;

public class Balle extends Projectile {

    public Balle(int degats, Environnement environnement, Acteur acteurQuiALancer) {
        super(degats, environnement,acteurQuiALancer,16,320);
    }

    @Override
    public int jeVaisEnX() {
        return getEnvironnement().getJoueur().getxDeLaSouris();
    }

    @Override
    public int jeVaisEnY() {
        return getEnvironnement().getJoueur().getyDeLaSouris();
    }

    @Override
    public void effet() {} //Cette balle n'a aucun effet sur la map ou autres

}
