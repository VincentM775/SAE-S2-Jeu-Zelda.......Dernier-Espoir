package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class PNJ extends Acteur{

    private boolean premiereInteraction;
    private BooleanProperty enInteraction;

    private String texte;

    public PNJ(int x, int y, String nom, Environnement environnement, int vie, int vitesse, int longTuile, int largeTuile, int nbTuile, int longBox, int largeBox) {
        super(x, y, nom, environnement, vie, vitesse, longTuile, largeTuile, nbTuile, longBox, largeBox);
        this.premiereInteraction = false;
        this.enInteraction = new SimpleBooleanProperty(false);
    }


    @Override
    public void meurtOuVie() {
        //??
    }

    @Override
    public void seDeplacer() {
        //Le PNJ ne se déplace pas
    }

    @Override
    public void agit() {
        if(getEnvironnement().getJoueur().getTouche().contains("R")){
            if(estPresentDansRayonPixel(45, getEnvironnement().getJoueur().getX(), getEnvironnement().getJoueur().getY())) {
                interaction();
            }
        }
    }

    public void interaction() {
        if(!premiereInteraction){
            setTexte(textePremiereInteraction());
            premiereInteraction=true;
        }
        else{
            setTexte(texte());
        }
        enInteraction.setValue(true);
    }

    public abstract String texte();
    public abstract String textePremiereInteraction();

    public void setPremiereInteraction(boolean premiereInteraction) {
        this.premiereInteraction=premiereInteraction;
    }

    public boolean getPremiereInteraction(){
        return this.premiereInteraction;
    }

    public void setEnInteraction(boolean enInteraction) {
        this.enInteraction.set(enInteraction);
    }

    public boolean getEnInteraction(){
        return this.enInteraction.getValue();
    }

    public BooleanProperty enInteraction(){
        return this.enInteraction;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getTexte() {
        return texte;
    }
}