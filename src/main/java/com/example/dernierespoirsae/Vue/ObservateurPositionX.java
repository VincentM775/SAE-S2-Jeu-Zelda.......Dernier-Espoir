package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.Main1;
import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Joueur;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
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
        if(this.joueur.getY() > Main1.hauteur/2 && this.joueur.getY() < this.joueur.getEnvironnement().getInfoTuile()[0] * this.joueur.getEnvironnement().getInfoTuile()[2] - Main1.hauteur/2) //300 = la moitié de la largeur de la Scene

        if(this.joueur.getX() > Main1.largeur/2 && this.joueur.getX() < this.joueur.getEnvironnement().getInfoTuile()[0] * this.joueur.getEnvironnement().getInfoTuile()[1] - Main1.largeur/2)
            this.principalPane.setTranslateX(Main1.largeur/2 - joueur.getX());
    }
}