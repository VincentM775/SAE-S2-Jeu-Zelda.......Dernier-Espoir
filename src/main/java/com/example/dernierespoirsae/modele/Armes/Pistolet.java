package com.example.dernierespoirsae.modele.Armes;

public class Pistolet extends Arme {

    private static int quantiteStatic = 0;
    private int quantite;

    public Pistolet(int x, int y) {
        super(100, x, y,"pistolet");
        this.quantite = 0;
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
