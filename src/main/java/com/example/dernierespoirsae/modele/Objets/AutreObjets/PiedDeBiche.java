package com.example.dernierespoirsae.modele.Objets.AutreObjets;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PiedDeBiche extends AutreObjets{
    private static int quantiteStatic = 0;
    private IntegerProperty quantite;
    private IntegerProperty valeurPorteAEnlever;
    public PiedDeBiche(Environnement environnement, int x, int y) {
        super(environnement, x, y, "piedDeBiche", 1, true);
        this.quantite = new SimpleIntegerProperty();
        this.valeurPorteAEnlever = new SimpleIntegerProperty();
    }

    @Override
    public void incremeterDecremeterQuantiteInventaire(int val){
        this.quantite.setValue( quantiteStatic+=val);
    }

    @Override
    public IntegerProperty quantiteProperty() {
        return this.quantite;
    }

    @Override
    public void agirAvecJoueur() {} //N'agi pas avec le joueur quand l'objet est récupérée

    @Override
    public void agir() {
        int tuileJoueurColonne,tuileJoueurLigne ;
        int valeurTuileDansListe;

        if (getEnvironnement().getJoueur().getTouche().contains("R")) {
            tuileJoueurColonne = getEnvironnement().getJoueur().getX() / getEnvironnement().getInfoTuile()[0];
            tuileJoueurLigne = getEnvironnement().getJoueur().getY() / getEnvironnement().getInfoTuile()[0];
            getEnvironnement().getJoueur().setArmeALattaque(true);

            //On parcourt toutes les cases autour du joueur
            for (int y = -1; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    if (tuileJoueurLigne + y >= 0 && tuileJoueurLigne + y < getEnvironnement().getInfoTuile()[1] && tuileJoueurColonne + x >= 0 && tuileJoueurColonne + x < getEnvironnement().getInfoTuile()[1]) {
                        //On récupère la valeur de la tuile dans la liste du terrain
                        valeurTuileDansListe = (tuileJoueurLigne+y)*getEnvironnement().getInfoTuile()[1]+(tuileJoueurColonne+x);
                        //On test s'il y a une porte sur une des cases autour du joueur (la porte a la valeur 1061)
                        if (getEnvironnement().getTerrain().getTerrain().get(valeurTuileDansListe)==(1061)){
                            //On met la valeur 0 pour débloquer le passage
                            getEnvironnement().getTerrain().getTerrain().set(valeurTuileDansListe,0);
                            setValeurPorteAEnlever(valeurTuileDansListe);
                        }
                    }
                }
            }
        }
    }

    public int getValeurPorteAEnlever() {
        return valeurPorteAEnlever.get();
    }

    public void setValeurPorteAEnlever(int valeurPorteAEnlever) {
        this.valeurPorteAEnlever.set(valeurPorteAEnlever);
    }

    public IntegerProperty valeurPorteAEnleverProperty() {
        return valeurPorteAEnlever;
    }
}
