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
        interragirPNJ();
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

    public void interragirPNJ(){
        if(getTouche().contains("R")){
            for(int i = 0; i < getEnvironnement().getListPNJ().size(); i++) {
                if(estPresentDansRayonPixel(32, getEnvironnement().getListPNJ().get(i).getX(), getEnvironnement().getListPNJ().get(i).getY())) {
                    getEnvironnement().getListPNJ().get(i).interaction();
                }
            }
        }
    }
}