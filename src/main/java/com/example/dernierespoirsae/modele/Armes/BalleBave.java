package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;

public class BalleBave extends Projectile{
    public BalleBave(int degats, Environnement environnement, Acteur acteurQuiALancer) {
        super(degats, environnement, acteurQuiALancer);
    }

    @Override
    public void agit() {
        if (getEstVivant()) {
            if (!testProjectileArriverSurJoueur()) {
                avance();
            }
            else
                setEstVivant(false);
        }
        else{
             getEnvironnement().getListProjectile().remove(this);
        }
    }

    @Override
    public int jeVaisEnX() {
        return getEnvironnement().getJoueur().getX();
    }

    @Override
    public int jeVaisEnY() {
        return getEnvironnement().getJoueur().getY();
    }

    public boolean testProjectileArriverSurJoueur() {
        return (getXProperty()>= getjX()-(getVitesse()/2) && getXProperty()<= (getjX()+getVitesse()/2) && getYProperty()>= this.getjY()-(getVitesse()/2) && getYProperty()<= (this.getjY()+getVitesse()/2));
    }
}
