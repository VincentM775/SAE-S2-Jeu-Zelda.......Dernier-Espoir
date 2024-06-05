
package com.example.dernierespoirsae.modele.Armes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Armes {

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
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
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getXProperty() {
        return this.xProperty.getValue();
    }

    public IntegerProperty xProperty() {
        return this.xProperty;
    }

    public int getYProperty() {
        return this.yProperty.getValue();
    }

    public IntegerProperty yProperty() {
        return this.yProperty;
    }

    public void setxProperty(int x) {
        this.xProperty.setValue(x);
    }

    public void setyProperty(int y) {
        this.yProperty.setValue(y);
    }

    public int getId() {
        return id;
    }
}