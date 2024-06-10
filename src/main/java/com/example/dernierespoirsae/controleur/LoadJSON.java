package com.example.dernierespoirsae.controleur;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadJSON {

    private ArrayList<Integer> terrainSol;
    private ArrayList<Integer> terrainColision;
    private int PrefColumns;
    private int PrefRows;

    public LoadJSON(String file){
        loadMap(file);
    }

    public void loadMap(String filename) {
        ArrayList<Integer> elementsMap = new ArrayList<>();
        ArrayList<Integer> elementsMap2 = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filename)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray layersArray = (JSONArray) jsonObject.get("layers");
            JSONObject firstLayer = (JSONObject) layersArray.get(0);
            JSONObject firstLayer2 = (JSONObject) layersArray.get(1);
            JSONArray dataArray = (JSONArray) firstLayer.get("data");
            JSONArray dataArray2 = (JSONArray) firstLayer2.get("data");
            this.PrefColumns = (int) (long) firstLayer.get("height");
            this.PrefRows = (int) (long) firstLayer.get("width");

            for (Object data : dataArray) {
                elementsMap.add(Math.toIntExact((Long) data));
            }
            for (Object data : dataArray2) {
                elementsMap2.add(Math.toIntExact((Long) data));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        this.terrainSol = elementsMap2;
        this.terrainColision = elementsMap;
    }

    public ArrayList<Integer> getMap() {
        return terrainSol;
    }
    public ArrayList<Integer> getMap2() {
        return terrainColision;
    }
    public int getPrefColumns() {
        return PrefColumns;
    }
    public int getPrefRows() {
        return PrefRows;
    }
}