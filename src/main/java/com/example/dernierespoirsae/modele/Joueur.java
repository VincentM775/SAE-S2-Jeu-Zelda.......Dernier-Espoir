package com.example.dernierespoirsae.modele;

public class Joueur extends Acteur{

    private final int vitesse = 1; // Vitesse de déplacement du joueur

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(340, 260, "Johnny", environnement, 20, 1, 7, longTuile, largeTuile, nbTuile, 15, 15);

    }

    @Override
    public void seDeplacer() {
        int dx = 0;
        int dy = 0;

        if (getDirection().contains("up") && getHitBox().collisionHaut()) {
            dy -= vitesse;
        }
        if (getDirection().contains("down") && getHitBox().collisionBas()) {
            dy += vitesse;
        }
        if (getDirection().contains("left") && getHitBox().collisionGauche()) {
            dx -= vitesse;
        }
        if (getDirection().contains("right") && getHitBox().collisionDroite()) {
            dx += vitesse;
        }

        deplacementActeur(dx, dy);
    }

    private void deplacementActeur(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
