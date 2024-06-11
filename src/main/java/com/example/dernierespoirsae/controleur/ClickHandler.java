package com.example.dernierespoirsae.controleur;

import com.example.dernierespoirsae.Observateur.ObservateurPositionX;
import com.example.dernierespoirsae.Observateur.ObservateurPositionY;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class ClickHandler implements EventHandler<MouseEvent> {

    private final Environnement environnement;
    private ObservateurPositionX obsX;
    private ObservateurPositionY obsY;

    public ClickHandler(Environnement environnement, ObservateurPositionX obsX, ObservateurPositionY obsY) {
        this.environnement = environnement;
        this.obsX = obsX;
        this.obsY = obsY;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        environnement.getJoueur().setClicks("");

        String clickSouris = "";

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            // Ajouter "g" pour chaque clic gauche
            clickSouris += "g";
            environnement.getJoueur().setxDeLaSouris((int) mouseEvent.getX() + Math.abs(this.obsX.getX()));
            environnement.getJoueur().setyDeLaSouris((int) mouseEvent.getY() + Math.abs(this.obsY.getY()));


        }
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            // Ajouter "d" pour chaque clic droit
            clickSouris += "d";
        }

        // Mettre à jour la variable clicks dans l'environnement
        environnement.getJoueur().setClicks(clickSouris);
    }
}
