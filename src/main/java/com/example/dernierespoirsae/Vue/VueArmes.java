package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Armes.Armes;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class VueArmes {
    private Pane armePane;
    private Armes arme;

    public VueArmes(Pane armePane, Armes arme) {
        this.armePane = armePane;
        this.arme = arme;
    }
    public void creeViewArme(){
        Image image = new Image("file:file:src/main/resources/com/example/dernierespoirsae/images/hache.png");

    }
}
