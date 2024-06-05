package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Acteur.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Balle extends Projectile {

    public Balle(int degats, int x, int y, Environnement environnement, Acteur acteurQuiALancer) {
        super(degats, x, y, environnement,acteurQuiALancer);
    }


}
