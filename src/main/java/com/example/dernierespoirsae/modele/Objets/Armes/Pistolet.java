package com.example.dernierespoirsae.modele.Objets.Armes;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Projectile.Balle;
import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pistolet extends Arme {

    private static int quantiteStatic = 0;
    private IntegerProperty quantite;

    public Pistolet(int x, int y, Environnement environnement) {
        super(50, x, y,"pistolet",environnement,1,true);
        this.quantite = new SimpleIntegerProperty();
    }

    @Override
    public void incremeterDecremeterQuantiteInventaire(int val){
        this.quantite.set(quantiteStatic+=val);
    }

    @Override
    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    @Override
    public void agirAvecJoueur() {

    }

    @Override
    public void agir() {
        if (getEnvironnement().getJoueur().getClickSouris().contains("g")) {
            getEnvironnement().getJoueur().setArmeALattaque(true);
            attaquer();
            getEnvironnement().getJoueur().setClicks("");
        }
    }

    @Override
    public void attaquer() {
        if (getEnvironnement().getJoueur().getQuantiteMunitions()>0) {
            Projectile projectile = new Balle(getDegats(), getEnvironnement(), getEnvironnement().getJoueur());
            getEnvironnement().getListProjectile().add(projectile);
            getEnvironnement().getJoueur().quantiteMunitionsProperty().set(getEnvironnement().getJoueur().getQuantiteMunitions()-1);
        }
    }
}
