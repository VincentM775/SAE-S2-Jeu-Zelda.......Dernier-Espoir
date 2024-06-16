package com.example.dernierespoirsae.Vue;

import com.example.dernierespoirsae.modele.Acteurs.Joueur;
import com.example.dernierespoirsae.modele.Objets.Objets;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class VueObjetEquipee extends VueObjet {

    private Joueur joueur;
    private Pane objetPaneEquipee;

    public VueObjetEquipee(Objets objets, Joueur joueur, Pane objetPaneEquipee) {
        super(objets);
        this.joueur = joueur;
        this.objetPaneEquipee = objetPaneEquipee;
        creeViewObjet();
        ChangeListener<Boolean> effetUtilisation = ((obs, old, nouv)-> effetUtilisation() );
        joueur.armeALattaqueProperty().addListener(effetUtilisation);
    }

    @Override
    public void setPositionArme(ImageView imageView){
        imageView.xProperty().bind(joueur.xProperty());
        imageView.yProperty().bind(joueur.yProperty());
    }

    @Override
    public void ajoutImagePane(ImageView imageView){
        if(this.objetPaneEquipee.getChildren().size()==1){
            this.objetPaneEquipee.getChildren().remove(0);
        }
        this.objetPaneEquipee.getChildren().add(imageView);
    }
    public void effetUtilisation(){
        objetPaneEquipee.getChildren().get(0).rotateProperty().setValue(60);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            objetPaneEquipee.getChildren().get(0).rotateProperty().setValue(0);
            joueur.setArmeALattaque(false);
        }));
        timeline.setCycleCount(1); // Exécuter une seule fois
        timeline.play();
    }
}