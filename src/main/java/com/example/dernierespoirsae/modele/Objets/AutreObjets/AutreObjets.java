package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Objets;

public abstract class AutreObjets extends Objets {
    private int quantiteObjets;
    public AutreObjets(Environnement environnement, int x, int y, String type,int quantiteObjets) {
        super(environnement, x, y, type);
        this.quantiteObjets = quantiteObjets;
    }

    @Override
    public abstract void incremeterDecremeterQuantiteInventaire(int val);


    @Override
    public abstract int getQuantite();

    @Override
    public abstract void agirAvecJoueur();



    public int getQuantiteObjets() {
        return quantiteObjets;
    }

    public void setQuantiteObjets(int quantiteObjets) {
        this.quantiteObjets = quantiteObjets;
    }
}
