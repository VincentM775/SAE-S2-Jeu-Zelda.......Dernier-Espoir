package com.example.dernierespoirsae.controleur;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.MasticatorZ;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.Objects;

public class KeyHandler implements EventHandler<KeyEvent>{
    private Environnement environnement;
    
    public KeyHandler(Environnement environnement){
        this.environnement=environnement;
    }

    @Override
    public void handle(KeyEvent keyEvent){
        switch (keyEvent.getCode()){
            case Q :
                System.out.println("left");
                environnement.getJoueur().seDeplacer("left");
                break;

            case D :
                System.out.println("right");
                environnement.getJoueur().seDeplacer("right");
                break;

            case Z : 
                System.out.println("up");
                environnement.getJoueur().seDeplacer("up");
                break;

            case S : 
                System.out.println("down");
                environnement.getJoueur().seDeplacer("down");
                break;
            case M:
                System.out.println("déplacement ennemi");
                for (Acteur acteur : environnement.getActeurs()){
                    if (acteur instanceof MasticatorZ){
                        ((MasticatorZ) acteur).seDeplacerAleatoirement();
                    }
                }
            default:
                break;
        }
    }

}
