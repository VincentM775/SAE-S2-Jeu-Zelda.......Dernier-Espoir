package com.example.dernierespoirsae.Observateur;

import com.example.dernierespoirsae.Vue.VueTerrain;
import com.example.dernierespoirsae.modele.Objets.Armes.Bave;
import com.example.dernierespoirsae.modele.Environnement;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class ObservateurTrainerBave implements ListChangeListener<Bave> {
    private Environnement environnement;
    private TilePane animationPane;
    private ArrayList<Integer> terrainData;
    private Image baveImage;
    private VueTerrain vueTerrain;

    public ObservateurTrainerBave(Environnement environnement, TilePane animationPane, VueTerrain vueterrain) {
        this.environnement = environnement;
        this.animationPane = animationPane;
        this.terrainData = environnement.getTerrain().getTerrain();
        this.baveImage = new Image("file:src/main/resources/com/example/dernierespoirsae/images/flaque_bave.png");
        this.vueTerrain = vueterrain;
    }
    @Override
    public void onChanged(Change<? extends Bave> bave) {
        while (bave.next()) {
            for (int i = 0; i < bave.getAddedSize(); i++) {
                int ligne = bave.getAddedSubList().get(i).getLigne();
                int colonne = bave.getAddedSubList().get(i).getColonne();
                Image terrainImage = vueTerrain.obtenirImageTerrain(ligne, colonne); // Obtenez l'image du terrain pour cette tuile
                int tuileDansListe = ligne * environnement.getInfoTuile()[1] + colonne;
                setImageAtIndex(tuileDansListe, baveImage, terrainImage);
                if (environnement.getListBave().size() == 5)
                    environnement.getListBave().remove(0);
            }
            for (int i = 0; i < bave.getRemovedSize(); i++) {
                int ligne = bave.getRemoved().get(i).getLigne();
                int colonne = bave.getRemoved().get(i).getColonne();
                Image terrainImage = vueTerrain.obtenirImageTerrain(ligne, colonne); // Obtenez l'image du terrain pour cette tuile
                int tuileDansListe = ligne * environnement.getInfoTuile()[1] + colonne;
                // Remplacer l'image de la bave par l'image du terrain
                setImageAtIndex(tuileDansListe, terrainImage, terrainImage);
            }
        }
    }



    public void setImageAtIndex(int index,Image baveImage, Image terrainImage) {
        // Créer un StackPane
        StackPane stackPane = new StackPane();

        // Ajouter l'image du terrain en arrière-plan
        ImageView terrainView = new ImageView(terrainImage);
        terrainView.setFitWidth(environnement.getInfoTuile()[0]);
        terrainView.setFitHeight(environnement.getInfoTuile()[0]);
        stackPane.getChildren().add(terrainView);

        // Ajouter l'image de la bave par-dessus
        ImageView baveView = new ImageView(baveImage);
        baveView.setFitWidth(environnement.getInfoTuile()[0]);
        baveView.setFitHeight(environnement.getInfoTuile()[0]);
        stackPane.getChildren().add(baveView);

        // Remplacer le Node existant à l'index spécifié par le StackPane nouvellement créé
        animationPane.getChildren().set(index, stackPane);
    }


}
