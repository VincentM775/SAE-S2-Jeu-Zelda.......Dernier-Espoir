package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


public class Zamikaze extends Ennemi {
    private int compteurTemps=0;
    private BooleanProperty aExplosee; //Par défaut, il n'a pas explosé (0 pas explosé, 1 explosé)
    public Zamikaze(int x, int y, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(x, y, "Zamikaze", environnement, 100, 4, 10, longTuile, largeTuile, nbTuile, 5+(int) (Math.random()*2));
        this.aExplosee = new SimpleBooleanProperty(false);
    }


    public void testExplosion(int temps){
        int tuileAcolonne;
        int tuileAligne;

        if (joueurPresentDansRayonPixel(40)) {

            if (this.compteurTemps == 0) {
                this.compteurTemps = temps;
                this.compteurTemps += 75;
            }

            if (temps >= this.compteurTemps) {
                tuileAcolonne = getX() / getEnvironnement().getInfoTuile()[0];
                tuileAligne = getY() / getEnvironnement().getInfoTuile()[0];

                for (int y = -1; y <= 1; y++) {
                    for (int x = -1; x <= 1; x++) {
                        if (tuileAligne + y >= 0 && tuileAligne + y < getEnvironnement().getInfoTuile()[1] && tuileAcolonne + x >= 0 && tuileAcolonne + x < getEnvironnement().getInfoTuile()[1]) {
                            getEnvironnement().getTerrain().getListTuiles().remove(caseAExploser(y, x)); //case à remplacer selon x et y
                            getEnvironnement().getTerrain().getListTuiles().add(caseAExploser(y, x), 2); //case à remplacer selon x et y
                            setaExplosee(true);
//                        getEnvironnement().getJoueur().perdPV(10); //Le joueur prend des dégâts

                        }
                    }
                }
                this.compteurTemps = 0;
            }
        }
        else {
            this.compteurTemps = 0; //Si le joueur n'est plus dans la zone d'explosion, on réinitialise le compteur à 0
        }
    }
    public int caseAExploser(int caseY,int caseX){
        return (((getY()/getEnvironnement().getInfoTuile()[0])+caseY)*getEnvironnement().getInfoTuile()[1]+(getX()/getEnvironnement().getInfoTuile()[0])+caseX);
    }

    public void meurt() {
        this.perdPV(this.getVie()); //Zamikaze se suicide
    }

    public boolean aExploser(){
        return this.aExplosee.getValue();
    }

    public void setaExplosee(boolean aExplosee) {
        this.aExplosee.set(aExplosee);
    }

    public BooleanProperty getAExploseeProperty() {
        return this.aExplosee;
    }

    @Override
    public void agit() {
        testExplosion(getEnvironnement().getTemps());
    }
}

