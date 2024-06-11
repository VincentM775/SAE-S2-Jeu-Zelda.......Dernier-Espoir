package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.Main;
import com.example.dernierespoirsae.Vue.VueArmeEquipee;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Joueur extends Acteur {

    private Pane armePaneEquipee;
    private Inventaire inventaire;

    private Arme armeEquipee;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile, VBox inventairePane, Pane armePaneEquipee) {
        super(Main.longeur/2,Main.largeur/2, "Johnny", environnement, 20, 4, longTuile, largeTuile, nbTuile, 15, 15);
        this.inventaire = new Inventaire(inventairePane);
        this.armeEquipee = null;
        this.armePaneEquipee = armePaneEquipee;
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

    public void setArmeEquipee(String typeArme)  {
        for (int i = 0; i < inventaire.getArmes().size(); i++){
            if(inventaire.getArmes().get(i).getType().equals(typeArme)){
                this.armeEquipee = inventaire.getArmes().get(i);
            }
        }
        new VueArmeEquipee(this.armeEquipee,this, this.armePaneEquipee);
    }

    public Arme getArmeEquipee() {
        return this.armeEquipee;
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

        if (getArmeEquipee() != null){ //Si on est équipé d'une arme

            //Si oui, on regarde si le click gauche est clické
            if (getClickSouris().contains("g")){
                armePaneEquipee.getChildren().get(0).rotateProperty().setValue(60);

                //Pour chacun des acteurs de la map
                for (int i=0; i<getEnvironnement().getListActeurs().size();i++){

                    //sauf le joueur
                    if (getEnvironnement().getListActeurs().get(i) != this) {

                        //On regarde si l'acteur parcouru est dans un rayon de 32px autour du joueur
                        if (estPresentDansRayonPixel(32, getEnvironnement().getListActeurs().get(i).getX(), getEnvironnement().getListActeurs().get(i).getY())) {
                            //Fait perdre a l'acteur a coté du quelle on est autant de pv que l'atme équipée fait de dégat
                            getEnvironnement().getListActeurs().get(i).perdPV(getArmeEquipee().getDegats());
                        }
                    }
                }
                setClicks("");
            }
        }
    }
}