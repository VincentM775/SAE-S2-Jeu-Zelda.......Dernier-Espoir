package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Acteurs.Ennemi;
import com.example.dernierespoirsae.modele.Environnement;

public class BalleBave extends Projectile{
    public BalleBave(int degats, Environnement environnement, Acteur acteurQuiALancer) {
        super(degats, environnement, acteurQuiALancer,8,((Ennemi) acteurQuiALancer).getPorteeDeVue()*32);
    }

    @Override
    public int jeVaisEnX() {
        return getEnvironnement().getJoueur().getX();
    }

    @Override
    public int jeVaisEnY() {
        return getEnvironnement().getJoueur().getY();
    }

}
