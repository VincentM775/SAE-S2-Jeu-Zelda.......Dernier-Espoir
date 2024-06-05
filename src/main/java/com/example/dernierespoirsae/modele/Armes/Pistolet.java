package com.example.dernierespoirsae.modele.Armes;

public class Pistolet extends Arme {

    private static int quantiteStatic = 1;
    private int quantite;

    public Pistolet(int x, int y) {
        super(100, x, y,"pistolet");
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
