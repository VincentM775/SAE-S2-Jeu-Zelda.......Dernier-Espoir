package com.example.dernierespoirsae.modele.Armes;

public class Hache extends Arme {

    private static int quantiteStatic = 1;
    private int quantite;

    public Hache(int x, int y) {
        super(2, x, y,"hache");
        this.quantite =0;
    }

    @Override
    public void incremeterQuantiteInventaire(){
        this.quantite = quantiteStatic++;
    }

    @Override
    public int getQuantite() {
        return quantite;
    }
}
