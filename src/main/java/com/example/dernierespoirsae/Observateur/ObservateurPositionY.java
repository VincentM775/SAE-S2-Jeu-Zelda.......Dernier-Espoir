package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Main;
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
        changement();
    }

    @Override
    public void changed(ObservableValue<? extends Number> obs, Number oldV, Number newV) {
        changement();
    }
    public void changement(){
        if(this.joueur.getY() > Main.largeur/2 && this.joueur.getY() < this.joueur.getEnvironnement().getInfoTuile()[0] * this.joueur.getEnvironnement().getInfoTuile()[2] - Main.largeur/2) //300 = la moitié de la largeur de la Scene
            this.principalPane.setTranslateY((double) Main.largeur /2-joueur.getY());
    }
    public int getY(){
        return (int)this.principalPane.getTranslateY();
    }
}
