package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Acteur{

    private ObservableList<Arme> armes;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(547 - 15,299 - 15, "Johnny", environnement, 20, 4, 7, longTuile, largeTuile, nbTuile, 15, 15);
        this.armes = FXCollections.observableArrayList();
    }

    public ObservableList<Arme> getArmes() {
        return armes;
    }

    public void setArmes(ObservableList<Arme> armes) {
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

        setX(getX() + dx);
        setY(getY() + dy);
    }
}
