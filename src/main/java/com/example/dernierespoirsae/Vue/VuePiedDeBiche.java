package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Environnement;
import com.example.dernierespoirsae.modele.Objets.AutreObjets.PiedDeBiche;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class VuePiedDeBiche {
    private PiedDeBiche piedDeBiche;
    private Environnement environnement;
    private VueTerrain vueTerrain;
    private Pane terrainPane;

    public VuePiedDeBiche(PiedDeBiche piedDeBiche, Environnement environnement,VueTerrain vueTerrain,Pane terrainPane) {
        this.piedDeBiche = piedDeBiche;
        this.environnement = environnement;
        this.vueTerrain = vueTerrain;
        this.terrainPane = terrainPane;
        piedDeBiche.valeurPorteAEnleverProperty().addListener(((observable, oldValue, newValue) -> {
            enlevementDeLaPorte((Integer) newValue);
        }));
    }
    public void enlevementDeLaPorte(int valDanslaListe){
        Image imageFond = vueTerrain.obtenirImageTerrainFond(0,0,valDanslaListe);
        Image imageAutres = vueTerrain.obtenirImageTerrainAutres(0,0,valDanslaListe);
        StackPane stackpane = new StackPane(new ImageView(imageFond),new ImageView(imageAutres));
        this.terrainPane.getChildren().set(valDanslaListe,stackpane);

    }


}
