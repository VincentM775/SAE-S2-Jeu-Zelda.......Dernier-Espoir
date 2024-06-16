package com.example.dernierespoirsae.modele.Objets.Armes;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Projectile.CocktailM;
import com.example.dernierespoirsae.modele.Objets.Projectile.Projectile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CocktailMolotov extends Arme {
    private IntegerProperty quantite;
    private static int quantiteStatic=0;

    public CocktailMolotov(Environnement environnement, int x, int y) {
        super(40, x, y, "cocktailMolotov",environnement,(int) (Math.random()*99+1)+99,false);
        this.quantite = new SimpleIntegerProperty();
    }

    @Override
    public void incremeterDecremeterQuantiteInventaire(int val){
        this.quantite.setValue( quantiteStatic+=val);
    }

    @Override
    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    @Override
    public void attaquer() {
        if (getEnvironnement().getJoueur().getQuantiteCocktailMolotov() >0) {
            Projectile projectile = new CocktailM(getDegats(), getEnvironnement(), getEnvironnement().getJoueur());
            getEnvironnement().getListProjectile().add(projectile);
            getEnvironnement().getJoueur().quantiteCocktailMolotovProperty().set(getEnvironnement().getJoueur().getQuantiteCocktailMolotov()-1);
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
