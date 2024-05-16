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
        int positionHautDroite = ((this.acteur.getX() + acteur.getVitesse() + longueur / 2) / acteur.getLongTuile()) + ((this.acteur.getY() - hauteur / 2) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
        System.out.println(positionHautDroite);;
        int positionBasDroite = ((this.acteur.getX() + acteur.getVitesse() + longueur / 2) / acteur.getLongTuile()) + ((this.acteur.getY() + hauteur / 2) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
        System.out.println(positionBasDroite);

        if(positionHautDroite % 25 == 24 || positionBasDroite % 25 == 24 || acteur.getEnvironnement().getMap().getListTuiles().get(positionHautDroite) != 0 || acteur.getEnvironnement().getMap().getListTuiles().get(positionBasDroite) != 0)
            return false;
        return true;
    }
    public boolean hitBoxGauche() {
        int positionHautGauche = ((this.acteur.getX() - acteur.getVitesse() - longueur / 2) / acteur.getLongTuile()) + ((this.acteur.getY() - hauteur / 2) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());
        int positionBasGauche = ((this.acteur.getX() - acteur.getVitesse() - longueur / 2) / acteur.getLongTuile()) + ((this.acteur.getY() + hauteur / 2) / this.acteur.getLargeTuile() * this.acteur.getNbTuile());

        if(positionHautGauche % 25 == 0|| positionBasGauche % 25 == 0 || acteur.getEnvironnement().getMap().getListTuiles().get(positionHautGauche) != 0 || acteur.getEnvironnement().getMap().getListTuiles().get(positionBasGauche) != 0)
            return false;
        return true;
    }
}