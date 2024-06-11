package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Environnement;

public class Hache extends Arme {

    private static int quantiteStatic = 0;
    private int quantite;

    public Hache(int x, int y, Environnement environnement) {
        super(20, x, y,"hache", environnement);
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

    @Override
    public void attaquer() {
        //on parcours tout les acteurs
        for (int i=0; i<getEnvironnement().getListActeurs().size();i++){
            //sauf le joueur
            if (getEnvironnement().getListActeurs().get(i) != getEnvironnement().getJoueur()) {
                if(getEnvironnement().getJoueur().estPresentDansRayonPixel(32,getEnvironnement().getListActeurs().get(i).getX(),getEnvironnement().getListActeurs().get(i).getY())){
                    getEnvironnement().getListActeurs().get(i).perdPV(getDegats());
                }
            }
        }
    }
}
