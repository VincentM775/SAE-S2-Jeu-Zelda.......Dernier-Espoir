package com.example.dernierespoirsae.modele.Armes;

import com.example.dernierespoirsae.modele.Joueur;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Armes {

    private IntegerProperty xProperty, yProperty;
    private int degats;
    private static int idStatic=0;
    private int id;
    public Armes(int degats) {
        this.degats = degats;
        this.id = idStatic++;
        this.xProperty = new SimpleIntegerProperty();
        this.yProperty = new SimpleIntegerProperty();
    }

    public Armes(int degats, int x, int y) {
        this.degats = degats;
        this.id = idStatic++;
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
    }

    public int getDegats() {
        return degats;
    }

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
}
