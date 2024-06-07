package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Terrain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class VueTerrain {
    private Terrain terrain;
    private TilePane terrainPane;
    private Image[] tiles;
    private ArrayList<Integer> m2;

    public VueTerrain(Terrain terrain, TilePane terrainPane,ArrayList<Integer> m1, ArrayList<Integer> m2) {
        this.terrainPane = terrainPane;
        this.terrain = terrain;
        this.m2=m2;
        terrain.setTerrain(m1);

        Image tileset = new Image("file:src/main/resources/com/example/dernierespoirsae/images/passages.png", 320, 520, false, false);

        int tileWidth = 32;
        int tileHeight = 32;
        int colonne = (int) (tileset.getWidth() / tileWidth);
        int ligne = (int) (tileset.getHeight() / tileHeight);
        tiles = new Image[colonne * ligne];

        for (int y = 0; y < ligne; y++) {
            for (int x = 0; x < colonne; x++) {
                tiles[y * colonne + x] = new WritableImage(tileset.getPixelReader(), (x * tileWidth), (y * tileHeight), tileWidth, tileHeight);
            }
        }
        afficherTerrain();

    }

    public void afficherTerrain() {
        ArrayList<Integer> m1 = terrain.getTerrain();
        this.terrain.setTerrain(m1);

        for (int i = 0; i < m1.size(); i++) {
            ImageView imageView = new ImageView();
            ImageView imageView2 = null;
            imageView.setId(""+i);

            int tileIndex = m1.get(i);
            int tileIndex2 = m2.get(i);
            if (tileIndex >= 1 && tileIndex <= tiles.length) // Assurez-vous que tileIndex est dans les limites
                imageView.setImage(tiles[tileIndex - 1]);
            if (tileIndex2 >= 1 && tileIndex2 <= tiles.length) {
                imageView2 = new ImageView();
                imageView2.setId(""+i);
                imageView2.setImage(tiles[tileIndex2 - 1]);
            }
            if (tileIndex2 != 0)
                this.terrainPane.getChildren().add(new StackPane(imageView2, imageView));
            else this.terrainPane.getChildren().add(new StackPane(imageView));
        }
    }

//    public void afficherTerrain() {
//
//        Image pelouse = new Image("file:src/main/resources/com/example/dernierespoirsae/images/Grass_02_v2.png");
//        Image mur = new Image("file:src/main/resources/com/example/dernierespoirsae/images/mur.png");
//
//        for (int x = 0; x < this.terrain.getListTuiles().size(); x++) {
//
//            ImageView imageView = new ImageView();
//
//            switch (this.terrain.getListTuiles().get(x)) {
//                case 0:
//                    imageView.setImage(pelouse);
//                    break;
//                case 1:
//                    imageView.setImage(mur);
//            }
//
//            imageView.setFitWidth(32);
//            imageView.setFitHeight(32);
//
//            terrainPane.getChildren().add(imageView);
//        }
//    }
}
