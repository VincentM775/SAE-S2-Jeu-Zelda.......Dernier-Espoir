package com.example.dernierespoirsae.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Terrain {
    private ObservableList<Integer> terrain;

    public Terrain() {
        this.terrain = FXCollections.observableArrayList();
    }

    public void generTerrain(int n) {

        double chiffre;

        for (int i = 0; i < n; i++) {
            chiffre = Math.random();

            if (chiffre < 0.75){
                this.terrain.add(0);
            }
            else{
                this.terrain.add(1);
            }
        }
    }

    public ObservableList<Integer> getListTuiles() {
        return this.terrain;
    }

    public boolean estObstacle(int val){

        return !(this.terrain.get(val)==0 || this.terrain.get(val)==2);
    }
}
