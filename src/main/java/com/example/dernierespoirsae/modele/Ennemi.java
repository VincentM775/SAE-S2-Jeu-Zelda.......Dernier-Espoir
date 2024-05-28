package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.algo.BFS;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Ennemi extends Acteur {

    private int attentePourDeplacement = 0;
    private int nombreDePixelDeplacer = 10; // Distance totale à parcourir en pixels
    private int dx = 0;
    private int dy = 0;
    private int deplacementRestant = 0;

    public Ennemi(int x, int y, String nom, Environnement environnement, int vie, int vitesse, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, nom, environnement, vie, vitesse, nombreDeDegat, longTuile, largeTuile, nbTuile);//15,15

    }

    @Override
    public void seDeplacer() {
        if (this.attentePourDeplacement <= 0) {
            seDeplacerAleatoirement();
            this.attentePourDeplacement = 30;
        } else {
            this.attentePourDeplacement--;
        }

        if (deplacementRestant > 0) {
            moveCharacter(dx, dy);
            deplacementRestant -= Math.abs(dx) + Math.abs(dy);
        }
        if (modeDeDeplacement==1){
            seDeplacerEnBFS();
//            if (this.attentePourDeplacement <= 0) {
//                seDeplacerEnBFS();
//                this.attentePourDeplacement = 30;
//            } else
//                this.attentePourDeplacement--;
//            if (deplacementRestant > 0) {
//                seDeplacerEnBFS();
//                deplacementRestant -= Math.abs(dx) + Math.abs(dy);
//            }
        }
    }

    private void seDeplacerAleatoirement() {
        int chanceDeDeplacement = (int) (Math.random() * 100) + 1;
        int chanceDeNouvelleDirection = (int) (Math.random() * 100) + 1;
        int directionAleatoire;
        String nouvelleDirection;

        if (chanceDeDeplacement <=100) { //75% de chance de se déplacer
            if (chanceDeNouvelleDirection <= 75) { //75% de chance de changer de direction
                do {//Tant que l'on a pas changé de direction
                    directionAleatoire = (int) (Math.random() * 4) + 1;
                    nouvelleDirection = switch (directionAleatoire) {
                        case 1 -> "up";
                        case 2 -> "right";
                        case 3 -> "down";
                        case 4 -> "left";
                        default -> "null";
                    };
                }while (nouvelleDirection.equals(this.getDerniereDirection()));
                setUneDirection(nouvelleDirection);
            }
            //sinon il se déplace dans la meme direction
            deplacement();
            this.setDirection("null");
        }
    }
    public void setUneDirection(String direction){
        this.setDirection(direction);
        this.setDerniereDirection(this.getDirection());
    }

    private void deplacement() {
        this.setDirection(this.getDerniereDirection());
        dx = 0;
        dy = 0;
        deplacementRestant = nombreDePixelDeplacer;

        if (getDirection().contains("up")){//&& getHitBox().collisionHaut()
            System.out.println("test Haut");
            dy = -this.getVitesse();
        }
        if (getDirection().contains("down")) {// && getHitBox().collisionBas()
            System.out.println("test Bas");
            dy = this.getVitesse();
        }
        if (getDirection().contains("left") ) {//&& getHitBox().collisionGauche()
            System.out.println("test Gauche");
            dx = -this.getVitesse();
        }
        if (getDirection().contains("right") ) {//&& getHitBox().collisionDroite()
            System.out.println("test Droite");
            dx = this.getVitesse();
        }
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public void setNombreDePixelDeplacer(int nombreDePixelDeplacer) {
        this.nombreDePixelDeplacer = nombreDePixelDeplacer;
    }
}
