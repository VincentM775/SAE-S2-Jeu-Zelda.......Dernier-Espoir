package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.Main;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Acteur {

    private ObservableList<Arme> armes;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(Main.longeur/2,Main.largeur/2, "Johnny", environnement, 100, 4, longTuile, largeTuile, nbTuile, 15, 15);
        this.armes = FXCollections.observableArrayList();
    }

    public ObservableList<Arme> getArmes() {
        return armes;
    }

    public void setArmes(ObservableList<Arme> armes) {
        this.armes = armes;
    }

    @Override
    public void agit() {
        seDeplacer();
        attaque();
        rechercheArme();
    }

    @Override
    public void meurtOuVie() {
        if (getVie()<=0)
            getEnvironnement().setJoueur(null);
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
    public void rechercheArme() {
        for (int i = 0; i < getEnvironnement().getListArmes().size(); i++) {
            if (estPresentDansRayonPixel(30,getEnvironnement().getListArmes().get(i).getX(),getEnvironnement().getListArmes().get(i).getY())){
                if (getTouche().contains("R")) {
                    getEnvironnement().getJoueur().getInventaire().getArmes().add(getEnvironnement().getListArmes().get(i));
                    getInventaire().getArmes().add(getEnvironnement().getListArmes().get(i));
                    getEnvironnement().getListArmes().remove(i);
                }
            }
        }
    }

    public void attaque(){
        if (!getInventaire().getArmes().isEmpty()){
            for (int i=0; i<getEnvironnement().getListActeurs().size();i++){
                if (getEnvironnement().getListActeurs().get(i) != this){
                    if (estPresentDansRayonPixel(32,getEnvironnement().getListActeurs().get(i).getX(),getEnvironnement().getListActeurs().get(i).getY())){
                        if (getTouche().contains(" "))
                            getEnvironnement().getListActeurs().get(i).perdPV(getInventaire().getArmes().get(0).getDegats());
                    }
                }

            }
        }
    }
}
