package com.example.dernierespoirsae.modele;

public class Joueur extends Acteur{

    private final int SPEED = 1; // Vitesse de déplacement du joueur

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(340, 260, "Johnny", environnement, 20, 7, longTuile, largeTuile, nbTuile);
    }

    @Override
    public void seDeplacer() {
        int dx = 0;
        int dy = 0;

        if (getDirection().contains("up")) {
            dy -= SPEED;
        }
        if (getDirection().contains("down")) {
            dy += SPEED;
        }
        if (getDirection().contains("left")) {
            dx -= SPEED;
        }
        if (getDirection().contains("right")) {
            dx += SPEED;
        }

        moveCharacter(dx, dy);
    }

    private void moveCharacter(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
