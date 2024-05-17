package com.example.dernierespoirsae.controleur;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.MasticatorZ;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class KeyHandler implements EventHandler<KeyEvent>{
    private Environnement environnement;
    
    public KeyHandler(Environnement environnement){
        this.environnement=environnement;
    }

    @Override
    public void handle(KeyEvent keyEvent){
//        switch (keyEvent.getCode()){
//            case Q :
//                System.out.println("left");
//                environnement.getJoueur().setDirection("left");
//                break;
//
//            case D :
//                System.out.println("right");
//                environnement.getJoueur().setDirection("right");
//                break;
//
//            case Z :
//                System.out.println("up");
//                environnement.getJoueur().setDirection("up");
//                break;
//
//            case S :
//                System.out.println("down");
//                environnement.getJoueur().setDirection("down");
//                break;
//
//            default:
//                break;
//        }
        if(keyEvent.getCode().equals(KeyCode.Q)) {

            System.out.println("left");
            environnement.getJoueur().setDirection("left");
        }
        if(keyEvent.getCode().equals(KeyCode.D)) {

            System.out.println("right");
            environnement.getJoueur().setDirection("right");
        }
        if(keyEvent.getCode().equals(KeyCode.Z)) {

            System.out.println("up");
            environnement.getJoueur().setDirection("up");
        }
        if(keyEvent.getCode().equals(KeyCode.S)) {

            System.out.println("down");
            environnement.getJoueur().setDirection("down");
        }
    }
//    private void update() {
//        double dx = 0, dy = 0;
//
//        if (pressedKeys.contains(KeyCode.Z)) {
//            dy -= 5;
//        }
//        if (pressedKeys.contains(KeyCode.S)) {
//            dy += 5;
//        }
//        if (pressedKeys.contains(KeyCode.Q)) {
//            dx -= 5;
//        }
//        if (pressedKeys.contains(KeyCode.D)) {
//            dx += 5;
//        }
//
//        moveCharacter(dx, dy);
//    }
//
//    private void moveCharacter(double dx, double dy) {
//        environnement.getJoueur().xProperty().setValue(environnement.getJoueur().getX()+dx);
//        environnement.getJoueur().yProperty().setValue(environnement.getJoueur().getX()+dy);
//
//    }

}
