package com.example.dernierespoirsae.modele.Objets.Armes;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Armes.Arme;
import com.example.dernierespoirsae.modele.Objets.Projectile.Balle;
import com.example.dernierespoirsae.modele.Objets.Projectile.CocktailM;
import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;

public class CocktailMolotov extends Arme {
    private int quantite;
    private static int quantiteStatic=0;

    public CocktailMolotov(Environnement environnement, int x, int y) {
        super(50, x, y, "cocktailMolotov",environnement,(int) (Math.random()*2+1),false);
    }

    @Override
    public void incremeterDecremeterQuantiteInventaire(int val){
        this.quantite = quantiteStatic+=val;
    }


    @Override
    public int getQuantite() {
        return this.quantite;
    }

    @Override
    public void attaquer() {
        if (getEnvironnement().getJoueur().getQuantiteCocktailMolotov()>0) {
            Projectile projectile = new CocktailM(getDegats(), getEnvironnement(), getEnvironnement().getJoueur());
            getEnvironnement().getListProjectile().add(projectile);
            getEnvironnement().getJoueur().setQuantiteMunitions(getEnvironnement().getJoueur().getQuantiteMunitions()-1);
        }
    }
    @Override
    public void agirAvecJoueur() {
        getEnvironnement().getJoueur().setQuantiteCocktailMolotov(getEnvironnement().getJoueur().getQuantiteCocktailMolotov()+getQuantiteObjets());
    }

    @Override
    public void agir() {
        attaquer();
    }
}
