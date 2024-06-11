package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.Main;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Joueur extends Acteur {

    private Inventaire inventaire;
    private ObjectProperty<Arme> armeEquipee;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile, VBox inventairePane, Pane armePaneEquipee) {
        super(Main.longeur/2,Main.largeur/2, "Johnny", environnement, 1000, 4, longTuile, largeTuile, nbTuile, 20, 26,0,0);
        this.inventaire = new Inventaire(environnement);
        this.armeEquipee =  new SimpleObjectProperty<>();
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public ObjectProperty<Arme> getArmeEquipeeProperty() {
        return armeEquipee;
    }

    public void setArmeEquipee(Arme armeEquipee) {
        this.armeEquipee.set(armeEquipee);
    }

    @Override
    public void agit() {
        seDeplacer();
        attaque();
        rechercheArme();
    }

    public void setArmeEquipee(String typeArme)  {
        for (int i = 0; i < inventaire.getListeArmeInventaire().size(); i++){
            if(inventaire.getListeArmeInventaire().get(i).getType().equals(typeArme)){
                setArmeEquipee(inventaire.getListeArmeInventaire().get(i));
            }
        }
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
        for (int i = 0; i < getEnvironnement().getListArmeEnvironnement().size(); i++) {
            if (estPresentDansRayonPixel(30,getEnvironnement().getListArmeEnvironnement().get(i).getX(),getEnvironnement().getListArmeEnvironnement().get(i).getY())){
                if (getTouche().contains("R")) {
                    getInventaire().getListeArmeInventaire().add(getEnvironnement().getListArmeEnvironnement().get(i));
                    getEnvironnement().getListArmeEnvironnement().remove(i);
                }
            }
        }
    }

    public void attaque(){

        if (getArmeEquipeeProperty() != null){ //Si on est équipé d'une arme

            //Si oui, on regarde si le click gauche est clické
            if (getClickSouris().contains("g")){

                //Pour chacun des acteurs de la map
                for (int i=0; i<getEnvironnement().getListActeurs().size();i++){

                    //sauf le joueur
                    if (getEnvironnement().getListActeurs().get(i) != this) {

                        //On regarde si l'acteur parcouru est dans un rayon de 32px autour du joueur
                        if (estPresentDansRayonPixel(32, getEnvironnement().getListActeurs().get(i).getX(), getEnvironnement().getListActeurs().get(i).getY())) {
                            //Fait perdre a l'acteur a coté du quelle on est autant de pv que l'atme équipée fait de dégat
                            getEnvironnement().getListActeurs().get(i).perdPV(getArmeEquipeeProperty().getValue().getDegats());
                        }
                    }
                }
                setClicks("");
            }
        }
    }
}
