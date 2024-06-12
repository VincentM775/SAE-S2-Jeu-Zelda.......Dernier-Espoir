package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Objets;

public abstract class AutreObjets extends Objets {

    public AutreObjets(Environnement environnement, int x, int y, String type) {
        super(environnement, x, y, type);

    }

    @Override
    public abstract void incremeterDecremeterQuantiteInventaire(int val);


    @Override
    public abstract int getQuantite();

    @Override
    public abstract void agirAvecJoueur();
}
