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
    private IntegerProperty vie;
    private int nombreDeDegat;
    private static int idStatic=0;
    private int id;
    private int longTuile;
    private int largeTuile;
    private int nbTuile;

    public Acteur(int x,int y, String nom, Environnement environnement,int vie, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.nom = nom;
        this.environnement = environnement;
        this.vie = new SimpleIntegerProperty(vie);
        this.nombreDeDegat = nombreDeDegat;
        this.longTuile = longTuile;
        this.largeTuile = largeTuile;
        this.nbTuile = nbTuile;
        this.id=idStatic++;
        this.direction = "null";
    }
    public Acteur(String nom, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        this(300,260,nom, environnement, 20, 5,longTuile, largeTuile, nbTuile);
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

    public int getX() {
        return xProperty.getValue();
    }

    public final IntegerProperty xProperty() {
        return xProperty;
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

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

}