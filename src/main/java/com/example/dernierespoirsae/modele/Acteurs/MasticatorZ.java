package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;

public class MasticatorZ extends Ennemi {

    public MasticatorZ(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "MasticatorZ", environnement, 100, 2, 2, longTuile, largeTuile, nbTuile, 4+(int) (Math.random()*2),28,28);
    }

    @Override
    public void agit() {
        seDeplacer();
        if (joueurPresent()){
            if (joueurPresentDansRayonPixel(17))
//                if (getEnvironnement().getTemps()%20==0)
                    getEnvironnement().getJoueur().perdPV(getNombreDeDegat());
        }
    }
    @Override
    public void enleverEffet() {} //S'il y a des effets a enlever au moment de mourir, c'est à mettre ici
}
