package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Objets;

public class BoiteDeMunition extends Objets {
    private int quantiteDeMunition;
    private int quantite;
    private static int quantiteStatic=0;

    public BoiteDeMunition( Environnement environnement,int x, int y,String type) {
        super(environnement,x,y,type);
        this.quantiteDeMunition = (int) (Math.random()*7+5); //Nombre aléatoire entre 5 inclus et 12 exclus
        this.quantite=0;
    }
    @Override
    public void incremeterDecremeterQuantiteInventaire(int val){
        this.quantite = quantiteStatic+=val;
    }

    @Override
    public int getQuantite() {
        return 0;
    }


}
