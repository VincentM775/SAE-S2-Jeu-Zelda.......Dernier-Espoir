package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoiteDeMunition extends AutreObjets {
    private IntegerProperty quantite;
    private static int quantiteStatic=0;

    public BoiteDeMunition( Environnement environnement,int x, int y) {
        super(environnement,x,y,"boiteDeMunitions",(int) (Math.random()*7+5),false);
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
    public void agirAvecJoueur() {
        getEnvironnement().getJoueur().setQuantiteMunitions(getEnvironnement().getJoueur().getQuantiteMunitions()+getQuantiteObjets());
    }

    @Override
    public void agir() {
        //ne fais rien
    }
}
