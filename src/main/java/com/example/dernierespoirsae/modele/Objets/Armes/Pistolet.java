package com.example.dernierespoirsae.modele.Objets.Armes;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Projectile.Balle;
import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;

public class Pistolet extends Arme {

    private static int quantiteStatic = 0;
    private int quantite;

    public Pistolet(int x, int y, Environnement environnement) {
        super(50, x, y,"pistolet",environnement,1,true);
        this.quantite = 0;
    }

    @Override
    public void incremeterDecremeterQuantiteInventaire(int val){
        this.quantite = quantiteStatic+=val;
    }

    @Override
    public int getQuantite() {
        return quantite;
    }

    @Override
    public void agirAvecJoueur() {

    }

    @Override
    public void agir() {
        attaquer();
    }

    @Override
    public void attaquer() {
        if (getEnvironnement().getJoueur().getQuantiteMunitions()>0) {
            Projectile projectile = new Balle(getDegats(), getEnvironnement(), getEnvironnement().getJoueur());
            getEnvironnement().getListProjectile().add(projectile);
            getEnvironnement().getJoueur().setQuantiteMunitions(getEnvironnement().getJoueur().getQuantiteMunitions()-1);
        }
    }
}
