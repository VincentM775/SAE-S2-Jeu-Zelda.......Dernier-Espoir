package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public class ObservateurPositionY implements ChangeListener<Number> {

    private Pane principalPane;
    private Acteur joueur;

    public ObservateurPositionY(Pane pane, Acteur joueur){
        this.principalPane = pane;
        this.joueur = joueur;
    }

    @Override
    public void changed(ObservableValue<? extends Number> obs, Number oldV, Number newV) {
        int deplacement = newV.intValue() - oldV.intValue();

        if(this.joueur.getY() > 300 && this.joueur.getY() < this.joueur.getEnvironnement().getInfoTuile()[0] * this.joueur.getEnvironnement().getInfoTuile()[2] - 300) //300 = la moitié de la largeur de la Scene
            this.principalPane.setTranslateY(this.principalPane.getTranslateY() - deplacement);
    }
}
