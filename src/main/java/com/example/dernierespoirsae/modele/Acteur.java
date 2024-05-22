package com.example.dernierespoirsae.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Acteur {
    private static int id=0;
    private IntegerProperty xProperty, yProperty;
    private String nom;
    private Environnement environnement;
    private String direction;
    private int vie;
    private int nombreDeDegat;

    private int longTuile;
    private int largeTuile;
    private int nbTuile;

    public Acteur(int x,int y, String nom, Environnement environnement,int vie, int nombreDeDegat, int longTuile, int largeTuile, int nbTuile) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.nom = nom;
        this.environnement = environnement;
        this.vie = vie;
        this.nombreDeDegat = nombreDeDegat;
        this.longTuile = longTuile;
        this.largeTuile = largeTuile;
        this.nbTuile = nbTuile;
        id++;  //Id qui sauto incrémente à chaque création d'un acteur
        this.direction = "null";
    }
    public Acteur(String nom, Environnement environnement, int longTuile, int largeTuile, int nbTuile) {
        this(300,260,nom, environnement, 20, 5,longTuile, largeTuile, nbTuile);
    }

    protected int getId() {
        return id;
    }

    public void setX(int x){
        this.xProperty.setValue(x);
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

    public Environnement getEnvironnement() {
        return environnement;
    }
}