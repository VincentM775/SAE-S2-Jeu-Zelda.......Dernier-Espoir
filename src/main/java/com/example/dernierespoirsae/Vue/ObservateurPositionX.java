package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;

public class ObservateurPositionX implements ChangeListener<Number> {

    private Pane principalPane;
    private Acteur joueur;

    public ObservateurPositionX(Pane principalPane, Acteur joueur){
        this.principalPane = principalPane;
        this.joueur = joueur;
    }

    @Override
    public void changed(ObservableValue<? extends Number> obs, Number oldV, Number newV) {
        int deplacement = newV.intValue() - oldV.intValue();

        if(this.joueur.getX() > 550 && this.joueur.getX() < this.joueur.getEnvironnement().getInfoTuile()[0] * this.joueur.getEnvironnement().getInfoTuile()[1] - 550) //550 = la moitié de la longeur de la Scene
            this.principalPane.setTranslateX(this.principalPane.getTranslateX() - deplacement);
    }
}