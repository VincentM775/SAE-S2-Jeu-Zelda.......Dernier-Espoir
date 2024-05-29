package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteur;
import com.example.dernierespoirsae.modele.Armes.Armes;
import com.example.dernierespoirsae.modele.Inventaire;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VueArmes {
    private Pane paneHache;
    private Armes arme;
    private Inventaire inventaire;
    private Pane armePaneMap;
    private Acteur joueur;
    public VueArmes(Pane paneHache, Armes arme, Acteur joueur, Inventaire inventaire, Pane armePaneMap) {
        this.paneHache = paneHache;
        this.arme = arme;
        this.armePaneMap = armePaneMap;
        this.joueur = joueur;
        this.inventaire=inventaire;
        ajoutInventaire();
    }

    public VueArmes(Armes arme, Acteur joueur, Pane paneHache, Pane armePaneMap){
        this.paneHache = paneHache;
        this.joueur = joueur;
        this.armePaneMap = armePaneMap;
        suprimerArmeMap(arme);
    }

    public void ajoutInventaire(){
        if (inventaire.getArmes().contains(arme)) {
            creeViewArmeIventaire();
        }
        else{
            creeViewArmeMap();
        }
    }

    public void creeViewArmeMap() {

        Image hache = new Image("file:src/main/resources/com/example/dernierespoirsae/images/hache.png");
        ImageView imageView = new ImageView(hache);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.translateXProperty().setValue(arme.getX());
        imageView.translateYProperty().setValue(arme.getY());
        this.armePaneMap.getChildren().add(imageView);
        imageView.setId(""+this.arme.getId());
    }

    public void suprimerArmeMap(Armes arme){
        new VueArmes(paneHache, arme, this.joueur, this.joueur.getInventaire(), armePaneMap);
        this.armePaneMap.getChildren().remove(this.armePaneMap.lookup("#"+arme.getId()));

    }

    public void creeViewArmeIventaire() {
        Image image = new Image("file:src/main/resources/com/example/dernierespoirsae/images/hache.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        this.paneHache.getChildren().add(imageView);
        imageView.setId(""+this.arme.getId());
    }
}