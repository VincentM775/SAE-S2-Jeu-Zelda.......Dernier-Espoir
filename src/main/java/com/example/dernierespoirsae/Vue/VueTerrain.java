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
    private ArrayList<Integer> m2;
    private Environnement environnement;

    public VueTerrain(Terrain terrain, TilePane terrainPane, ArrayList<Integer> m1, ArrayList<Integer> m2, Environnement environnement) {
        this.terrainPane = terrainPane;
        this.terrain = terrain;
        this.m2=m2;
        this.environnement = environnement;
        terrain.setTerrain(m1);

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
        ArrayList<Integer> m1 = terrain.getTerrain(); //On récupère la map sans le sol (que les colision)

        for (int i = 0; i < m1.size(); i++) {
            ImageView imageView = new ImageView();
            ImageView imageView2 = null;
            imageView.setId(""+i);

            int tileIndex = m1.get(i);
            int tileIndex2 = m2.get(i);
            if (tileIndex >= 1 && tileIndex <= tiles.length) // Assurez-vous que tileIndex est dans les limites
                imageView.setImage(tiles[tileIndex-6]);
            if (tileIndex2 >= 1 && tileIndex2 <= tiles.length) {
                imageView2 = new ImageView();
                imageView2.setId(""+i);
                imageView2.setImage(tiles[tileIndex2-6]);
            }
            if (tileIndex2 != 0) {
                this.terrainPane.getChildren().add(new StackPane(imageView2, imageView));
            }
            else this.terrainPane.getChildren().add(new StackPane(imageView));
        }
    }

    public Image obtenirImageTerrain(int ligne, int colonne) {
        int index = ligne * environnement.getInfoTuile()[1] + colonne;
        int tileIndex;

        // si index est valide
        if (index >= 0 && index < terrain.getTerrain().size()) {
            // recupère l'index de la tuile dans la liste représentant le terrain
             tileIndex = m2.get(index)-6;

            // si que tileIndex est dans les limites
            if (tileIndex >= 1 && tileIndex <= tiles.length) {
                // Retournez l'image correspondante à l'index
                return tiles[tileIndex];
            }
        }

        // Si l'index est invalide ou si l'image correspondante n'est pas trouvée, retournez null
        return null;

    }

    public ArrayList<Integer> getM2() {
        return m2;
    }

    public Image[] getTiles() {
        return tiles;
    }
}
