package com.example.dernierespoirsae.controleur;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class ClickHandler implements EventHandler<MouseEvent> {

    private final Environnement environnement;

    public ClickHandler(Environnement environnement) {
        this.environnement = environnement;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        environnement.getJoueur().setClicks("");

        String clickSouris = "";

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            // Ajouter "g" pour chaque clic gauche
            clickSouris += "g";
        }
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            // Ajouter "d" pour chaque clic droit
            clickSouris += "d";
        }

        // Mettre à jour la variable clicks dans l'environnement
        environnement.getJoueur().setClicks(clickSouris);
    }
}
