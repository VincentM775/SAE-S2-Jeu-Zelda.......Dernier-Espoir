package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.Main;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends Acteur {

    private ObservableList<Arme> armes;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(Main.longeur/2,Main.largeur/2, "Johnny", environnement, 1000, 4, longTuile, largeTuile, nbTuile, 20, 26,0,0);
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
//        if (getDirection().contains("") || getDirection().contains("leftright") || getDirection().contains("updown") || ) {
//
//        }

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
        if (!getInventaire().getArmes().isEmpty()){ //Si l'inventaire d'arme n'est pas vide
            //On parcourt tous les acteurs
            for (int i=0; i<getEnvironnement().getListActeurs().size();i++){
                //sauf le joueur
                if (getEnvironnement().getListActeurs().get(i) != this){
                    //On regarde si l'acteur parcouru est dans un rayon de 32px autour du joueur
                    if (estPresentDansRayonPixel(32,getEnvironnement().getListActeurs().get(i).getX(),getEnvironnement().getListActeurs().get(i).getY())){
                        //Si oui, on regarde si le click gauche est clické
                        if (getClickSouris().contains("g")){
                            getEnvironnement().getListActeurs().get(i).perdPV(10);
                            //si oui, il l'acteur dans la zone perd 10pv x (fois) le nombre de dégâts de l'arme
                            if (getEnvironnement().getTemps()%getInventaire().getArmes().get(0).getDegats()==0) {
                                setClicks(""); //On réinitialise la variable des clicks
                            }
                        }
                    }
                }

            }
        }
    }
}
