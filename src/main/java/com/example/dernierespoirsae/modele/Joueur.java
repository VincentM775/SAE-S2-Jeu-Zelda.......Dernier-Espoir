package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Acteur{

    private final int vitesse = 1; // Vitesse de déplacement du joueur
    private ObservableList<Armes> armes;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(550 - 15,300 - 15, "Johnny", environnement, 20, 1, 7, longTuile, largeTuile, nbTuile, 15, 15);
        this.armes = FXCollections.observableArrayList();
    }

    public ObservableList<Armes> getArmes() {
        return armes;
    }

    public void setArmes(ObservableList<Armes> armes) {
        this.armes = armes;
    }

    @Override
    public void seDeplacer() {
        int dx = 0;
        int dy = 0;

        if (getDirection().contains("up") && getHitBox().collisionHaut()) {
            dy -= this.getVitesse();
        }
        if (getDirection().contains("down") && getHitBox().collisionBas()) {
            dy += this.getVitesse();
        }
        if (getDirection().contains("left") && getHitBox().collisionGauche()) {
            dx -= this.getVitesse();
        }
        if (getDirection().contains("right") && getHitBox().collisionDroite()) {
            dx += this.getVitesse();
        }

        // Création d'un tableau des distances à chaque fois que le joueur se déplace
        getEnvironnement().getBfs().lancementBFS();

        //AFFICHAGE DU BFS SUR LE TERMINAL
//        for (int[] tab : getEnvironnement().getBfs().getTableauDesDistances()) {
//            for (int val : tab) {
//                System.out.print(val + " ");
//            }
//            System.out.println();
//        }
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
