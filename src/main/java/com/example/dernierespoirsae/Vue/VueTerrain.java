package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Environnement;
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
    private ArrayList<Integer> terrainFond;
    private ArrayList<Integer> terrainAutres;
    private Environnement environnement;

    public VueTerrain(Terrain terrain, TilePane terrainPane, ArrayList<Integer> terrainColision, ArrayList<Integer> terrainFond,ArrayList<Integer> terrainAutres, Environnement environnement) {
        this.terrainPane = terrainPane;
        this.terrain = terrain;
        this.terrainFond=terrainFond;
        this.terrainAutres = terrainAutres;
        this.environnement = environnement;
        terrain.setTerrain(terrainColision);

        Image tileset = new Image("file:src/main/resources/com/example/dernierespoirsae/tiles.png", 1792, 736, false, false);

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
        ArrayList<Integer> mapColision = terrain.getTerrain(); //On récupère la map sans le sol (que les colision)

        for (int i = 0; i < mapColision.size(); i++) {
            ImageView imageView = new ImageView();
            ImageView imageView2 = null;
            ImageView imageView3 = null;
            imageView.setId(""+i);

            int tileIndex = mapColision.get(i);
            int tileIndex2 = terrainAutres.get(i);
            int tileIndex3 = terrainFond.get(i);

            if (tileIndex >= 1 && tileIndex <= tiles.length) // Assurez-vous que tileIndex est dans les limites
                imageView.setImage(tiles[tileIndex-6]);

            if (tileIndex2 >= 1 && tileIndex2 <= tiles.length) {
                imageView2 = new ImageView();
                imageView2.setImage(tiles[tileIndex3-6]);
            }
            if (tileIndex3 >= 1 && tileIndex3 <= tiles.length) {
                imageView3 = new ImageView();
                imageView3.setImage(tiles[tileIndex3-6]);
            }

            StackPane stackpane = new StackPane();
//            stackpane1.getChildren().add()

            if (tileIndex2 != 0) {
                stackpane.getChildren().add(imageView2);
//                (new StackPane(imageView2, imageView));
            }
            if (tileIndex3 != 0) {
                stackpane.getChildren().add(imageView3);
//                this.terrainPane.getChildren().add(new StackPane(imageView2, imageView));
            }
            stackpane.getChildren().add(imageView);
//                this.terrainPane.getChildren().add(new StackPane(imageView));
            this.terrainPane.getChildren().add(stackpane);
        }
    }

    public Image obtenirImageTerrain(int ligne, int colonne) {
        int index = ligne * environnement.getInfoTuile()[1] + colonne;
        int tileIndex;

        // si index est valide
        if (index >= 0 && index < terrain.getTerrain().size()) {
            // recupère l'index de la tuile dans la liste représentant le terrain
             tileIndex = terrainFond.get(index)-6;

            // si que tileIndex est dans les limites
            if (tileIndex >= 1 && tileIndex <= tiles.length) {
                // Retournez l'image correspondante à l'index
                return tiles[tileIndex];
            }
        }

        // Si l'index est invalide ou si l'image correspondante n'est pas trouvée, retournez null
        return null;

    }

    public ArrayList<Integer> getTerrainFond() {
        return terrainFond;
    }

    public Image[] getTiles() {
        return tiles;
    }

    public ArrayList<Integer> getTerrainAutres() {
        return terrainAutres;
    }
}
