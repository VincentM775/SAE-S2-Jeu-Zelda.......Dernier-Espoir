package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public abstract class VueZombie extends VueActeur{
    public VueZombie(Pane persoPane, Pane barreViePane, TilePane terrainPane, Acteur acteur, Environnement environnement) {
        super(persoPane, barreViePane, terrainPane, acteur, environnement);
    }


    @Override
    public void definitionbarreDeVie() {
        getBarreVie().getStyleClass().add("health-bar-ennemi");
        getBarreVie().translateXProperty().bind(getActeur().xProperty().subtract(20 - getActeur().getHitBox().getLongueur() / 2));
        getBarreVie().translateYProperty().bind(getActeur().yProperty().subtract(15));
        getPersoPane().getChildren().add(getBarreVie());
    }

}
