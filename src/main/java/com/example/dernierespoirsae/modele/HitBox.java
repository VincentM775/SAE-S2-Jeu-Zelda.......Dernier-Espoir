package com.example.dernierespoirsae.modele;

public class HitBox {

    private int longueur;
    private int hauteur;
    Acteur acteur;

    public HitBox(int longueur, int hauteur, Acteur acteur){
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.acteur = acteur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLongueur() {
        return longueur;
    }

//    public int hitBoxDroite(){
//     return acteur.getX() + longueur / 2;
//    }
//
//    public int hitBoxGauche(){
//        return acteur.getX() - longueur / 2;
//    }
//
//    public int hitBoxHaut(){
//        return acteur.getY() - hauteur / 2;
//    }
//
//    public int hitBoxBas(){
//        return acteur.getY() + hauteur / 2;
//    }

//    int position = ((this.getX() / this.longTuile) + (this.getY() / this.largeTuile * nbTuile));
//    if(position % 25 == 0 || position % 25 == 24 || position > 0 && position < 25 || position > 350 && position < 375 || environnement.getMap().getListTuiles().get(position) != 0)

    public boolean hitBoxDroite(){

        int positionPointHitBox = ((this.acteur.getX() + acteur.getVitesse() + longueur) / acteur.getLongTuile()) + ((this.acteur.getY()) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
        if(positionPointHitBox % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionPointHitBox) != 0)
            return false;

        for(int i = 1; i <= hauteur; i++){
            positionPointHitBox = ((this.acteur.getX() + acteur.getVitesse() + longueur) / acteur.getLongTuile()) + ((this.acteur.getY() + i) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
            if(positionPointHitBox % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionPointHitBox) != 0)
                return false;
        }

        return true;
    }

    public boolean hitBoxGauche() {

        int positionPointHitBox = ((this.acteur.getX() + acteur.getVitesse()) / acteur.getLongTuile()) + (this.acteur.getY() / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
        if(positionPointHitBox % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionPointHitBox) != 0)
            return false;

        for(int i = 1; i <= hauteur; i++){
            positionPointHitBox = ((this.acteur.getX() + acteur.getVitesse()) / acteur.getLongTuile()) + ((this.acteur.getY() + i) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
            if(positionPointHitBox % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionPointHitBox) != 0)
                return false;
        }

        return true;
    }

    public boolean hitBoxHaut() {

        int positionPointHitBox = ((this.acteur.getX() + acteur.getVitesse()) / acteur.getLongTuile()) + (this.acteur.getY() / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
        if(positionPointHitBox % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionPointHitBox) != 0)
            return false;

        for(int i = 1; i <= longueur; i++){
            positionPointHitBox = ((this.acteur.getX() + acteur.getVitesse() + i) / acteur.getLongTuile()) + (this.acteur.getY() / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
            if(positionPointHitBox % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionPointHitBox) != 0)
                return false;
        }

        return true;
    }

    public boolean hitBoxBas() {

        int positionPointHitBox = ((this.acteur.getX() + acteur.getVitesse()) / acteur.getLongTuile()) + ((this.acteur.getY() + this.hauteur) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
        if(positionPointHitBox % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionPointHitBox) != 0)
            return false;

        for(int i = 1; i <= hauteur; i++){
            positionPointHitBox = ((this.acteur.getX() + acteur.getVitesse() + i) / acteur.getLongTuile()) + ((this.acteur.getY()  + this.hauteur) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
            if(positionPointHitBox % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionPointHitBox) != 0)
                return false;
        }

        return true;
    }
}