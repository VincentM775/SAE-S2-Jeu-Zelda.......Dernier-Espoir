package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;

public class ObjetCocktailMolotov extends AutreObjetsAvecQuantite{
    private int quantite;
    private static int quantiteStatic=0;

    public ObjetCocktailMolotov(Environnement environnement, int x, int y) {
        super(environnement, x, y, "cocktailMolotov", (int) (Math.random()*2+1));
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
        getEnvironnement().getJoueur().setQuantiteCocktailMolotov(getEnvironnement().getJoueur().getQuantiteCocktailMolotov()+getQuantiteObjets());
    }
}
