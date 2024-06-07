package com.example.dernierespoirsae.modele;

import com.example.dernierespoirsae.controleur.LoadJSON;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Terrain {
    private ArrayList<Integer> terrain;
    public Terrain() {
        this.terrain = new ArrayList<>();
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

//    public ObservableList<Integer> getListTuiles() {
//        return this.terrain;
//    }

    public boolean estObstacle(int val){

        return !(this.terrain.get(val)==0 || this.terrain.get(val)==2);
    }

//    public void setTerrain(ArrayList<Integer> terrain) {
//        this.terrain = (ObservableList<Integer>) terrain;
//    }

//    public ObservableList<Integer> getTerrain() {
//        return terrain;
//    }

    public void setTerrain(ArrayList<Integer> terrain) {
        this.terrain = terrain;
    }

    public ArrayList<Integer> getTerrain() {
        return terrain;
    }

}
