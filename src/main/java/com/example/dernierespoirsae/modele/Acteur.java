package com.example.dernierespoirsae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;

public abstract class Acteur {

    private int vitesse = 1; // Vitesse de déplacement du joueur

    private IntegerProperty xProperty, yProperty;
    private String nom;
    private Environnement environnement;
    private String direction;
    private String derniereDirection;
    private IntegerProperty vie;
    private int nombreDeDegat;
    private static int idStatic=0;
    private int id;
    private int longTuile;
    private int largeTuile;
    private int nbTuile;
    private Collision collision;

    public Acteur(int x,int y, String nom, Environnement environnement, int vie, int vitesse, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile, int longBox, int largeBox) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.nom = nom;
        this.environnement = environnement;
        this.vitesse = vitesse;
        this.vie = new SimpleIntegerProperty(vie);
        this.nombreDeDegat = nombreDeDegat;
        this.longTuile = longTuile;
        this.largeTuile = largeTuile;
        this.nbTuile = nbTuile;
        collision = new Collision(longBox, largeBox, this);
        this.id=idStatic++;
        this.direction = "null";
        this.derniereDirection="null";
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getVie() {
        return vie.get();
    }

    public IntegerProperty vieProperty() {
        return vie;
    }

    public void perdPV(int decrement){
        this.vie.setValue(this.vie.getValue()-decrement);
    }
    public int getId() {
        return this.id;
    }

    public void setX(int x){
        this.xProperty.setValue(x);
    }

    public void meurtOuVie(){
        if(this.vie.getValue() <= 0) {
            environnement.getListActeurs().remove(this);
        }
    }
    public void setY(int y){
        this.yProperty.setValue(y);
    }

    public int getxProperty() {
        return xProperty.get();
    }

    public int getX() {
        return xProperty.getValue();
    }

    public final IntegerProperty xProperty() {
        return xProperty;
    }

    public int getyProperty() {
        return yProperty.get();
    }

    public int getY() {
        return yProperty.getValue();
    }

    public final IntegerProperty yProperty() {
        return yProperty;
    }


    public String getNom() {
        return nom;
    }

    public abstract void seDeplacer();
    public int getLongTuile() {
        return longTuile;
    }

    public int getLargeTuile() {
        return largeTuile;
    }

    public int getNbTuile() {
        return nbTuile;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }


    public Collision getHitBox() {
        return collision;
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }


    public String getDirection() {
        return direction;
    }

    public String getDerniereDirection() {
        return derniereDirection;
    }

    public void setDerniereDirection(String derniereDirection) {
        this.derniereDirection = derniereDirection;
    }
}