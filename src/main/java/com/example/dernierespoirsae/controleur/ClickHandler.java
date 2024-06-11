package com.example.dernierespoirsae.controleur;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ClickHandler implements EventHandler<MouseEvent> {

    private VBox inventairePane;

    private final Environnement environnement;

    public ClickHandler(Environnement environnement, VBox inventairePane) {
        this.environnement = environnement;
        this.inventairePane = inventairePane;
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
        setPane(clickSouris);
    }

    public void setPane(String clickSouris) {

        for (int i = 0; i < inventairePane.getChildren().size(); i++) {
            // Ajouter un gestionnaire d'événements de clic de souris pour chaque Pane
            inventairePane.getChildren().get(i).setOnMouseClicked(event -> handleMouseClick(event, clickSouris));        }
    }

    private void handleMouseClick(MouseEvent event, String clickSouris ) {

        // Récupére le Pane sur lequel on a cliqué
        Pane clickedPane = (Pane) event.getSource();
        if(clickSouris.equals("d"))
            environnement.getJoueur().setArmeEquipee(clickedPane.getId());
    }

}
