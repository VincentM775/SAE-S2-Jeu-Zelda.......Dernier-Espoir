package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.Objets;

public abstract class AutreObjets extends Objets {

    public AutreObjets(Environnement environnement, int x, int y, String type,int quantiteObjet,boolean objetUnique) {
        super(environnement, x, y, type, quantiteObjet,objetUnique);

    }
}
