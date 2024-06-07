package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.Main;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.scene.layout.VBox;

public class Joueur extends Acteur {


    private Inventaire inventaire;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile, VBox inventairePane) {
        super(Main.longeur/2,Main.largeur/2, "Johnny", environnement, 20, 4, longTuile, largeTuile, nbTuile, 15, 15);
        this.inventaire = new Inventaire(inventairePane);
    }

    public Inventaire getInventaire() {
        return this.inventaire;
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
                    if (estPresentDansRayonPixel(32,getEnvironnement().getListActeurs().get(i).getX(),getEnvironnement().getListActeurs().get(i).getY())) {
                        if (getTouche().contains(" ")){
                            getEnvironnement().getListActeurs().get(i).perdPV(getInventaire().getArmes().get(0).getDegats());
                        }
                    }
                }
            }
        }
    }
}