package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueMap {
    private Map map;
    private TilePane mapPane;

    public VueMap(Map map, TilePane mapPane) {
        this.mapPane = mapPane;
        this.map = map;
    }

    public void afficherMap() {
        for (int x = 0; x < this.map.getListTuiles().size(); x++) {
            ImageView imageView = new ImageView();
            Image pelouse = new Image("file:src/main/resources/com/example/dernierespoirsae/images/Grass_02_v2.png");
            switch (this.map.getListTuiles().get(x)) {
                case 0:
                    imageView.setImage(pelouse);
                    imageView.setFitWidth(39);
                    imageView.setFitHeight(39);
                    break;
            }
            mapPane.getChildren().add(imageView);
        }
    }
}
