package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Main;
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
        changement();
    }

    @Override
    public void changed(ObservableValue<? extends Number> obs, Number oldV, Number newV) {
        changement();
    }
    public void changement(){
        if (this.joueur.getX() > Main.longeur / 2 && this.joueur.getX() < this.joueur.getEnvironnement().getInfoTuile()[0] * this.joueur.getEnvironnement().getInfoTuile()[1] - Main.longeur / 2){
            this.principalPane.setTranslateX((double) Main.longeur / 2 - joueur.getX());
        }
    }
    public int getX(){
        return (int)this.principalPane.getTranslateX();
    }
}