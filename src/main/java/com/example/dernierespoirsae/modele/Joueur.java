package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Acteur{

    private ObservableList<Armes> armes;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(547 - 15,299 - 15, "Johnny", environnement, 20, 8, 7, longTuile, largeTuile, nbTuile, 15, 15);
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

        if (getDirection().contains("up") && getHitBox().collisionHaut(getVitesse())) {
            dy -= this.getVitesse();
            getEnvironnement().getBfs().lancementBFS();
        }
        if (getDirection().contains("down") && getHitBox().collisionBas(getVitesse())) {
            dy += this.getVitesse();
            getEnvironnement().getBfs().lancementBFS();
        }
        if (getDirection().contains("left") && getHitBox().collisionGauche(getVitesse())) {
            dx -= this.getVitesse();
            getEnvironnement().getBfs().lancementBFS();
        }
        if (getDirection().contains("right") && getHitBox().collisionDroite(getVitesse())) {
            dx += this.getVitesse();
            getEnvironnement().getBfs().lancementBFS();
        }

        //AFFICHAGE DU BFS SUR LE TERMINAL
//        for (int[] tab : getEnvironnement().getBfs().getTableauDesDistances()) {
//            for (int val : tab) {
//                System.out.print(val + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();
        setX(getX() + dx);
        setY(getY() + dy);
    }
    public boolean meurt(){
        return this.getVie()<=0;
    }
}
