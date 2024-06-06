package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Acteurs.*;
import com.example.dernierespoirsae.modele.Acteurs.Zamikaze;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;

public class VueZamikaze extends VueActeur{

    public VueZamikaze(Pane persoPane, TilePane terrainPane, Acteur acteur, Environnement environnement) {
        super(persoPane, terrainPane, acteur, environnement);


    }

    @Override
    public int definitionCouleur() {
        return 2;
    }
    public void explosion(){
            //TODO REFAIRE LA METHODE DU JOUEUR PRESENT DANS UN CERTAIN RAYON (FINI voir class ennemi)
//        Rectangle joueur = (Rectangle) getPersoPane().lookup("#" + getEnvironnement().getListActeurs().get(i).getId());
//
//        //verifie si un acteur est dans un rayon de 'zoneDegat' autours du joueur
//        if ((getEnvironnement().getJoueur().getY() + joueur.getWidth() + rayonInteraction) >= getEnvironnement().getListActeurs().get(i).getY() && ((environnement.getJoueur().getY() - joueur.getWidth() - rayonInteraction) <= environnement.getListActeurs().get(i).getY()) && (environnement.getJoueur().getX() + joueur.getWidth() + rayonInteraction) >= environnement.getListActeurs().get(i).getX() && ((environnement.getJoueur().getX() - joueur.getWidth() - rayonInteraction) <= environnement.getListActeurs().get(i).getX())) {
////            ((Zamikaze) getActeur().explose(temps);

//            if (((Zamikaze) getActeur().aExploser()){//Si le Zamikaze explose
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
//                ((Zamikaze) getActeur().meurt();
                //Faire un ajout de l'acteur dans une observable liste et dès que la liste détecte un ajout, il fait l'animation
                getActeur().meurtOuVie();


    }
}
