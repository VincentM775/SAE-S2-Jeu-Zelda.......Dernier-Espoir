package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;

public abstract class AutreObjetsAvecQuantite extends AutreObjets{
    private int quantiteObjets;
    public AutreObjetsAvecQuantite(Environnement environnement, int x, int y, String type, int quantiteObjets) {
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
