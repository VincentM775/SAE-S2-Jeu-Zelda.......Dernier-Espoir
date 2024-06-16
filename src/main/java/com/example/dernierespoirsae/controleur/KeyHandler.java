package com.example.dernierespoirsae.controleur;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyHandler implements EventHandler<KeyEvent> {
    private final Environnement environnement;
    private final Set<KeyCode> pressedKeys;

    public KeyHandler(Environnement environnement) {
        this.environnement = environnement;
        this.pressedKeys = new HashSet<>();
    }

    @Override
    public void handle(KeyEvent keyEvent) {

        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            pressedKeys.add(keyEvent.getCode());
        }
        else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            pressedKeys.remove(keyEvent.getCode());
        }

        // Mise à jour de la direction du joueur en fonction des touches enfoncées
        String direction = "";
        String touche = "";

        if (pressedKeys.contains(KeyCode.Z)) {
            direction += "up";
        }
        if (pressedKeys.contains(KeyCode.S)) {
            direction += "down";
        }
        if (pressedKeys.contains(KeyCode.Q)) {
            direction += "left";
        }
        if (pressedKeys.contains(KeyCode.D)) {
            direction += "right";
        }
        if (pressedKeys.contains(KeyCode.R)) {
            touche += "R";
        }
        if (pressedKeys.contains(KeyCode.SPACE)) {
            touche += " ";
        }

        // Set de la nouvelle direction pour le joueur
        environnement.getJoueur().setDirection(direction);
        environnement.getJoueur().setTouche(touche);
    }
}
