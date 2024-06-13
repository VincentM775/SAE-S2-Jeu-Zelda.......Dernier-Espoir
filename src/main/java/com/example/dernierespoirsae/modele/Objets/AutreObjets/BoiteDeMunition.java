package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Objets;

public class BoiteDeMunition extends AutreObjets {
    private int quantite;
    private static int quantiteStatic=0;

    public BoiteDeMunition( Environnement environnement,int x, int y) {
        super(environnement,x,y,"boiteDeMunitions",(int) (Math.random()*7+5),false);
        this.quantite=0;
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
    public void agirAvecJoueur() {
        getEnvironnement().getJoueur().setQuantiteMunitions(getEnvironnement().getJoueur().getQuantiteMunitions()+getQuantiteObjets());
    }

    @Override
    public void agir() {
        //ne fais rien
    }
}
