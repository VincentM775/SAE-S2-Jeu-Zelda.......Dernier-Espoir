package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;

public class RogerPNJ extends PNJ{

    public RogerPNJ(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y,"Roger", environnement, 1, 0, longTuile, largeTuile, nbTuile, 15, 15);
    }


    @Override
    public String texte() {
        return "Je sais où trouver une arme à feu !" +
                "\nRends-toi dans le sud-ouest de la ville," +
                "\nelle devrait se trouver dans une petite cabane";
    }

    @Override
    public String textePremiereInteraction() {
        return "Oh ! Un autre survivant ! Mon nom est Roger, et toi ?";
    }
}