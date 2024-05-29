package com.example.dernierespoirsae.algo;

import java.util.Objects;

public class Point {

    private int colonne, ligne;

    Point(int ligne, int colonne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public int getLigne() {
        return this.ligne;
    }
    public int getColonne() {
        return this.colonne;
    }

    public void setligne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

}