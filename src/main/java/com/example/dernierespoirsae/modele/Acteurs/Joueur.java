package com.example.dernierespoirsae.modele.Acteurs;

import com.example.dernierespoirsae.Main;
import com.example.dernierespoirsae.modele.Objets.Armes.Arme;
import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Inventaire;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Joueur extends Acteur {

    private Inventaire inventaire;
    private ObjectProperty<Objets> objetsEquipee;
    private int quantiteMunitions;

    public Joueur(Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        super(Main.longeur/2,Main.largeur/2, "Johnny", environnement, 1000, 4, longTuile, largeTuile, nbTuile, 20, 26);
        this.inventaire = new Inventaire(environnement);
        this.objetsEquipee =  new SimpleObjectProperty<>();
        this.quantiteMunitions = 0;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public ObjectProperty<Objets> getArmeEquipeeProperty() {
        return objetsEquipee;
    }
    public Objets getArmeEquipee() {
        return objetsEquipee.getValue();
    }

    public void setArmeEquipee(Objets objetsEquipe) {
        this.objetsEquipee.set(objetsEquipe);
    }

    @Override
    public void agit() {
        seDeplacer();
        attaque();
        rechercheObjets();
    }

    public void setArmeEquipee(String typeArme)  {
        for (int i = 0; i < inventaire.getListeObjetsInventaire().size(); i++){
            if(inventaire.getListeObjetsInventaire().get(i).getType().equals(typeArme)){
                setArmeEquipee(inventaire.getListeObjetsInventaire().get(i));
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
    public void rechercheObjets() {
        for (int i = 0; i < getEnvironnement().getlistObjetsEnvironnement().size(); i++) {
            if (estPresentDansRayonPixel(30,getEnvironnement().getlistObjetsEnvironnement().get(i).getX(),getEnvironnement().getlistObjetsEnvironnement().get(i).getY())){
                if (getTouche().contains("R")) {
                    getEnvironnement().getlistObjetsEnvironnement().get(i).agirAvecJoueur();
                    getInventaire().getListeObjetsInventaire().add(getEnvironnement().getlistObjetsEnvironnement().get(i));
                    getEnvironnement().getlistObjetsEnvironnement().remove(i);
                }
            }
        }
    }

    public void attaque(){
        if (getArmeEquipee() != null){ //Si on est équipé d'une arme
            //Si oui, on regarde si le click gauche est clické
            if (getClickSouris().contains("g")){
                    setArmeALattaque(true);
                    ((Arme) getArmeEquipee()).attaquer(); //On utilise notre arme
                    setClicks("");

            }
        }
    }

    public int getQuantiteMunitions() {
        return quantiteMunitions;
    }

    public void setQuantiteMunitions(int quantiteMunitions) {
        this.quantiteMunitions = quantiteMunitions;
    }
}
