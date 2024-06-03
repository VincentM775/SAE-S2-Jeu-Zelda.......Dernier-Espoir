package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Armes.Arme;
import com.example.dernierespoirsae.modele.Armes.Hache;
import com.example.dernierespoirsae.modele.Armes.Pistolet;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueArmes {
    private Pane hache;
    private Arme arme;
    private Inventaire inventaire;
    private Pane armePaneMap;
    private Acteur joueur;
    public VueArmes(Pane hache, Arme arme, Acteur joueur, Inventaire inventaire, Pane armePaneMap) {
        this.hache = hache;
        this.arme = arme;
        this.armePaneMap = armePaneMap;
        this.joueur = joueur;
        this.inventaire=inventaire;
        creeViewArmeMap();
    }

    public VueArmes(Arme arme, Acteur joueur, Pane hache, Pane armePaneMap){
        this.hache = hache;
        this.joueur = joueur;
        this.armePaneMap = armePaneMap;
        suprimerArmeMap(arme);
    }

    public void creeViewArmeMap() {

        Image hache = new Image("file:src/main/resources/com/example/dernierespoirsae/images/hache.png");
        Image pistolet = new Image("file:src/main/resources/com/example/dernierespoirsae/images/pistolet.png");
        ImageView imageView;
        if(this.arme instanceof Hache){
            imageView = new ImageView(hache);
        } else {
            imageView = new ImageView(pistolet);
        }
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.translateXProperty().setValue(arme.getX());
        imageView.translateYProperty().setValue(arme.getY());
        this.armePaneMap.getChildren().add(imageView);
        imageView.setId(""+this.arme.getId());
    }

    public void suprimerArmeMap(Arme arme){
        this.armePaneMap.getChildren().remove(this.armePaneMap.lookup("#"+arme.getId()));
    }
}