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

    public int[] getPoint(){
        int[] point = new int[2];
        point[0]=this.ligne;
        point[1]=this.colonne;
        return point;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        Point point = (Point) o;
//        return x == point.x && y == point.y;
//    }

}