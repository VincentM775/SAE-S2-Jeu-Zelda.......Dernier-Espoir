package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.Main;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class Joueur extends Acteur {


    private Inventaire inventaire;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile, VBox inventairePane) {
        super(Main.longeur/2,Main.largeur/2, "Johnny", environnement, 20, 4, 7, longTuile, largeTuile, nbTuile, 15, 15);
        this.inventaire = new Inventaire(inventairePane);
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    @Override
    public boolean seDeplacer() {
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
        return true;
    }
}
