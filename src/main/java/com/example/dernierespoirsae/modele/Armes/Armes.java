package com.example.dernierespoirsae.modele.Armes;

import javafx.beans.property.IntegerProperty;

public class Armes {

    private IntegerProperty x;
    private IntegerProperty y;
    private int degats;
    private static int idStatic=0;
    private int id;

    public Armes(int degats) {
        this.degats = degats;
        this.id = idStatic++;
    }

    public Armes(int degats, int x, int y) {
        this.degats = degats;
        this.id = idStatic++;
        this.x.set(x);
        this.y.set(y);
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }
}
