package com.example.dernierespoirsae.modele.Objets.Armes;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Hache extends Arme {

    private static int quantiteStatic = 0;
    private IntegerProperty quantite;

    public Hache(int x, int y, Environnement environnement) {
        super(20, x, y,"hache", environnement,1,true);
        this.quantite = new SimpleIntegerProperty();
    }

    @Override
    public void incremeterDecremeterQuantiteInventaire(int val){
        this.quantite.setValue(quantiteStatic += val);
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    @Override
    public void agirAvecJoueur() {

    }

    @Override
    public void agir() {
        attaquer();
    }

    @Override
    public void attaquer() {
        boolean flag;
        if (getEnvironnement().getJoueur().getClickSouris().contains("g")) {
            getEnvironnement().getJoueur().setArmeALattaque(true);

            flag = true;
            //on parcours tout les acteurs
            for (int i=0; i<getEnvironnement().getListActeurs().size() && flag;i++){
                //sauf le joueur
                if (getEnvironnement().getListActeurs().get(i) != getEnvironnement().getJoueur()) {
                    if(getEnvironnement().getJoueur().estPresentDansRayonPixel(32,getEnvironnement().getListActeurs().get(i).getX(),getEnvironnement().getListActeurs().get(i).getY())){
                        getEnvironnement().getListActeurs().get(i).perdPV(getDegats());
                        flag = false;
                    }
                }
            }
            getEnvironnement().getJoueur().setClicks("");
        }
    }
}
