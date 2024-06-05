package com.example.dernierespoirsae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Terrain {
    private ObservableList<Integer> map;

    public Terrain() {
        this.map = FXCollections.observableArrayList();
    }

    public void generTerrain(int n) {
        double chiffre;
        for (int i = 0; i < n; i++) {
            chiffre = Math.random();
            if (chiffre < 0.75)
                this.map.add(0);
            else
                this.map.add(1);
        }
    }

    public ObservableList<Integer> getListTuiles() {
        return this.map;
    }

    public boolean estObstacle(int val){
        return !(this.map.get(val)==0 ||this.map.get(val)==2);
    }
}
