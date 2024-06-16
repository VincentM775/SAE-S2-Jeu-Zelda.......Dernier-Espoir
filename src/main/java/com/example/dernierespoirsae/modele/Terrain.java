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

    public boolean estObstacle(int val){

        return !(this.terrain.get(val)==0 || this.terrain.get(val)==2);
    }
    public boolean estArbres(int val){
        int[] tabArbres = new int[]{680,681,682,683,684,685,686,687,736,737,738,739,740,741,742,743,792,793,794,795,796,797,798,799,848,849,850,851,852,853,854,855,904,905,906,907,908,909,910,911,960,961,962,963,964,965,966,967,1016,1017,1018,1019,1020,1021,1022,1023,1072,1073,1074,1075,1076,1077,1078,1079,1128,1129,1130,1131,1132,1133,1134,1135,1184,1185,1186,1187,1188,1189,1190,1191};
        for (int valArbre : tabArbres){
            if (this.terrain.get(val)==valArbre+1){
                return true;
            }
        }
       return false;
    }

    public void setTerrain(ArrayList<Integer> terrain) {
        this.terrain = terrain;
    }

    public ArrayList<Integer> getTerrain() {
        return terrain;
    }

}
