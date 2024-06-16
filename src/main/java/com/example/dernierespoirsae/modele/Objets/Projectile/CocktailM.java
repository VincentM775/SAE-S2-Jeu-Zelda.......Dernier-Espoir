package com.example.dernierespoirsae.modele.Objets.Projectile;

import com.example.dernierespoirsae.modele.Acteurs.Acteur;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.EstPresentRayon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CocktailM extends Projectile{
    private BooleanProperty arriver;
    private BooleanProperty aExplosee;
    public CocktailM(int degats, Environnement environnement, Acteur acteurQuiALancer) {
        super(degats, environnement, acteurQuiALancer, 8, 320);
        this.aExplosee = new SimpleBooleanProperty(false);

    }

    @Override
    public int jeVaisEnX() {
        return getEnvironnement().getJoueur().getxDeLaSouris();
    }

    @Override
    public int jeVaisEnY() {
        return getEnvironnement().getJoueur().getyDeLaSouris();
    }

    @Override
    public void effet() {
        int tuileAcolonne = getX() / getEnvironnement().getInfoTuile()[0];
        int tuileAligne = getY() / getEnvironnement().getInfoTuile()[0];

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (tuileAligne + y >= 0 && tuileAligne + y < getEnvironnement().getInfoTuile()[1] && tuileAcolonne + x >= 0 && tuileAcolonne + x < getEnvironnement().getInfoTuile()[1]) {
                    if(getEnvironnement().getTerrain().estDestructible(valDansListe(y,x))) {
                        getEnvironnement().getTerrain().getTerrain().remove(valDansListe(y, x)); //case à remplacer selon x et y
                        getEnvironnement().getTerrain().getTerrain().add(valDansListe(y, x), 2); //case à remplacer selon x et y
                    }
                }
            }
        }
        setaExplosee(true);

        //mettre des dégâts aux acteurs présents dans la zone
        for (int i=0;i< getEnvironnement().getListActeurs().size();i++){
            if (EstPresentRayon.estPresentDansRayonPixel(64,getX(),getY(),0,0,getEnvironnement().getListActeurs().get(i).getX(),getEnvironnement().getListActeurs().get(i).getY())){
                getEnvironnement().getListActeurs().get(i).perdPV(getDegats()); //On met de gros dégats à l'ennemi toucher
            }
        }
    }
    public int valDansListe(int caseY,int caseX){
        return (((getY()/getEnvironnement().getInfoTuile()[0])+caseY)*getEnvironnement().getInfoTuile()[1]+(getX()/getEnvironnement().getInfoTuile()[0])+caseX);
    }

    public void setaExplosee(boolean aExplosee) {
        this.aExplosee.set(aExplosee);
    }

    public BooleanProperty aExploseeProperty() {
        return aExplosee;
    }

    public boolean getaExplosee() {
        return aExplosee.getValue();
    }

}
