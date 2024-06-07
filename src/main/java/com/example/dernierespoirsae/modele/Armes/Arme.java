package com.example.dernierespoirsae.modele.Armes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Arme {
    private String type;
    private IntegerProperty xProperty, yProperty;
    private int degats;
    private static int idStatic=0;
    private int id;

    public Arme(int degats, String type) {
        this.type = type;
        this.degats = degats;
        this.id = idStatic++;
        this.xProperty = new SimpleIntegerProperty();
        this.yProperty = new SimpleIntegerProperty();
    }

    public Arme(int degats, int x, int y, String type) {
        this.degats = degats;
        this.id = idStatic++;
        this.type = type;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
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

    @Override
    public String toString() {
        return "Arme{" +
                "type='" + type + '\'' +
                ", id=" + id +
                '}';
    }

    public String getType() {
        return type;
    }
}
