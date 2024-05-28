package com.example.dernierespoirsae.modele;

public class Joueur extends Acteur{

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(340, 260, "Johnny", environnement, 20,2, 7, longTuile, largeTuile, nbTuile);
    }

    @Override
    public void seDeplacer() {
        int dx = 0;
        int dy = 0;
        System.out.println(getDirection());
        if (getDirection().contains("up")) {
            dy -= this.getVitesse();
        }
        if (getDirection().contains("down")) {
            dy += this.getVitesse();
        }
        if (getDirection().contains("left")) {
            dx -= this.getVitesse();
        }
        if (getDirection().contains("right")) {
            dx += this.getVitesse();
        }

        // Démarrer la recherche récursive
       getEnvironnement().getBfs().lancementBFS();
        for (int[] tab : getEnvironnement().getBfs().getTableauDesDistances()) {
            for (int val : tab) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
