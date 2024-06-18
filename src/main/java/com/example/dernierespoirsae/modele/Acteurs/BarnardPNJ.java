package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;

public class BarnardPNJ extends PNJ{

    public BarnardPNJ(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y,"Barnard", environnement, 1, 0, longTuile, largeTuile, nbTuile, 15, 15);
    }


    @Override
    public String texte() {
        return "Je sais où trouver un pied de biche pour ouvrir" +
                "\nles portes des maisons fermé. Rends toi dans la maison" +
                "\nvoisine à gauche, il devrait avoir une petite cabane";
    }

    @Override
    public String textePremiereInteraction() {
        return "Arghhh ! Oh salutions ! Aide moi ! Mon nom est barnard, et toi ?";
    }
}