package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Acteurs.Zamikaze;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueZamikaze extends VueZombie{

    public VueZamikaze(Pane persoPane,Pane barreViePane, TilePane terrainPane, Acteur acteur, Environnement environnement) {
        super(persoPane,barreViePane, terrainPane, acteur, environnement);
        ChangeListener<Boolean> explosion = ((obs,old,nouv)-> explosion());
        ((Zamikaze)acteur).getAExploseeProperty().addListener(explosion);

    }

    @Override
    public int definitionCouleur() {
        return 2;
    }
    public void explosion(){
        int tuileAcolonne = getActeur().getX() / getEnvironnement().getInfoTuile()[0];
        int tuileAligne = getActeur().getY() / getEnvironnement().getInfoTuile()[0];
        int tuilePositionEListe; //recupere la position de l'ennemi dans la liste
        addGifToPane(getActeur().getX(), getActeur().getY(), 96, "file:src/main/resources/com/example/dernierespoirsae/images/explosion.gif",480);

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (tuileAligne + y >= 0 && tuileAligne + y < getEnvironnement().getInfoTuile()[1] && tuileAcolonne + x >= 0 && tuileAcolonne + x < getEnvironnement().getInfoTuile()[1]) {
                    tuilePositionEListe = getEnvironnement().getInfoTuile()[1] * (tuileAligne + y) + (tuileAcolonne + x);
                    setImageAtIndex(tuilePositionEListe, "file:src/main/resources/com/example/dernierespoirsae/images/Grass_burned.png");
                }
            }
        }
        ((Zamikaze) getActeur()).meurt();

    }
}
