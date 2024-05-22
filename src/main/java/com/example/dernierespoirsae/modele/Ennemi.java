package com.example.dernierespoirsae.modele;

public class Ennemi extends Acteur {

    private int attentePourDeplacement = 0;
    private int vitesse = 1; // Vitesse de déplacement de l'ennemi en pixels par frame
    private int nombreDePixelDeplacer = 10; // Distance totale à parcourir en pixels
    private int dx = 0;
    private int dy = 0;
    private int deplacementRestant = 0;

    public Ennemi(int x, int y, String nom, Environnement environnement, int vie, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, nom, environnement, vie, nombreDeDegat, longTuile, largeTuile, nbTuile);
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
            setX(getX() + dx);
            setY(getY() + dy);
            deplacementRestant -= Math.abs(dx) + Math.abs(dy);
        }
    }

    private void seDeplacerAleatoirement() {
        int chanceDeDeplacement = (int) (Math.random() * 100) + 1;
        int chanceDeNouvelleDirection = (int) (Math.random() * 100) + 1;
        int directionAleatoire;
        String nouvelleDirection;

        if (chanceDeDeplacement <= 50) {//50% de chance de se déplacer aléatoirement
            chanceDeDeplacement = (int) (Math.random() * 100) + 1;

            if (chanceDeNouvelleDirection <= 75) {
                do {
                    directionAleatoire = (int) (Math.random() * 4) + 1;
                    nouvelleDirection = switch (directionAleatoire) {
                        case 1 -> "up";
                        case 2 -> "right";
                        case 3 -> "down";
                        case 4 -> "left";
                        default -> "null";
                    };
                } while (nouvelleDirection.equals(this.getDirection()));
                this.setDirection(nouvelleDirection);
            }

            definirDeplacement();
            this.setDirection("null");
        }
    }

    private void definirDeplacement() {
        dx = 0;
        dy = 0;
        deplacementRestant = nombreDePixelDeplacer;

        if (getDirection().contains("up")) {
            dy = -vitesse;
        }
        if (getDirection().contains("down")) {
            dy = vitesse;
        }
        if (getDirection().contains("left")) {
            dx = -vitesse;
        }
        if (getDirection().contains("right")) {
            dx = vitesse;
        }
    }


    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setNombreDePixelDeplacer(int nombreDePixelDeplacer) {
        this.nombreDePixelDeplacer = nombreDePixelDeplacer;
    }
}
