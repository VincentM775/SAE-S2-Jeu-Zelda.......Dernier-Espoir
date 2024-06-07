package com.example.dernierespoirsae.modele.Armes;

public class Hache extends Arme {

    private static int quantiteStatic = 0;
    private int quantite;

    public Hache(int x, int y) {
        super(20, x, y,"hache");
        this.quantite =0;
    }

    @Override
    public void incremeterDecremeterQuantiteInventaire(int val){
        this.quantite = quantiteStatic+=val;
    }

    @Override
    public int getQuantite() {
        return quantite;
    }
}
