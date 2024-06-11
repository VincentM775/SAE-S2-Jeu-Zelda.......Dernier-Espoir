package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Armes.Arme;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class VueArmeEquipee extends VueArme{

    private Joueur joueur;
    private Pane armePaneEquipee;

    public VueArmeEquipee(Arme arme, Joueur joueur, Pane armePaneEquipee) {
        super(arme);
        this.joueur = joueur;
        this.armePaneEquipee = armePaneEquipee;
        creeViewArme();
        ChangeListener<Boolean> effetAttaque = ((obs, old, nouv)-> effetAttaque() );
        joueur.armeALattaqueProperty().addListener(effetAttaque);
    }

    @Override
    public void setPotitionArme(ImageView imageView){
        imageView.xProperty().bind(joueur.xProperty());
        imageView.yProperty().bind(joueur.yProperty());
    }

    @Override
    public void ajoutImagePane(ImageView imageView){
        if(this.armePaneEquipee.getChildren().size()==1){
            this.armePaneEquipee.getChildren().remove(0);
        }
        this.armePaneEquipee.getChildren().add(imageView);
    }
    public void effetAttaque(){
        armePaneEquipee.getChildren().get(0).rotateProperty().setValue(60);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            armePaneEquipee.getChildren().get(0).rotateProperty().setValue(0);
            joueur.setArmeALattaque(false);
        }));
        timeline.setCycleCount(1); // Exécuter une seule fois
        timeline.play();
    }
}