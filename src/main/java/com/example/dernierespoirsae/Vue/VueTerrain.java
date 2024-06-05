package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Terrain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class VueTerrain {
    private Terrain terrain;
    private TilePane terrainPane;

    public VueTerrain(Terrain terrain, TilePane terrainPane) {
        this.terrainPane = terrainPane;
        this.terrain = terrain;
    }

    public void afficherTerrain() {

        Image pelouse = new Image("file:src/main/resources/com/example/dernierespoirsae/images/Grass_02_v2.png");
        Image mur = new Image("file:src/main/resources/com/example/dernierespoirsae/images/mur.png");

        for (int x = 0; x < this.terrain.getListTuiles().size(); x++) {

            ImageView imageView = new ImageView();

            switch (this.terrain.getListTuiles().get(x)) {
                case 0:
                    imageView.setImage(pelouse);
                    break;
                case 1:
                    imageView.setImage(mur);
            }

            imageView.setFitWidth(32);
            imageView.setFitHeight(32);

            terrainPane.getChildren().add(imageView);
        }
    }
}
