package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Arme {
    private String type;
    private IntegerProperty xProperty, yProperty;
    private int degats;
    private static int idStatic=0;
    private int id;
    private Environnement environnement;

    public Arme(int degats,int x,int y, String type, Environnement environnement) {
        this.type = type;
        this.degats = degats;
        this.id = idStatic++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.environnement = environnement;
    }


    public abstract void incremeterDecremeterQuantiteInventaire(int val);


    public abstract int getQuantite();

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getX() {
        return xProperty.get();
    }

    public IntegerProperty xProperty() {
        return xProperty;
    }

    public int getY() {
        return yProperty.get();
    }

    public IntegerProperty yProperty() {
        return yProperty;
    }

    public void setX(int x) {
        this.xProperty.set(x);
    }

    public void setY(int y) {
        this.yProperty.set(y);
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getDegats() {
        return degats;
    }
    public abstract void attaquer();

    public Environnement getEnvironnement() {
        return environnement;
    }
}
